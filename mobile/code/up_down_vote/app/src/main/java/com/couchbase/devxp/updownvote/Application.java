package com.couchbase.devxp.updownvote;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;
import com.couchbase.lite.util.Log;

import org.apache.http.client.HttpResponseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by phil on 03/11/14.
 */
public class Application extends android.app.Application {
    public static String TAG = "UpVoteDownVote";
    private static final String SYNC_URL_HTTP = "http://localhost:4984/db";
    private static String DBNAME = "upvotedownvote";
    private Database database = null;
    private Manager manager;

    public Database getDatabase() {
        if(database == null) {
            try {
                manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
                Log.d(TAG, "Created database manager");

                if(!Manager.isValidDatabaseName(DBNAME)) {
                    Log.e(TAG, "Bad database name");
                    return null;
                }

                try {
                    database = manager.getDatabase(DBNAME);
                    Log.d(TAG, "Database created");
                } catch (CouchbaseLiteException e) {
                    Log.e(TAG, "Database creation failed");
                    return null;
                }
            } catch (IOException e) {
                Log.e(TAG, "Failed to create database manager");
                return null;
            }

        }
        return database;
    }

    private void setupSync() {
        URL url;
        try {
            url = new URL(SYNC_URL_HTTP);
        } catch (MalformedURLException e) {
            Log.e(Application.TAG, "Sync URL is invalid, setting up sync failed");
            return;
        }

        Replication pull = database.createPullReplication(url);
        Replication push = database.createPushReplication(url);

        pull.setContinuous(true);
        push.setContinuous(true);

        pull.addChangeListener(getReplicationChangeListener());
        push.addChangeListener(getReplicationChangeListener());

        pull.start();
        push.start();
    }

    // print out errors and see what is going on
    private Replication.ChangeListener getReplicationChangeListener() {
        return new Replication.ChangeListener() {
            @Override
            public void changed(Replication.ChangeEvent event) {
                Replication replication = event.getSource();
                if (replication.getLastError() != null) {
                    Throwable lastError = replication.getLastError();
                    if (lastError.getMessage().contains("existing change tracker")) {
                        Log.e("Replication Event", String.format("Sync error: %s:", lastError.getMessage()));
                    }
                }
                Log.d(TAG, event.toString());
                Log.d(TAG, "Completed: " + replication.getCompletedChangesCount() + " of " + replication.getChangesCount());
            }
        };
    }


    @Override
    public void onCreate() {
        // Load up the database on start, if this fails the app is of no use anyway
        if(getDatabase() == null) {
            Log.e(TAG, "Failed to initialize");
            return;
        }

        // Setup the Sync for Couchbase Lite to a Sync Gateway
        setupSync();
        super.onCreate();
    }

}
