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
    public static final String TYPE = "presentation";

    private Date createdAt;
    private int upVotes;
    private int downVotes;
    private String title;
    private Document sourceDocument;
    private Database database;

    public static Query findAll(Database database) {

        com.couchbase.lite.View view = database.getView("presentations");
        if (view.getMap() == null) {
            Mapper mapper = new Mapper() {
                @Override
                public void map(Map<String, Object> doc, Emitter emitter) {
                    String type = (String) doc.get("type");
                    if (type.equals(Presentation.TYPE)) {
                        emitter.emit(doc.get("created_at"), doc);
                    }
                }
            };
            view.setMap(mapper, "2");
        }
        return view.createQuery();
    }

    public static Presentation from(Document document) {
        Presentation presentation = new Presentation(document.getDatabase());
        if (document.getProperty("title") != null) {
            presentation.setTitle((String) document.getProperty("title"));
        }
        if (document.getProperty("created_at") != null){
            long createdAtL = 0;
            Object createdAt = document.getProperty("created_at");
            if (createdAt instanceof Double){
                createdAtL = ((Double) createdAt).longValue();
            }
            if (createdAt instanceof Long){
                createdAtL = (Long) createdAt;
            }
            presentation.setCreatedAt(new Date(createdAtL));
        }
        if (document.getProperty("down_votes") != null) {
            presentation.setDownVotes((Integer) document.getProperty("down_votes"));
        }
        if (document.getProperty("up_votes") != null){
            presentation.setUpVotes((Integer) document.getProperty("up_votes"));
        }
        presentation.setSourceDocument(document);
        return presentation;
    }

    public Presentation(Database database) {
        this.createdAt = new Date();
        this.upVotes = 0;
        this.downVotes = 0;
        this.title = "";
        this.database = database;
    }

    public void save() throws CouchbaseLiteException {
        Map<String, Object> properties = new HashMap<String, Object>();
        Document document;
        if (sourceDocument == null) {
            document = database.createDocument();
        } else {
            document = sourceDocument;
            properties.putAll(sourceDocument.getProperties());
        }
        properties.put("type", TYPE);
        properties.put("created_at", createdAt.getTime());
        properties.put("title", title);
        properties.put("up_votes", upVotes);
        properties.put("down_votes", downVotes);
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

    public void setSourceDocument(Document document) {
        this.sourceDocument = document;
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
