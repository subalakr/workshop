package com.couchbase.updownapp;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public class CouchbaseConnectionFactory {

    private static Bucket defaultBucket = null;
    private static CouchbaseCluster cluster = null;

    public static Bucket getDefaultConnection() {
        if(defaultBucket == null) {
           // TODO should we return the observable here?
           defaultBucket = getCluster().openBucket("default").toBlocking().single();
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
