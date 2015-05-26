package com.couchbase.updownapp;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

public class CouchbaseConnectionFactory {

  private static AsyncBucket defaultBucket = null;
  private static Bucket defaultBucketSync = null;
  private static CouchbaseCluster defaultCluster = null;

  public static AsyncBucket getDefaultConnection() {
    if(defaultBucket == null) {
      defaultBucket = getDefaultConnectionSync().async();
    }
    return defaultBucket;
  }

  public static Bucket getDefaultConnectionSync() {
    if(defaultCluster == null) {
      defaultCluster = CouchbaseCluster.create(DefaultCouchbaseEnvironment
              .builder()
              .queryEnabled(true)
              .build());
    }
    if(defaultBucketSync == null) {
      defaultBucketSync = defaultCluster.openBucket();
    }
    return defaultBucketSync;
  }
}
