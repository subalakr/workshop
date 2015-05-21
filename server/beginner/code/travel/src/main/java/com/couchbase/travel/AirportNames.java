package com.couchbase.travel;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryRow;

public class AirportNames {
  // This simple application will print out airport names
  public static void main(String... args) {
    Bucket bucket = CouchbaseClientFactory.getBucket();
    for (QueryRow row : bucket.query(Query.simple("SELECT airportname FROM `travel-sample` LIMIT 10"))) {
      System.out.println(row.value().getString("airportname"));
    }

    // This will also show fields that don't actually have a bucket name.
    for (QueryRow row : bucket.query(Query.simple("SELECT airportname FROM `travel-sample` WHERE airportname IS NOT NULL LIMIT 10"))) {
      System.out.println(row.value().getString("airportname"));
    }
  }
}
