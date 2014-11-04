package com.couchbase.devxp.updownvote;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.LiveQuery;

/**
 * Created by phil on 04/11/14.
 */
public class PresentationAdapter extends LiveQueryAdapter {

    public PresentationAdapter(LiveQuery query, Context context) {
        super(query, context);
    }

    @Override
    // Display a Presentation as an Item in the list View
    public View getView(int position, View convertView, ViewGroup parent) {
        // Load the View if not done so already from the view_presentation.xml
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_presentation, null);
        }

        // Get the document currently to be displayed
        final Document document = (Document) getItem(position);

        // make sure this is valid
        if (document == null || document.getCurrentRevision() == null) {
            return convertView;
        }

        // Turn the document into a presentation model we can operate on
        final Presentation presentation = Presentation.from(document);


        // Fill in all the view items
        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(presentation.getTitle());

        TextView upVotesView = (TextView) convertView.findViewById(R.id.up);
        upVotesView.setText(Integer.toString(presentation.getUpVotes()));
        TextView downVotesView = (TextView) convertView.findViewById(R.id.down);
        downVotesView.setText(Integer.toString(presentation.getDownVotes()));

        // Wire up up voting
        Button upVoteButton = (Button) convertView.findViewById(R.id.up_vote);
        upVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int upVotes = presentation.getUpVotes();
                presentation.setUpVotes(upVotes + 1);
                try {
                    presentation.save();
                } catch (CouchbaseLiteException e) {
                    Log.e(Application.TAG, "Failed to save presentation update for up vote");
                }
            }
        });

        // Wire up down voting
        Button downVoteButton = (Button) convertView.findViewById(R.id.down_vote);
        downVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int downVotes = presentation.getDownVotes();
                presentation.setDownVotes(downVotes  + 1);
                try {
                    presentation.save();
                } catch (CouchbaseLiteException e) {
                    Log.e(Application.TAG, "Failed to save presentation update for down vote");
                }
            }
        });

        return convertView;

    }

}
