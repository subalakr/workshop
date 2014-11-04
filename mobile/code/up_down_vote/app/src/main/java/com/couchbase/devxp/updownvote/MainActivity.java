package com.couchbase.devxp.updownvote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.couchbase.lite.*;
import com.couchbase.lite.util.Log;


public class MainActivity extends ListActivity {

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(Application.TAG, "Starting MainActivity");
        Application application = (Application) getApplication();

        // Wire up the list view with the presentations in the Database via the Presentation Adapter for display
        this.database = application.getDatabase();

        Query all = Presentation.findAll(database);

        setListAdapter(new PresentationAdapter(all.toLiveQuery(), this));

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

        // Wire up the button defined in the main_menu.xml
        if (id == R.id.action_add) {
            addPresentation();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Create the button to add new presentations as needed
    private void addPresentation() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Presentation Title");
        final EditText input = new EditText(this);
        input.setMaxLines(1);
        input.setSingleLine(true);
        alert.setView(input);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = input.getText().toString();
                Presentation presentation = new Presentation(database);
                presentation.setTitle(title);
                try {
                    presentation.save();
                } catch (CouchbaseLiteException e) {
                    Log.e(Application.TAG, "Failed to save presentation");
                    return;
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        alert.show();

    }
}
