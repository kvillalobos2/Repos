using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Bookmarks.Web.Models
{
    public class UpdateBookmarkModel
    {
        public int BookmarkId { get; set; }

        [Required]
        [StringLength(60, ErrorMessage = "Debe incluir un titulo como maximo de 60 caracteres")]
        public string Title { get; set; }

        [Required]
        [StringLength(30, ErrorMessage = "Debe tener como maximo 30 caracteres")]
        public string Latitude { get; set; }

        [Required]
        [StringLength(30, ErrorMessage = "Debe tener como maximo 30 caracteres")]
        public string Longitude { get; set; }
    }
}
