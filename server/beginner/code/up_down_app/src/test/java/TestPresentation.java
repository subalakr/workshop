import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.error.CASMismatchException;
import com.couchbase.updownapp.CouchbaseConnectionFactory;
import com.couchbase.updownapp.Presentation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Date;
import static org.hamcrest.CoreMatchers.instanceOf;


import static org.junit.Assert.*;

public class TestPresentation {

    private static Presentation testPresentation;

    @AfterClass
    public static void tearDown() {
        // flush the bucket to make sure we have a clean state again
        Bucket bucket = CouchbaseConnectionFactory.getDefaultConnection();
        bucket.bucketManager().toBlocking().single().flush().toBlocking().single();
    }

    @BeforeClass
    public static void setup() {
        testPresentation = new Presentation();
        testPresentation.setTitle("test-presentation");
        testPresentation.save().toBlocking().single();
    }

    @Test
    public void testPresentationInitialiseWithDefaults() {
        Presentation presentation = new Presentation();
        assertEquals(presentation.getTitle(), "");
        assertEquals(presentation.getUpVotes(), 0);
        assertEquals(presentation.getDownVotes(), 0);
        assertThat(presentation.getCreatedAt(), instanceOf(Date.class));
    }

    @Test
    public void testPresentationSave() {
        Presentation presentation = new Presentation();
        String title = "Awesome stuff about Couchbase";
        presentation.setTitle(title);
        Presentation presentation1 = presentation.save().toBlocking().single();
        assertEquals(presentation1.getTitle(), title);
    }

    @Test
    public void testPresentationFind() {
        Presentation presentation = Presentation.find(testPresentation.getKey()).toBlocking().single();
        assertEquals(presentation.getTitle(), testPresentation.getTitle());
    }

    @Test
    public void testPresentationUpdate() {
        Presentation presentation = Presentation.find(testPresentation.getKey()).toBlocking().single();
        presentation.setUpVotes(1);
        Presentation presentation1 = presentation.save().toBlocking().single(); // success saving
        presentation.setUpVotes(99);
        try {
            presentation.save().toBlocking().single(); // should fail saving again
            assertTrue(false);
        } catch (CASMismatchException e) {
            assertTrue(true);
        }
    }

}
