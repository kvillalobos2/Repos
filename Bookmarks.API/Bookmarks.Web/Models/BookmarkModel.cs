using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookmarks.Web.Models
{
    public class BookmarkModel
    {
        public int BookmarkId { get; set; }
        public string Title { get; set; }

       
        public string Latitude { get; set; }

        public string Longitude { get; set; }
    }
}
