Mobile Segments
===============

Content: 6h

getting started with couchbase mobile - 3h
------------------------------------------

Pre-requisites:

- access to a laptop
- phonegap tools installed or virtualbox to run the provided VM
- basic Javascript experience

### why couchbase lite? - 30min

What is Couchbase Lite, what are the alternatives and what mobile-specific problems does it solve? This section is aimed at making the audience familiar with the issues of data sync and the ecosystem in which Couchbase Lite exists, as well as providing a general introduction to the Couchbase Mobile stack.

####Content outline:

 - Introduction to the status quo:
  - what we use now to store mobile data and their pros/cons
  - advantage of locality of data
  - the challenges of data synchronisation
- Introducing Couchbase Lite
 - Overview of the Couchbase Lite model
 - Quick reference to Sync Gateway and the full Couchbase Mobile stack
 - demo
 - supported platforms
- Built-in replication / synchronization

### your first couchbase lite app - 1h

During this section the attendees will build a simple app that stores data in Couchbase Lite. The app allows people to record an up-vote or down-vote, similar to those found at some tech conferences to rate the speaker. We'll use the same back-end for everyone in the room, enabling us to record and view the votes from across the room once everyone has implemented their app. The attendees will create a document, read it back and then write it back after having incremented the vote count.

- Introduction to the UpVote/DownVote app
 - Demo of the app and outline of what will be covered
- Install Couchbase Lite or the VM provided
- Introduction to the environment
 - Phonegap: add the Couchbase Lite plugin
- 45 minutes to one hour to enable the attendees to implement the app
 - With access to a git repo, broken into steps, that enables people to check they're on the right course


### Day to day development with Couchbase Lite - 45min

The common concepts of developing with Couchbase Lite, going beyond the basic CRUD functionality of the demo app.

- Storing data
 - keying
 - mapping documents to classes
 - platform-specific data integration
- Views and indexing:
 - introduction to views and when to use them
 - example views
- Querying:
 - basic queries and sorting
 - reduce queries and where to use them
 - live queries and where to use them.

### dealing with change - 45min

Synchronisation and coping with conflict, presented by example of a to-do list data model.

- Types of synchronisation and their trade-offs
- Channels and practical considerations
- Dealing with conflict
 - introduction to how Couchbase Lite detects conflicts
 - choosing the right data structures
 - how to resolve conflicts client-side


data synchronization in depth - 3h
----------------------------------

- flipchart / whiteboard

### data modelling for couchbase mobile - 1.5h
- map and reduce views
- keying documents
- handling attachments
- channels
- dealing with conflicts

### couchbase mobile - the server side - 1.5h
- sync gateway and couchbase server
- sync function
- interfacing with existing data (bucket shadowing)
- server side conflict resolution via changes feed
- scaling sync gateway


