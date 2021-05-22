using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Safeteefy.Web.Models
{
    public class UrgencyModel
    {
        public int UrgencyId { get; set; }
        public string Title { get; set; }
        public string Summary { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public DateTime ReportedAt { get; set; }

        // se agrega el guardianId 
        public int GuardianId { get; set; }

    }
}
