package com.couchbase.travel;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryRow;
import com.couchbase.client.java.query.Statement;
import com.couchbase.travel.CouchbaseClientFactory;


import java.util.ArrayList;
import java.util.List;

import static com.couchbase.client.java.query.Select.*;
import static com.couchbase.client.java.query.dsl.Expression.*;

// This is the route for a single flight.
public class FlightRoute {
  private String fromAirport;
  private String toAirport;
  private String airline;
  private Day day;
  private String flight;
  private String time;
  private String equipment;

  public enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }

  public FlightRoute(JsonObject result) {
    airline = result.getString("airline");
    fromAirport = result.getString("src");
    toAirport = result.getString("dst");
    day = Day.values()[result.getInt("day")];
    flight = result.getString("flight");
    time = result.getString("time");
    equipment = result.getString("equipment");
  }

  public JsonObject toJson() {
    JsonObject json = JsonObject.create();
    json.put("src", fromAirport);
    json.put("dst", toAirport);
    json.put("day", day.ordinal());
    json.put("flight", flight);
    json.put("equipment", equipment);
    json.put("time", time);
    json.put("airline", airline);
    return json;
  }

  // Returns a list of flight routes given a date, a source, and a destination
  public static List<FlightRoute> findRoute(String source, String destination, Day day) {
    // This shows how we can use UNNEST to project a single document into multiple results.
    Bucket bucket = CouchbaseClientFactory.getBucket();
    Statement s = select(
            "r.sourceairport AS src",
            "r.destinationairport AS dst",
            "r.equipment",
            "s.utc AS time",
            "s.flight",
            "s.day")
            .from(i(bucket.name()) + " r")
            .unnest("r.schedule s")
            .where(
                    x("r.type").eq(s("route"))
                            .and(x("r.sourceairport").eq(x("$1")))
                            .and(x("r.destinationairport").eq(x("$2")))
                            .and(x("s.day").eq(x("$3"))));
    JsonArray params = JsonArray.create();

    System.err.println(s);

    params.add(source).add(destination).add(day.ordinal());
    List<FlightRoute> routes = new ArrayList<FlightRoute>();

    for (QueryRow row : bucket.query(Query.parametrized(s, params)).allRows()) {
      routes.add(new FlightRoute(row.value()));
    }
    return routes;
  }

  public static void main(String... args) {
    List<FlightRoute> routes = findRoute("DFW", "RNO", Day.SUNDAY);
    for (FlightRoute route : routes) {
      System.out.println(route.toJson());
    }
  }

  // We could extend this to modify the query to populate the airline field..
}