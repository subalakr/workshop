package com.couchbase.travel;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.Query;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryRow;
import com.couchbase.client.java.query.Statement;

import java.util.ArrayList;
import java.util.List;

import static com.couchbase.client.java.query.Select.*;
import static com.couchbase.client.java.query.dsl.Expression.*;

public class Airport {
  private String name;
  private String faaCode;
  private String country;
  private String city;

  public Airport(JsonObject json) {
    name = json.getString("airportname");
    faaCode = json.getString("faa");
    country = json.getString("country");
    city = json.getString("city");
  }

  public JsonObject toJson() {
    JsonObject json = JsonObject.create();
    json.put("airportname", name);
    json.put("faa", faaCode);
    json.put("country", country);
    json.put("city", city);
    return json;
  }

  public String getName() {
    return name;
  }

  public String getFaaCode() {
    return faaCode;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  @Override
  public String toString() {
    return toJson().toString();
  }

  // Finds an airport by its given code
  public static Airport find(String faaCode) {
    Bucket bucket = CouchbaseClientFactory.getBucket();
    // SELECT aiportname, faa, country, city FROM `travel-sample` WHERE type = "airport" AND faa = $faa LIMIT 1
    Statement statement = select("airportname, faa, country, city").from(i(bucket.name()))
            .where(x("type").eq(s("airport")).and(x("faa")).eq(x("$faa"))).limit(1);

    JsonObject params = JsonObject.create();
    params.put("$faa", faaCode);

    System.err.println(statement);
    QueryResult result = bucket.query(Query.parametrized(statement, params));
    for (QueryRow row : result.allRows()) {
      return new Airport(row.value());
    }
    return null;
  }

  public static List<Airport> findByCity(String city) {
    Bucket bucket = CouchbaseClientFactory.getBucket();
    Statement statement = select("airportname, faa, country, city").from(i(bucket.name()))
            .where(x("type").eq(s("airport")).and(x("city").eq(x("$city"))));
    JsonObject params = JsonObject.create();
    params.put("$city", city);
    QueryResult result = bucket.query(Query.parametrized(statement, params));
    ArrayList<Airport> airports = new ArrayList<Airport>();
    for (QueryRow row : result.allRows()) {
      airports.add(new Airport(row.value()));
    }
    return airports;
  }

  public static void main(String... args) {
    System.out.println(find("RNO"));
    System.out.println(find("LAX"));

    // Find all airports in dallas:
    for (Airport airport : findByCity("Dallas")) {
      System.out.println(airport);
    }
  }
}