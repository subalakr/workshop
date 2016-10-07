using System.Configuration;
using System.Threading.Tasks;
using System.Web.Http;
using Couchbase;
using Couchbase.Core;
using Couchbase.N1QL;
using workshop_dotnet.Models;

namespace workshop_dotnet.Controllers
{
    [RoutePrefix("api")]
    public class PersonController : ApiController
    {
        private static readonly string BucketName = ConfigurationManager.AppSettings.Get("couchbaseBucket");
        private readonly IBucket _bucket = ClusterHelper.GetBucket(BucketName);

        [HttpGet]
        [Route("get/{id?}")]
        public async Task<IHttpActionResult> Get(string id = null)
        {
            if (string.IsNullOrEmpty(id))
            {
                return BadRequest();
            }

            var result = await _bucket.GetAsync<dynamic>(id);
            if (!result.Success)
            {
                return InternalServerError();
            }

            return Ok(result.Value);
        }

        [HttpGet]
        [Route("getAll")]
        public async Task<IHttpActionResult> Getall()
        {
            var query = new QueryRequest("SELECT * FROM $1 WHERE type = $2")
                .AddPositionalParameter(BucketName, typeof(Person).Name)
                .ScanConsistency(ScanConsistency.RequestPlus);

            var result = await _bucket.QueryAsync<Person>(query);
            if (!result.Success)
            {
                return InternalServerError();
            }

            return Ok(result.Rows);
        }

        [HttpPost]
        [Route("save")]
        public async Task<IHttpActionResult> Save([FromBody] Person person)
        {
            if (person == null)
            {
                return BadRequest();
            }

            var result = await _bucket.UpsertAsync(person.Id, person);
            if (!result.Success)
            {
                return InternalServerError();
            }

            return Ok();
        }

        [HttpPost]
        [Route("delete")]
        public async Task<IHttpActionResult> Delete([FromBody] Person person)
        {
            if (string.IsNullOrEmpty(person?.Id))
            {
                return BadRequest();
            }

            var result = await _bucket.RemoveAsync(person.Id);
            if (!result.Success)
            {
                return InternalServerError();
            }

            return Ok();
        }
    }
}
