using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Couchbase;
using Couchbase.Core;

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
        public ActionResult FindAll()
        {
            // TODO: implement me
            var result = string.Empty;

            return Content(result);
        }
    }
}