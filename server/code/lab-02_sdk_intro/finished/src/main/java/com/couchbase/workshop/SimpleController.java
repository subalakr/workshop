/**
 * Copyright (c) 2015 Couchbase, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */
package com.couchbase.workshop;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.UUID;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.s;
import static com.couchbase.client.java.query.dsl.Expression.x;
import static com.couchbase.client.java.query.dsl.functions.AggregateFunctions.count;

@RestController
@RequestMapping("/")
public class SimpleController {

    private final Bucket bucket;

    @Autowired
    public SimpleController(Bucket bucket) {
        this.bucket = bucket;
    }

    @RequestMapping("/hello")
    public Map<String, Object> insert() {
        String id = "user::" + UUID.randomUUID().toString();
        JsonObject content = JsonObject
            .create()
            .put("hello", "world")
            .put("nanotime", System.nanoTime())
            .put("type", "user");
        JsonDocument document = JsonDocument.create(id, content);

        bucket.insert(document);

        JsonDocument loaded = bucket.get(id);
        return loaded.content().toMap();
    }

    @RequestMapping("/world")
    public Integer read() {
        QueryResult result = bucket.query(Query.simple(
            select(count("*").as("count"))
                .from(i(bucket.name()))
                .where(x("type").eq(s("user")))
        ));

        return result.allRows().get(0).value().getInt("count");
    }


}
