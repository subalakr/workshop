package com.couchbase.updownapp;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public class CouchbaseConnectionFactory {

  private static AsyncBucket defaultBucket = null;
  private static Bucket defaultBucketSync = null;

  public static AsyncBucket getDefaultConnection() {
    if(defaultBucket == null) {
      defaultBucket = getDefaultConnectionSync().async();
    }
    return defaultBucket;
  }

  public static Bucket getDefaultConnectionSync() {
    if(defaultBucketSync == null) {
      defaultBucketSync = CouchbaseCluster.create().openBucket();
    }
    return defaultBucketSync;
  }
}
