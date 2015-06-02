using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Couchbase;
using Couchbase.Core;

namespace WebApplication1.Controllers
{
    public class HomeController : Controller
    {
        private IBucket bucket = null;

        public HomeController()
        {
            bucket = new Workshop.DatabaseConfig().Bucket();
        }

        public ActionResult Index()
        {
            return View();
        }

        [ActionName("Hello")]
        public ActionResult Insert()
        {
            string id = "user::" + Guid.NewGuid().ToString();

            var document = new Document<dynamic>
            {
                Id = id,
                Content = new
                {
                    Hello = "World",
                    Time = DateTime.Now.ToLongTimeString(),
                    Type = "user"
                }
            };

            bucket.Insert(document);

            var loaded = bucket.Get<dynamic>(id);
            return Content(loaded.Value.ToString());
        }

        [ActionName("World")]
        public ActionResult Read()
        {
            var query = bucket.Query<dynamic>(new Couchbase.N1QL.QueryRequest("SELECT COUNT (*) AS count FROM `travel-sample` WHERE type='user'"));

            if (query.Rows.Any())
            {
                return Content(query.Rows.First().ToString());
            }

            return Content(query.Exception.Message);
        }
    }
}