using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Common.Logging;
using Couchbase.Core;
using WebApplication1.Models;

// should probably be implemented in => public class MvcApplication : System.Web.HttpApplication
namespace WebApplication1.Workshop
{
    public class IndexChecker
    {
        private static ILog LOGGER = LogManager.GetLogger("LOGGER");
        private object applicationContext;
        private IBucket bucket;

        public IndexChecker(IBucket bucket)
        {
            this.bucket = bucket;
        }

        public void afterPropertiesSet()
        {
            ensureIndexes();
        }

        public void setApplicationContext(object ctx)
        {
            this.applicationContext = ctx;
        }

        private void ensureIndexes()
        {
            LOGGER.Info(string.Format("Loading current Indexes for bucket {0}", bucket.Name));

            List<Index> current = currentIndexes();

            LOGGER.Info(string.Format("Found {0} indexes on bucket {1}", current.Count, bucket.Name));
            LOGGER.Debug(string.Format("Found Indexes: {0}", current));

            List<Index> wanted = wantedIndexes();

            LOGGER.Info(string.Format("Found {0} indexes to potentially create on bucket {1}", wanted.Count, bucket.Name));
            LOGGER.Debug(string.Format("Wanted Indexes: {0}", wanted));

            wanted.RemoveAll(item => current.Contains(item));

            LOGGER.Info(string.Format("Creating {0} new indexes.", wanted.Count));

            createIndexes(wanted);
        }

        private List<Index> currentIndexes()
        {
            // IMPLEMENT ME

            return null;
        }

        private List<Index> wantedIndexes()
        {
            //Resource indexes = applicationContext.getResource("classpath:wanted_indexes.csv");

            // IMPLEMENT ME

            return null;
        }

        private void createIndexes(List<Index> toCreate)
        {
            // IMPLEMENT ME
        }
    }
}