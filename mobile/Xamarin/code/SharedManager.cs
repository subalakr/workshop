//Create a shared Manager
var manager = Manager.SharedInstance;
System.Diagnostics.Debug.Assert(manager != null);
Console.WriteLine("We now have a Manager created");
