Couchbase Mobile Developer Day
==============================

Status: Draft

Pre-requisites
---------------

 * Laptop with a relevant development environment installed
 * Phonegap tools installed or willingness to run a VM provided by the instructor
 * Basic JavaScript experience
 * Flipchart/whiteboard for the room

Content: 6h


## Why Couchbase Lite? - 30min

What is Couchbase Lite, what are the alternatives and what mobile-specific
problems does it solve?

This section is aimed at making the audience familiar
with the issues of data sync and the ecosystem in which Couchbase Lite exists,
as well as providing a general introduction to the Couchbase Mobile stack.

- Introduction to the status quo:
  - what we use now to store mobile data and their pros/cons (remote, local, sync)
  - advantage of locality of data
  - the effects of data location:
    - remote-only: covering the trade-offs of availability and responsiveness
    - local-only: covering the trade-off of great responsiveness and availability versus an isolated app
    - local with remote sync: the responsiveness and availability of a local app with asynchronous connectivity
  - the challenges of data synchronisation
- Introducing Couchbase Lite
  - Overview of the Couchbase Lite model
  - Quick reference to Sync Gateway and the full Couchbase Mobile stack
  - support platforms
  - demo
- Built-in replication / synchronisation

## Your first Couchbase Lite app - 2h

During this section the attendees will build a simple app that stores data in
Couchbase Lite. The app allows people to record an up-vote or down-vote, similar
to those found at some tech conferences to rate the speaker.

We'll use the same back-end for everyone in the room, enabling us to record and
view the votes from across the room once everyone has implemented their app. The
attendees will create a document, read it back and then write it back after having
incremented the vote count.

- Introduction to the UpVote/DownVote app
  - Demo of the app and outline of what will be covered
- Install Couchbase Lite or the VM provided
- Introduction to the environment
  - Getting setup with android Development for CouchbaseLite
- 45 minutes to one hour to enable the attendees to implement the app
  - With access to a git repo, broken into steps, that enables people to check
  they're on the right course


## Day to day development with Couchbase Lite - 45min

The every-day concepts of developing with Couchbase Lite, going beyond the basic
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

## Dealing with change - 45min 
### [2. iteration]

Synchronisation and coping with conflict, presented by example of a to-do list
data model.

- Types of synchronisation and their trade-offs
- Channels and practical considerations
- Dealing with conflict
  - introduction to how Couchbase Lite detects conflicts
  - choosing the right data structures
  - how to resolve conflicts client-side


## Data modeling for Couchbase Mobile - 1h
### [2. iteration]

Couchbase Mobile works best when we understand how to model our data to suit our
access patterns. Here we'll look in detail at how to model data with Couchbase Mobile
taking into account common access requirements.

- Couchbase Lite views
  - creating indexes via map views
  - processing data in reduce steps
  - common document structures for working with views
- Key / Value access to documents
  - choosing good keys
  - when to use K/V rather than views
- Document attachments
  - use cases for attachments
  - attachment storage on the device and the server
- Using channels to segregate data
  - Use cases for channels
  - limitations of channels
- Modeling for conflicts
  - how to limit conflicts through data modeling
  - data structures to deal with conflicts

## Couchbase Mobile: the server side - 1h

Presenting the practicalities of working with Sync Gateway and integrating
Couchbase Mobile with Couchbase Server.

- Interaction between Couchbase Server and Sync Gateway
  - syncing API to the outside
  - sync Gateway buckets and views
  - bucket shadowing to make data accessible via Couchbase and Sync Gatway
- Creating a sync function
  - authentication
  - roles
  - modifications
- Resolving monflicts on the server side
  - using the changes feed
- Scaling sync gateway in production


