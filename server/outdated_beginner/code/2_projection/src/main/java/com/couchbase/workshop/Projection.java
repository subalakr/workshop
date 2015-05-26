package com.couchbase.workshop;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryRow;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.s;

public class Projection {
  public static void main(String... args) {
    Bucket bucket = CouchbaseCluster.create().openBucket("travel-sample");

    // Here we will format a nice list of airports. This uses concatenation functions
    Query query = Query.simple(
            select(
                    i("airportname").
                            concat(s(": "))
                            .concat(i("city"))
                            .concat(s(", "))
                            .concat(i("country"))
                            .as("prettyName"),
                    i("faa"))
                    .from(i("travel-sample"))
                    .where(i("airportname").isNotNull())
                    .limit(10));
    System.err.println(query.statement());

    for (QueryRow row : bucket.query(query)) {
      System.out.printf("%s (%s)%n", row.value().getString("prettyName"), row.value().getString("faa"));
    }
  }
}
