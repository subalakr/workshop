using System.Web.Mvc;
using Couchbase.Core;
using Couchbase.N1QL;
using System.Linq;
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

        [ActionName("all")]
        public ActionResult FindAll()
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

        [ActionName("allNames")]
        public ActionResult allAirportNames()
        {
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
        }

        [ActionName("prefixedBy")]
        public ActionResult airportNamesStartingWith(string prefix)
        {
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
        }

        [ActionName("byFaa")]
        public ActionResult byFaa(string faa)
        {
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
        }

        [ActionName("routes")]
        public ActionResult routesByAirline(string airline)
        {
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
        }
    }
}