using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Couchbase;
using Couchbase.Configuration.Client;
using Couchbase.Core;

namespace WebApplication1.Workshop
{
    public class DatabaseConfig
    {
        private string hostName = "http://127.0.0.1";

        private string bucketName = "travel-sample";

        private string password = "";

        public Cluster Cluster()
        {
            var config = new ClientConfiguration
            {
                Servers = new List<Uri>
                {
                    new Uri(hostName +":8091/pools"),
                },
                UseSsl = false,
                BucketConfigs = new Dictionary<string, BucketConfiguration>
                {
                    {
                        "default",
                        new BucketConfiguration
                        {
                            BucketName = bucketName,
                            UseSsl = false,
                            Password = password,
                            PoolConfiguration = new PoolConfiguration
                            {
                                MaxSize = 5,
                                MinSize = 1
                            }
                        }
                    }
                }
            };

           return new Cluster(config);
        }

        public IBucket Bucket() {
            return Cluster().OpenBucket(bucketName, password);
        }
    }
}