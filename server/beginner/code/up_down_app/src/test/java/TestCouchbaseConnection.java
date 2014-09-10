import com.couchbase.updownapp.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCouchbaseConnection {
    @Test
    public void testGetsConnection() {
        assertNotNull(CouchbaseConnectionFactory.getDefaultConnection());
    }
}
