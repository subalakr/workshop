package com.couchbase.workshop;

import com.couchbase.client.java.document.json.JsonObject;
import java.util.Arrays;
import java.util.List;

/**
 * .
 *
 * @author Michael Nitschinger
 */
public class FlightPath {

    private final String name;
    private final String flight;
    private final List<String> equipment;
    private final String utc;
    private final String source;
    private final String destination;

    private FlightPath(String name, String flight, List<String> equipment, String utc, String source, String destination) {
        this.name = name;
        this.flight = flight;
        this.equipment = equipment;
        this.utc = utc;
        this.source = source;
        this.destination = destination;
    }

    public static FlightPath create(JsonObject input, String from, String to) {
        return new FlightPath(
            input.getString("name"),
            input.getString("flight"),
            Arrays.asList(input.getString("equipment").split(" ")),
            input.getString("utc"),
            from,
            to
        );
    }

    public String getName() {
        return name;
    }

    public String getFlight() {
        return flight;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public String getUtc() {
        return utc;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
