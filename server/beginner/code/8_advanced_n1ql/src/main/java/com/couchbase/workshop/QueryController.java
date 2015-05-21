package com.couchbase.workshop;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryParams;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    private final Bucket bucket;

    @Autowired
    public QueryController(final Bucket bucket) {
        this.bucket = bucket;
    }

    @RequestMapping("/readYourWrite")
    public Integer readYourWrite() {
        bucket.upsert(
            JsonDocument.create("doc-" + System.nanoTime(), JsonObject.create().put("type", "counter"))
        );

        QueryResult query = bucket.query(Query.simple(
            "SELECT COUNT(*) as cnt FROM default WHERE type = 'counter'",
            QueryParams.build().consistency(ScanConsistency.REQUEST_PLUS)
        ));

        return query.allRows().get(0).value().getInt("cnt");
    }

    @RequestMapping("/prepared")
    public List<String> prepared() {

        return null;
    }

    @RequestMapping("/parameterized")
    public List<String> parameterized() {

        return null;
    }

}
