# Couchbase Dev Day

Aim: 12h of content
     split in 2h logical segments to choose from
     each consistent of 1h slots


- There should be 2 versions
- People who are already using Couchbase want more detail
- People completely new to Couchbase want more inspiration
- This is valid since NoSQL grows more common all the time
  - there are enough people familiar with at least 2 other NoSQL databases
    telling them "err... You know JSON?" does not cut it
    - They already know MongoDB + Cassandra or alike
    - They have faced problems with other NoSQL DB and want to know
      why Couchbase is better, not what NoSQL means
    - They need clear strategies on how to implement certain patterns
- there are developers who have not used any NoSQL DB and are just used to
  relational
  - Most likely this is going to be Java or .NET developers (sorry this is
    just the case)
  - They need more hand holding
  - More inspiration on what is possible
  - Reason to use it
    - Solve problems they really have (sorry no schema is NOT a plus in most
      cases) schema upgrades are even harder to manage in Couchbase then in
      PostgreSQL or alike
    - If they don't use anything like Mongo etc. most likely they don't care
      about JSON, this is more of an overhead for Java devs then a benefit,
      sorry!
    - they want integration in Spring (Java), .NET and we have it so present
      it!
    - They want compelling use cases
      - Logging, compelling case for unstructuredness
      - Sessions, hard to sell unless they develop in really large
        organizations so, and not really exciting
      - click tracking, compelling case for high volume even on a small app
      - Cache, this is not exciting so
      - Real-time search, combines views and Key/Value queries, somewhat
        exciting

## What do we want to cover
- Couchbase Server
  - CRUD
  - Views
  - Data Access Patterns
  - Data Modeling

- Couchbase Sync Gateway
  - Syncing (hehe)
  - Authentication
  - Channels

- Couchbase Lite
  - Multi OS support (iOS, Android, Windows Phone, Java)
  - Data format
  - Views

- Where is Couchbase going
  - N1QL
  - GeoSpatial

## Mobile

- why couchbase lite
  - demo
  - platforms
  - advantages of local data
- getting setup
  - setting up the SDK
  - setting up sync gateway
- datamodelling for couchbase mobile
  - consistency model
  - keys
  - importing data
  - channels
  - attachments
  - dealing with conflicts
    - clientside via change callback
    - server side via monitoring the changes feed
- couchbase mobile views
  - introduction to views
  - view examples
    - geo?
  - querying a view
    - live query
- synchronization / replication
  - setup
  - continuous
  - performance implications
- couchbase mobile server side
  - sync gateway
  - sync function
    - authorization / authentication
  - sync bucket
    - bucket shadowing
  - scaling sync gateway
  - server side conflict resolution
    - serverside via node follow monitoring the changes feed


## Goals
- Jump into code quicker
  - abstract is hard to follow
  - especially when the goal is not to understand all the details
- More interactivity
  - More show and tell
  - More on what can be build
  - This should inspire people to use Couchbase not "just explain"
  - More example apps
    - Pointers to how to do stuff
- More real code
  - it is a dev day after all!
- Identify common tasks and model them in Couchbase

## Guides

### Couchbase for NoSQL users
  - Quickstart
    - Setup
    - Webinterface
    - Connecting to Couchbase
  - Storing data
    - what happens
    - instrumenting the code through observe
      - what do the different persistence models mean
  - updating data
    - atomic operations (read + modify)
      - incr, decr, append, prepend
    - non atomic read + modify dealing with concurrency
      - cas
  - data modeling
    - KV Patterns
  - views
    - when KV is not enough
    - consistency
    - changes to 2.X

### Couchbase for beginners
  - Getting Started
    - Setup Couchbase
    - The Web interface
  - CRUD operations

### How to support the developers
- create git repositories with all the samples
- tag each step in the repository to allow moving forward
