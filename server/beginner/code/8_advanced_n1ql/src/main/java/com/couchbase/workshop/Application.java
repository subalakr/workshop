package com.couchbase.workshop;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public Bucket bucket() {
        return CouchbaseCluster.create().openBucket();
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}
