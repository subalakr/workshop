package com.couchbase.updownapp;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.CouchbaseCluster;

public class CouchbaseConnectionFactory {

  private static AsyncBucket defaultBucket = null;
  private static CouchbaseCluster cluster = null;

  public static AsyncBucket getDefaultConnection() {
    if(defaultBucket == null) {
      // open the bucket in async mode, because we want to use Rx everywhere
      defaultBucket = getCluster().openBucket().async();
    }
    return defaultBucket;
  }

  public static CouchbaseCluster getCluster() {
    if(cluster == null) {
      cluster =  CouchbaseCluster.create();
    }
    return cluster;
  }

}
