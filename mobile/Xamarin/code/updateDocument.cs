//update document
var updatedProperties = new Dictionary<string, object>(retrievedDocument.Properties);
updatedProperties["message_0"] = "This is an updated entry";
updatedProperties["temperature"] = 95.0;

var updatedRevision = retrievedDocument.PutProperties(updatedProperties);
System.Diagnostics.Debug.Assert(updatedRevision != null);

Console.WriteLine("Updated document: ");
foreach (var entry in updatedRevision.Document.Properties){
	Console.WriteLine("{0} : {1}" , entry.Key, entry.Value);
}