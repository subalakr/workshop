using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;
using Couchbase;
using Couchbase.Core;
using Couchbase.N1QL;

namespace WebApplication1.Controllers
{
    public class FlightpathController : Controller
    {
        private IBucket bucket = null;

        public FlightpathController()
        {
            bucket = new Workshop.DatabaseConfig().Bucket();
        }

        [ActionName("all")]
        public ActionResult FindAll(string from, string to)
        {
            /*
            QueryResult result = bucket.query(Query.simple(
           select("a.name", "s.flight", "s.utc", "r.equipment")
               .from(i(bucket.name()).as("r"))
               .unnest("r.schedule s")
               .join(i(bucket.name()).as("a").toString()).onKeys("r.airlineid")
               .where(x("r.sourceairport").eq(s(from)).and(x("r.destinationairport").eq(s(to))))
               .orderBy(asc("s.utc"))
       ));


        List<FlightPath> flightPaths = new ArrayList<>();
        for (QueryRow rows : result) {
            flightPaths.add(FlightPath.create(rows.value(), from, to));
        }
        return flightPaths;
            */

            var query = "SELECT a.name, s.flight, s.utc, r.equipment from `" +
                bucket.Name + "` AS r UNNEST r.schedule s JOIN `" +
                bucket.Name + "` AS a ON KEYS r.airlineid WHERE r.sourceairport = '" + 
                from.ToUpper() + "' AND r.destinationairport = '" + 
                to.ToUpper() + "' ORDER BY s.utc ASC";


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