DRAFT:  Include Android Studio experience
NB:  Turn this into guides

If you are using Eclipse as your IDE, go to the Eclipse section.

If you are using Xcode as your IDE, go to the Xcode section.

Only one of these sections needs to be completed based on your choice of IDEs.
Eclipse

Creating The Android Project

1.	Start Eclipse.
2.	Go to File->New->Other.
3.	In the Android section, choose ‘Android Application Project‘.
4.	Click on ‘Next’.
5.	Give the project the following properties:
Application Name: CouchbaseEvents
Project Name: CouchbaseEvents
Package Name: com.couchbase.training.couchbaseevents
6.	Change the minimum required SDK to ‘API 9’.
7.	Click on ‘Next’.
8.	Click on ‘Next’.
9.	Click on ‘Next’.
10.	Click on ‘Next’.
11.	Click on ‘Finish’.
12.	The new Android project will be created.
Decompressing Couchbase Lite

1.	Go to the directory where you downloaded Couchbase Lite for Android.
2.	Decompress the zip file.
3.	Go to the directory where the contents of the zip file were written.
4.	Change the name of cbl_collator_so-1.0.2.jar to cbl_collator_so-1.0.2.zip.
5.	Decompress cbl_collator_so-1.0.2.zip.
6.	Move all of the directories under lib to the same directory as the other JARs.
Adding The Couchbase Lite Libraries

1.	Copy all of the JAR files that were in the Couchbase Lite for Android zip file to the project‘s libs directory.
2.	Copy all of the directories from cbl_collator_so-1.0.2.zip under the lib directory to the project‘s libs directory.
3.	In Eclipse, right click on the ‘CouchbaseEvents’ project and click on ‘Refresh’.
4.	In the project, expand the libs directory. It should look like:
 

7.	If your libs directory does not appear the same as the above image, you will need to try again. If you don’t get this correct, the Couchbase Lite classes will not be usable.

Running The App

1.	Verify that the App builds and runs by going to Run->Run.
2.	If you do not have an AVD, you will need to create one with Android Emulator that uses at least version 9, but the class and all example code will target SDK version 19.
 
Xcode

Creating The Xcode Project

1.	Start Xcode.
2.	Click on ‘Create a new Xcode Project’.
3.	Under ‘iOS’ and ‘Applications’, choose ‘Single View Application’.
4.	Click on ‘Next’.
5.	Give the project the following properties:
Product Name: CouchbaseEvents
Organization Name: Couchbase Training
Organziation Identifier: com.couchbase.training
6.	Click on ‘Next’.
7.	Choose a place to store the project and click on ‘Create’.
Decompressing Couchbase Lite

1.	Go to the directory where you downloaded Couchbase Lite for iOS.
2.	Decompress the zip file.

Adding The Couchbase Lite Libraries

1.	Open the Couchbase Lite for iOS folder and drag the CouchbaseLite.framework folder to the ‘Frameworks’ group in the Xcode project navigator.
2.	In the navigator, click on the CouchbaseEvents project file to open the project editor for your app, and then click the ‘Build Settings’ tab.
3.	Scroll to the ‘Linking’ section, find the ‘Other Linker Flags’ row, and then add the flag (be sure to use the capitalization shown):
-ObjC
4.	Click the ‘Build Phases’ tab.
5.	Expand the ‘Link Binary With Libraries’ section and add the following items:
CFNetwork.framework
Security.framework
SystemConfiguration.framework
libsqlite3.dylib
libz.dylib
Running The App

1.	Verify that the App builds and runs by going to Product->Run.
