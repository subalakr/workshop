using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Couchbase.Core;

namespace WebApplication1.Controllers
{
    public class HomeController : Controller
    {
        private IBucket bucket = null;

        public HomeController()
        {

        }

        public ActionResult Index()
        {
            return View();
        }
        
        [ActionName("Hello")]
        public ActionResult Insert()
        {
            // ToDo
            throw new NotImplementedException();
        }

        [ActionName("World")]
        public ActionResult Read()
        {
            // ToDo
            throw new NotImplementedException();
        }
    }
}