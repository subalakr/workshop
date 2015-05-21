import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryRow;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.x;

public class SingleFieldQuery {
  public static void main(String... args) {
    Bucket bucket = CouchbaseCluster.create().openBucket("travel-sample");
    for (QueryRow row : bucket.query(Query.simple("SELECT airportname FROM `travel-sample` LIMIT 10"))) {
      System.out.println(row.value().getString("airportname"));
    }

    // The i() escapes the "identifier", the x() begins an expression.
    // Here we ensure only those documents with a non-null airportname will be selected
    Query query = Query.simple(
            select("airportname").from(i("travel-sample"))
                    .where(x("airportname").isNotNull())
                    .limit(10));
    for (QueryRow row : bucket.query(query)) {
      System.out.println(row.value().getString("airportname"));
    }
  }
}
