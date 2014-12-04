//Retrieve Document
var retrieveDocument = database.GetDocument(docID);

Console.WriteLine("Retrieved document: ");
foreach(var entry in retrievedDocument.Properties){
	Console.WriteLine("{0} : {1}" , entry.Key, entry.Value);
}



