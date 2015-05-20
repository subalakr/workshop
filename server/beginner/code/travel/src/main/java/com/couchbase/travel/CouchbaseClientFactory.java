package com.couchbase.travel;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.Statement;
import static com.couchbase.client.java.query.Select.*;
import static com.couchbase.client.java.query.dsl.Expression.*;


public class CouchbaseClientFactory {
  static private CouchbaseCluster cluster = null;
  static private Bucket travelBucket = null;
  static public final String BUCKET_NAME = "travel-sample";

  private static void createIndex() {
    JsonArray array = JsonArray.create();
    Statement s = select("keyspace_id", "name").from("system:indexes")
            .where(x("keyspace_id").eq(x("$1")).and(x("name").eq(s("#primary"))));
    array.add(BUCKET_NAME);
    QueryResult res = travelBucket.query(Query.parametrized(s, array));
    if (!res.finalSuccess()) {
      throw new RuntimeException("Couldn't query indexes");
    }

    if (!res.allRows().isEmpty()) {
      // Index exists
      return;
    }

    res = travelBucket.query(Query.simple(String.format("CREATE PRIMARY INDEX ON `%s` USING GSI", BUCKET_NAME)));
    if (!res.finalSuccess()) {
      throw new RuntimeException("Couldn't create index");
    }
  }

  public static Bucket getBucket() {
    if (travelBucket == null) {
      cluster = CouchbaseCluster.create(DefaultCouchbaseEnvironment.builder()
              .queryEnabled(true)
              .build());
      travelBucket = cluster.openBucket(BUCKET_NAME);

      // Issue the query to create the index
      createIndex();
    }
    return travelBucket;
  }
}
