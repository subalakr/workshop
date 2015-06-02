using System.Web.Mvc;
using Couchbase.Core;

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
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
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