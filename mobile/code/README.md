Up Down Vote App
================

Android version of the App presented in the Server developer Day.

The app manages a simple list of "Presentations" like the ones given at a
developer day, and lets all the people connected to the same sync gateway vote
on them (Thumbs up / Thumbs down).

To make it easy there is no Authentication what-so-ever!

Setup
----
Clone down the Repository, and import into android Studio. The app is Runnable
against API level 19, which means KitKat. Lollipop is not supported by Couchbase
Lite, as of right now.

Now all you need todo is either have a sync_gateway in the cloud, or run one
locally via the included config

    $ sync_gateway up_down_vote/sync_gateway.json

The URL is set in the Application Class.

Enjoy Voting!


