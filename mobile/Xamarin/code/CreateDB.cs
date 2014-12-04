//Create a Database
var dbName = "myDB";
System.Diagnostics.Debug.Assert(Manager.IsValidDatabaseName(dbName));

var database = manager.GetDatabase(dbName);
System.Diagnostics.Debug.Assert(database != null);
Console.WriteLine("Database named, " + dbName +" created");

