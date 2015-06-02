using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Couchbase;
using Couchbase.Core;
using Couchbase.N1QL;

namespace WebApplication1.Controllers
{
    public class QueryController : Controller
    {
        private IBucket bucket = null;
        private QueryPlan queryPlan = null;

        public QueryController()
        {
            bucket = new Workshop.DatabaseConfig().Bucket();

            queryPlan = null; // implement me
        }

        [ActionName("readYourWrite")]
        public ActionResult ReadYourWrite()
        {

            // IMPLEMENT ME

            return Content("0");
        }

        [ActionName("prepared")]
        public ActionResult Prepared()
        {
            var items = new List<string>();
            // IMPLEMENT ME

            return Content("");
        }

        [ActionName("parameterized")]
        public ActionResult parameterized(string faa)
        {
            // IMPLEMENT ME

            return Content("");
        }
    }
}