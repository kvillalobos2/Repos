using Bookmarks.Data.Mapping;
using Bookmarks.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bookmarks.Data
{
    public class DbContextBookmarks: DbContext
    {
        public DbContextBookmarks(DbContextOptions<DbContextBookmarks>options):base (options)
        {

        }
        public DbSet<Booksmark> Bookmarks { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.ApplyConfiguration(new BookmarkMap());
        }

    }
}
