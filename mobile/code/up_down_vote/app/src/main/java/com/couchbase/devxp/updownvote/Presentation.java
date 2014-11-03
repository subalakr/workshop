package com.couchbase.devxp.updownvote;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by phil on 03/11/14.
 */
public class Presentation {
    public final String TYPE = "presentation";

    private Date createdAt;
    private int upVotes;
    private int downVotes;
    private String title;
    private Database database;

    public Presentation(Database database) {
        this.createdAt = new Date();
        this.upVotes = 0;
        this.downVotes = 0;
        this.title = "";
        this.database = database;
    }

    public void save() throws CouchbaseLiteException {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("type", TYPE);
        properties.put("created_at", createdAt.getTime());
        properties.put("title", title);
        properties.put("up_votes", upVotes);
        properties.put("down_votes", downVotes);
        Document document = database.createDocument();
        try {
            document.putProperties(properties);
        } catch(CouchbaseLiteException e) {
            Log.e("PRESENTATION", "Failed to save");
            throw e;
        }
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }
}
