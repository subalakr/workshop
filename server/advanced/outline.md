Advanced segments
=================

Status: Pre-draft

Target Audience
---------------

The content is for developer who are already very familiar with Java and have
some experience with other NoSQL systems and now want to know more on Couchbase
and advance their knowledge.

Pre-requisites
---------------

 * Laptop with a relevant development environment installed
 * Couchbase + Java SDK installed or willingness to run a VM provided by the instructor
 * Java experience
 * NoSQL experience
 * Flipchart/whiteboard for the room

Content: 4h

Deep dive into developing with couchbase - 4h
---------------------------------------------

### Client architecture by Java as an example - 1h

What does happen on the SDK level when you interact with Couchbase?

In this part we go into detail on the Java SDK 2.0+, its current architecture
and how it influences your application design. We do this by example of the
common operations.

- Couchbase operations using the Java SDK
  - CRUD / prepend / append / increment / decrement
  - Following the operations through the SDK
    - Scheduling
    - Connection multiplexing
  - Operations server side
- Atomic operations
- Driver architecture

### Advanced client operations 1.5h

We have looked at the CRUD operations, now it is time to look into handling
concurrent access to Couchbase, as well as using views to extract data.

This part will go into detail on CAS optimistic locking, and using views from
the SDK.

- Concurrent document access
  - Locking
    - Optimistic locking
      - Tutorial dealing with optimistic locking
    - Pessimistic locking
- Monitoring operations
  - Observing operations
  - ReplicateTo and PersistTo
  - Tutorial using monitoring to observe operations
- Performance considerations


### Couchbase Views - 1h

Couchbase supports secondary indexes, called views. They allow for some advanced
data processing and access patterns.

- Creating view queries
  - Options
  - Queries over HTTP
  - Tutorial on writing views and querying them
- Performance considerations
- Server side
  - Eventual consistency
- Use cases for views

### 2 cents on N1QL - 30min

While it is not released as of yet, N1QL provides an interesting insight what is
possible with Couchbase. So this section provides a quick intro to N1QL

- N1QL in general
  - Installing
  - Running queries
  - Tutorial 
- N1QL and the Java SDK
  - Creating queries
  - Type safe queries

