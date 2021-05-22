using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Bookmarks.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bookmarks.Data.Mapping
{
    public class BookmarkMap : IEntityTypeConfiguration<Booksmark>
    {
        public void Configure(EntityTypeBuilder<Booksmark> builder)
        {
            builder.ToTable("bookmark");
            builder.HasKey(b => b.BookmarkId);
            builder.Property(b => b.Title)
                .HasColumnName("title")
                .HasMaxLength(60)
                .IsRequired()
                .IsUnicode(true);
            builder.Property(b => b.Latitude)
                .HasColumnName("latitude")
                .HasMaxLength(30)
                .IsRequired()
                .IsUnicode(true);
            builder.Property(b => b.Longitude)
                .HasColumnName("longitude")
                .HasMaxLength(30)
                .IsRequired()
                .IsUnicode(true);
        }
    }
    
}
