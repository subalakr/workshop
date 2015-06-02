using System.Web.Mvc;
using Couchbase.Core;
using Couchbase.N1QL;
using System.Linq;
using System;
using System.Text;

namespace WebApplication1.Controllers
{
    public class AirportController : Controller
    {
        private IBucket bucket = null;

        public AirportController()
        {
            bucket = new Workshop.DatabaseConfig().Bucket();
        }



        [ActionName("allNames")]
        public ActionResult allAirportNames()
        {
            string query = "SELECT airportname FROM `" + bucket.Name
            + "` WHERE type = 'airport' ORDER BY airportname ASC";

            var request = new QueryRequest().Statement(query);
            var queryResult = bucket.Query<dynamic>(request);

            if (queryResult.Rows.Any())
            {
                var sb = new StringBuilder();
                foreach (var row in queryResult.Rows)
                {
                    sb.Append(row.ToString());
                    sb.Append("\n\r");
                }

                return Content(sb.ToString());
            }

            return Content("No rows");
        }

        /**
         * Returns airport names which start with the given prefix.
         *
         * @param prefix the prefix they need to start with
         * @return the list of airport names.
         */
        //@RequestMapping("/prefixedBy")
        [ActionName("prefixedBy")]
        public ActionResult AirportNamesStartingWith(string prefix)
        {
            /*
            QueryResult result = bucket.query(Query.simple(
                select("airportname")
                    .from(i(bucket.name()))
                    .where(
                        x("type").eq(s("airport")).and(x("airportname").like(s(prefix.toUpperCase() + "%")))
                    )
                    .orderBy(asc("airportname"))
            ));

            List<String> airports = new ArrayList<>();
            for (QueryRow row : result)
            {
                airports.add(row.value().getString("airportname"));
            }

            return airports;
            */

            string query = "SELECT airportname FROM `" + bucket.Name
            + "` WHERE type = 'airport' AND airportname LIKE '" + prefix + "%' ORDER BY airportname ASC";

            var request = new QueryRequest().Statement(query);
            var queryResult = bucket.Query<dynamic>(request);

            if (queryResult.Rows.Any())
            {
                var sb = new StringBuilder();
                foreach (var row in queryResult.Rows)
                {
                    sb.Append(row.ToString());
                    sb.Append("\n\r");
                }

                return Content(sb.ToString());
            }

            return Content("No rows");
        }

        /**
         * Returns the full airport information by its FAA code (like LAX or SFO).
         *
         * @param faa the faa code.
         * @return the airport info, or 404 if not found.
         */
        //@RequestMapping("/byFaa")
        [ActionName("byFaa")]
        public ActionResult byFaa(string faa)
        {
            /*
            QueryResult result = bucket.query(Query.simple(
                select(i(bucket.name()) + ".*")
                    .from(i(bucket.name()))
                    .where(
                        x("type").eq(s("airport")).and(x("faa").eq(s(faa)))
                    )
            ));

            List<QueryRow> rows = result.allRows();

            if (rows.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(
                result.allRows().get(0).value().toMap(),
                HttpStatus.OK
            );
            */

            string query = "SELECT * FROM `" + bucket.Name
            + "` WHERE type = 'airport' AND faa = '" + faa.ToUpper() + "'";

            var request = new QueryRequest().Statement(query);
            var queryResult = bucket.Query<dynamic>(request);

            if (queryResult.Rows.Any())
            {
                var sb = new StringBuilder();
                foreach (var row in queryResult.Rows)
                {
                    sb.Append(row.ToString());
                    sb.Append("\n\r");
                }

                return Content(sb.ToString());
            }

            return Content(queryResult.Exception != null ? queryResult.Exception.Message :  "No rows");
        }

        /**
         * Returns all routes by the given airline.
         *
         * @param airline the airline to check.
         * @return the list of routes.
         */
        //@RequestMapping("/routes")

        [ActionName("routes")]
        public ActionResult routesByAirline(string airline)
        {
            /*
            QueryResult result = bucket.query(Query.simple(
                select(
                    arrayLength("schedule").as("num_routes"),
                    x("sourceairport").as("source"),
                    x("destinationairport").as("dest"),
                    x("airline")
                )
                    .from(i(bucket.name()))
                    .where(
                        x("type").eq(s("route")).and(x("airline").eq(s(airline)))
                    )
                    .orderBy(asc("source"))
            ));


            List<String> destinations = new ArrayList<>();
            for (QueryRow row : result)
            {
                JsonObject value = row.value();
                destinations.add(
                    value.getString("source")
                        + " -> " + value.getString("dest")
                        + ": " + value.getLong("num_routes")
                );
            }

            return new ResponseEntity<List<String>>(destinations, HttpStatus.OK);
            */

            throw new NotImplementedException();

            var query = "";

            var request = new QueryRequest().Statement(query);
            var queryResult = bucket.Query<dynamic>(request);

            if (queryResult.Rows.Any())
            {
                var sb = new StringBuilder();
                foreach (var row in queryResult.Rows)
                {
                    sb.Append(row.ToString());
                    sb.Append("\n\r");
                }

                return Content(sb.ToString());
            }

            return Content(queryResult.Exception != null ? queryResult.Exception.Message : "No rows");
        }
    }
}