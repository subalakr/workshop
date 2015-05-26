package com.couchbase.workshop;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryRow;
import com.couchbase.client.java.query.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.*;
import static com.couchbase.client.java.query.dsl.functions.MetaFunctions.meta;

@RestController
@RequestMapping("/schedule")
public class FlightSchedules {
  private final Bucket bucket;

  @Autowired
  public FlightSchedules(final Bucket bucket) {
    this.bucket = bucket;
  }

  @RequestMapping(value = "/find", produces = "application/json")
  public String find(@RequestParam String from, @RequestParam String to, @RequestParam int day) {
    Statement s = select(
            x("r.sourceairport").as("src"),
            x("r.destinationairport").as("dst"),
            x("r.equipment"),
            x("s.utc").as("time"),
            x("s.flight"),
            x("s.day"),
            x("META(r).id").as("routeId"))
            .from(i(bucket.name()).as(" r"))
            .unnest("r.schedule").as("s")
            .where(
                    x("r.type").eq(s("route"))
                            .and(x("r.sourceairport").eq(x("$1")))
                            .and(x("r.destinationairport").eq(x("$2")))
                            .and(x("s.day").eq(x("$3"))));
    JsonArray params = JsonArray.create();

    System.err.println(s);

    params.add(from).add(to).add(day);
    JsonArray schedules = JsonArray.create();
    for (QueryRow row : bucket.query(Query.parametrized(s, params)).allRows()) {
      schedules.add(row.value());
    }
    return schedules.toString();
  }

  @RequestMapping(value = "/detail", produces = "application/json")
  public String detail(@RequestParam String id) {
    Statement s = select("r.*", "airline")
            .from(i(bucket.name()).as("r"))
            .useKeys(x("$1"))
            .nest(i(bucket.name()).toString()).as("airline")
            .onKeys("r.airlineid");

    JsonArray params = JsonArray.create().add(id);

    System.err.println(s);
    for (QueryRow row : bucket.query(Query.parametrized(s, params)).allRows()) {
      return row.value().toString();
    }
    return null;
  }
}
