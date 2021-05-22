using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Safeteefy.Web.Models
{
    public class GuardianModel
    {
        public int GuardiansId { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Gender { get; set; }
        public string Address { get; set; }
    }
}
