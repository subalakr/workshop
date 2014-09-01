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

What is Couchbase Lite, what are the alternatives and what mobile-specific
problems does it solve? This section is aimed at making the audience familiar
with the issues of data sync and the ecosystem in which Couchbase Lite exists,
as well as providing a general introduction to the Couchbase Mobile stack.

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

During this section the attendees will build a simple app that stores data in
Couchbase Lite. The app allows people to record an up-vote or down-vote, similar
to those found at some tech conferences to rate the speaker. We'll use the same
back-end for everyone in the room, enabling us to record and view the votes from
across the room once everyone has implemented their app. The attendees will
create a document, read it back and then write it back after having incremented
the vote count.

- Introduction to the UpVote/DownVote app
  - Demo of the app and outline of what will be covered
- Install Couchbase Lite or the VM provided
- Introduction to the environment
  - Phonegap: add the Couchbase Lite plugin
- 45 minutes to one hour to enable the attendees to implement the app
  - With access to a git repo, broken into steps, that enables people to check
  they're on the right course


### Day to day development with Couchbase Lite - 45min

The common concepts of developing with Couchbase Lite, going beyond the basic
CRUD functionality of the demo app.

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

Synchronisation and coping with conflict, presented by example of a to-do list
data model.

- Types of synchronisation and their trade-offs
- Channels and practical considerations
- Dealing with conflict
  - introduction to how Couchbase Lite detects conflicts
  - choosing the right data structures
  - how to resolve conflicts client-side


data synchronization in depth - 3h
----------------------------------

Pre-requisites:
- flipchart / whiteboard

### data modeling for couchbase mobile - 1.5h

As with Couchbase server, Couchbase mobile performs best when the data is
modeled to facilitate the access patterns, so this section goes into detail on
how to model data with couchbase mobile for different access needs.

- Couchbase Lite Views
  - Creating indexes via map views
  - processing data in reduce steps
  - Common document structures to work with views
- Key / Value access to documents
  - Choosing good keys
  - When to use K/V vs Views
- Document attachments
  - Use cases for attachments
  - Attachment storage on the device and the server
- Using Channels to segregate data
  - Use cases of channels
  - Limitations for channels
- Modeling for conflicts
  - How to limit the conflicts through good data modeling
  - Data structures to deal with conflicts

### couchbase mobile - the server side - 1.5h

When integration Couchbase Mobile in an existing architecture it is important to
integrate it well on the server side as well as on the mobile app side. This
section goes into detail how Couchbase Server and Sync Gateway work together to
create a unified solution.

- Interaction between Couchbase Server and Sync Gateway
  - Syncing API to the outside
  - Sync Gateway Buckets and views
  - Bucket shadowing to make data accessible via Couchbase and Sync Gatway
- Creating a sync function
  - Authentication
  - Roles
  - Modifications
- Resolving Conflicts outside the mobile applications
  - using the changes feed
- Scaling sync gateway in production


