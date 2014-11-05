package com.couchbase.devxp.updownvote.test;

import com.couchbase.devxp.updownvote.Application;
import com.couchbase.devxp.updownvote.Presentation;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.QueryEnumerator;

import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public Application application;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        application = getApplication();
    }

    @Override
    protected void tearDown() throws Exception {
        application.getDatabase().delete();
        super.tearDown();
    }

    public void testDatabaseConnection () {
        assertNotNull(application.getDatabase());
    }

    public void testCreatePresentation () {
        Presentation presentation = new Presentation(application.getDatabase());
        presentation.setTitle("I am a test");
        try {
            presentation.save();
        } catch (CouchbaseLiteException e) {
            fail();
        }
    }

    public void testFindAndUpdatePresentation () {
        String title = "I am a test";
        Presentation presentation = new Presentation(application.getDatabase());
        presentation.setTitle(title);
        try {
            presentation.save();
            QueryEnumerator presentations = Presentation.findAll(application.getDatabase()).run();
            Presentation loaded = Presentation.from(presentations.getRow(0).getDocument());
            assertEquals(loaded.getTitle(), title);
        } catch (CouchbaseLiteException e) {
            fail();
        }
    }
}