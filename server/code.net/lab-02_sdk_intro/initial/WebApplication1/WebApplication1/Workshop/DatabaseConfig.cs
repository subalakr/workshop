using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Couchbase;
using Couchbase.Core;

namespace WebApplication1.Workshop
{
    public class DatabaseConfig
    {
        private string hostName = null;

        private string bucketName = null;

        private string password = null;

        public Cluster Cluster()
        {
            // ToDo
            throw new NotImplementedException();
        }

        public IBucket Bucket() {
            // ToDo
            throw new NotImplementedException();
        }
    }
}