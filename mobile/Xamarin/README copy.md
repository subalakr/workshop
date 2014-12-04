##Workshop

###Running the Sample Application

First, let's open the Tasky sample application. Tasky is a simple To-Do application that you can use to track tasks. It's a cross-platform sample that shares its core code between iOS and Android and illustrates good cross-platform architectural design including data access, business logic, and native-UI elements.

The solution and project structure in the Solution Pad shows all the files in the solution and should look familiar to any modern IDE user. It consists of two projects containing a number of C# files.

If you cannot see the Solution Pad, choose View → Pads → Solution from the menu.

[solution pad]

Check that the Debug configuration is selected in the toolbar and choose Run → Debug from the menu or press Command + Return (or the 'play' button) to start debugging with the iOS Simulator:

[app in sim]

The app looks pretty empty to start with – press the plus button and add a few tasks to see how it works.

Before looking at the code more closely, let's revisit the solution and application architecture. There are two projects, which help facilitate a cross-platform solution when combined with Xamarin.Android:

###Tasky

An iOS application project containing the user-interface and application layer for iPhones & iPads.

####Tasky.Core.iOS

A Core library project which contains platform-independent code. This code is shared between different device platforms such as Android, iOS, and Windows Phone. Each platform has a different .csproj file (e.g. Tasky.Core.Android, Tasky.Core.iOS) but share the same C# code files.

####Understanding the Architecture

The architecture and project structure of the Tasky application is shown in the following diagram:

[architecture diagram]

- User Interface - The screens, controls and data presentation code. In Xamarin.iOS these classes are wrappers around the iOS CocoaTouch frameworks. The user interface that you build looks, feels and performs like a native Objective-C application.
- App Layer - Custom classes to bind the business layer to the user interface, typically requiring platform specific features.
- Business Layer - Business object classes and business logic.
- Data Access Layer - Abstraction layer between the business logic and the data layer.
- Data Layer - Low-level data persistence and retrieval; the Tasky sample uses a SQLite database binding.

####Modifying Tasky

As it sits, Tasky lacks a basic feature of any decent task application: the ability to mark a task complete. We're going to add that functionality by making the following modifications:

1. Implement a new property on the Task object called Done.

2. Add a mechanism in the UI to allow users to mark a task as completed.

3. Change the home screen to display whether a task is completed.

4. Allow deleting a task.

[task detail]

#####Implement a new property on the Task class

Open the Task.cs file in **Tasky.Core.iOS** and notice the class has the following property:

	public bool Done { get; set; }
	
_**TODO: this is where we can use Couchbase**_

Tasky uses _ADO.NET_ to store and retrieve data using the built-in SQLite database engine. All data operations can be performed with familiar SQL syntax and ADO.NET classes. Here's an example of how ADO Connection and Command objects can be used to access data stored in SQLite on a device:

	using (var command = connection.CreateCommand ()) {  
	  command.CommandText = "SELECT [\_id], [Name], [Notes], [Done] from [Items] WHERE [\_id] = ?";  
	  command.Parameters.Add (new SqliteParameter (DbType.Int32) { Value = id });  
	  var reader = command.ExecuteReader ();  
	  if (reader.Read ())   
	    task = FromReader (reader);  
	}

Now we need to modify the UI to support this change.

#####Add corresponding controls to the Task Details screen in the storyboard

Now that we've modified our Task object, let's modify the user interface to allow users to mark them as complete and also to delete a task.

Tasky uses the Xamarin iOS designer to create the user interface, contained in the HomeScreen.storyboard file.

We can drag and drop a Switch and Label to change the Done property, and a Button to delete a task, giving each control a Name in Properties pad.

[storyboard]

#####Sync changes made in the user interface back to the business object

First let's implement delete. If we double-click on the button, an event handler for the TouchUpInside event is generated in the TaskDetailController class:

    partial void DeleteButton_TouchUpInside (UIButton sender)
    {
      if(CurrentTask.ID >= 0)
        TaskManager.DeleteTask(CurrentTask.ID);

      NavigationController.PopViewControllerAnimated (true);
    }

To persist the Done property, we simply access the value from the Switch by the name we gave the control in the Property pad and set the value of the Task object:

    partial void SaveButton_TouchUpInside (UIButton sender)
    {
      CurrentTask.Name = taskNameField.Text;

      CurrentTask.Notes = taskNotesField.Text;

      CurrentTask.Done = taskDoneSwitch.On;

      TaskManager.SaveTask(CurrentTask);

      NavigationController.PopViewControllerAnimated (true);
    }

Also, we can set the initial value of the Switch when the view loads:

    public override void ViewDidLoad ()
    {
      base.ViewDidLoad ();

      Title = "Task Details";

      if (CurrentTask != null) {
        taskNameField.Text = CurrentTask.Name;

        taskNotesField.Text = CurrentTask.Notes;

        taskDoneSwitch.On = CurrentTask.Done;
      }
    }

#####Alter the Home screen so that the Done status is displayed in the list

To complete our new feature, we need to display the completion status of each task on the home screen.

We can do this by simply updating the cell's Accessory in the DataSource's GetCell method in HomeScreenController.cs, based on the value of the task's Done property:

	cell.Accessory = task.Done ? UITableViewCellAccessory.Checkmark : UITableViewCellAccessory.None;

[cell accessory]

We've now created our first application in Xamarin.iOS. We've seen Xamarin Studio and we built and tested an application in the simulator.