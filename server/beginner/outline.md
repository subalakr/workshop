Beginner Segments
=================

Status: Pre-draft

Pre-requisites
---------------

 * Laptop with a relevant development environment installed
 * Couchbase + Java SDK installed or willingness to run a VM provided by the instructor
 * Basic Java experience
 * Flipchart/whiteboard for the room

Content: 4h

Hit the ground running - interaction with Couchbase - 2h
--------------------------------------------------------

### Setting up Couchbase and web interface - 1h

What is Couchbase and how do you interact with it.

This section is about getting setup with Couchbase and storing and retrieving
data from it.

- Installing Couchbase
  - Configuring Couchbase
  - Setting up a bucket
- Interacting with the web interface
  - Storing and viewing documents
  - Developing Views
    - Writing basic indexes (map)
    - Querying via the Web interface
    - Reduce views
    - Development vs Production Views
- Basic Couchbase operations beyond the web interface
  - CRUD, Replace, Add, Append, Prepend

### Your first Couchbase app - 1h

From a developers point of view there is more to Couchbase then the Web
interface, so how do you use an SDK to interact with it?

This section is really about day to day development with Couchbase. Using the
Java SDK as an example we explore the operations Couchbase supports and how to
develop a simple app using it.

- Connecting to Couchbase
  - The Couchbase smart client
  - Patterns for managing the connection
- Storing and retrieving data
  - Creating JSON objects in Java
  - CRUD operations
- Hands on
  - Creating a login with session
- Statistics with views
  - Creating a view to show active sessions
  - Querying views from Java

How Couchbase works - 2h
------------------------

### Exploring Couchbase - 1h

- reading data
  - simple objects
  - JSON
- storing data
  - example
  - consistency
  - replication
- scaling out

### data modeling - 1h

- recap Data types
- JSON documents
- Modeling data as JSON
- Indexing
- Key Value access patterns

