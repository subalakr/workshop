package com.couchbase.workshop;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryRow;
import com.couchbase.client.java.query.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;

import java.util.ArrayList;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.*;
import static com.couchbase.client.java.query.dsl.functions.MetaFunctions.meta;

@RestController
@RequestMapping("/airport")
public class AirportSearch {
  private final Bucket bucket;

  @Autowired
  public AirportSearch(Bucket bucket) {
    this.bucket = bucket;
  }

  final private String[] FIELDS = { "airportname", "faa", "country", "city" };

  @RequestMapping("/by_faa")
  String findByFaa(@RequestParam String faa) {
    // SELECT aiportname, faa, country, city FROM `travel-sample` WHERE type = "airport" AND faa = $faa LIMIT 1
    Statement statement = select(FIELDS)
            .from(i(bucket.name()))
            .where(x("type").eq(s("airport")).and(x("faa")).eq(x("$1"))).limit(1);

    JsonArray params = JsonArray.create().add(faa);

    System.err.println(statement);
    QueryResult result = bucket.query(Query.parametrized(statement, params));
    for (QueryRow row : result.allRows()) {
      return row.value().toString();
    }
    return null;
  }

  @RequestMapping("/by_city")
  String findAllByCity(@RequestParam String city) {
    Statement statement = select(FIELDS).from(i(bucket.name()))
            .where(x("type").eq(s("airport")).and(x("city").eq(x("$city"))));
    JsonObject params = JsonObject.create();
    params.put("$city", city);

    JsonArray results = JsonArray.create();
    for (QueryRow row : bucket.query(Query.parametrized(statement, params))) {
      results.add(row.value());
    }

    return results.toString();
  }
}
