using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class FlightPath
    {
        private string Name { get; set; }
        private string Flight { get; set; }
        private List<string> Equipment { get; set; }
        private string Utc { get; set; }
        private string Source { get; set; }
        private string Destination { get; set; }
    }
}
