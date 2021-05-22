using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Safeteefy.Web.Models
{
    public class PostGuardianModel
    {
        [Required(ErrorMessage = "Un Guardian debe tener un usuario")]
        [StringLength(30, ErrorMessage ="Un Guardian debe tener como máximo 30 caracteres")]
        public string Username { get; set; }
        [Required]
        [StringLength(30, ErrorMessage = "Un Guardian debe como máximo 30 caracteres")]
        public string Email { get; set; }
        [StringLength(60, ErrorMessage = "Un Guardian debe como máximo 60 caracteres")]
        public string FirstName { get; set; }
        [StringLength(60, ErrorMessage = "Un Guardian debe como máximo 60 caracteres")]
        public string LastName { get; set; }
        [Required(ErrorMessage = "Un Guardian debe tener un genero")]
        public string Gender { get; set; }
        public string Address { get; set; }
    }
}
