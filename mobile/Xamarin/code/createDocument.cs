//Create a document 
var properties = new Dictionary<string, object>(){
	{"message_0", "Hello Couchbase Mobile"},
	{"message_1", "These are keys & values in the document database"},
	{"message created:", DateTime.utcNow.ToString("o")},
};

var document = database.CreateDocument();
System.Diagnostics.Debug.Assert(document != null);

//create and save a new document revision in the database
var revision = document.PutProperties(properties);
System.Diagnostics.Debug.Assert(revision != null);

var docID = document.Id; 
Console.WriteLine("Document created with ID = {0}", docID);


