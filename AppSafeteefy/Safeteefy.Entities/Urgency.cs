using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Safeteefy.Entities
{
    public class Urgency
    {
        public int UrgencyId { get; set; }
        [Required(ErrorMessage ="Un Urgency debe tener un título")]
        public string Title { get; set; }
        public string Summary { get; set; }
        //Los números no necesitan el StringhLenght
        [Required(ErrorMessage = "Un Urgency debe tener una latitud")]
        public double Latitude { get; set; }
        [Required(ErrorMessage = "Un Urgency debe tener una longitud")]
        public double Longitude { get; set; }
        public DateTime ReportedAt { get; set; }

        //ForeingKey
        public int GuardianId { get; set; }
        public Guardian Guardian { get; set; }
    }
}
