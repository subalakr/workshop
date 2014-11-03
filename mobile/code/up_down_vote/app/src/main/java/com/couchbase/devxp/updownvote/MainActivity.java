package com.couchbase.devxp.updownvote;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;


public class MainActivity extends Activity {

    public static String TAG = "UpVoteDownVote";
    public static String DBNAME = "upvotedownvote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Manager manager;
        Database database;

        try {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
            Log.d(TAG, "Created database manager");

            if(!Manager.isValidDatabaseName(DBNAME)) {
                Log.e(TAG, "Bad database name");
                return;
            }

            try {
                database = manager.getDatabase(DBNAME);
                Log.d(TAG, "Database created");
            } catch (CouchbaseLiteException e) {
                Log.e(TAG, "Database creation failed");
                return;
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to create database manager");
            return;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
