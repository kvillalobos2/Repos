using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Safeteefy.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Safeteefy.Data.Mapping
{
    public class UrgencyMap:IEntityTypeConfiguration<Urgency>
    {
        public void Configure(EntityTypeBuilder<Urgency> builder)
        {
            builder.ToTable("Urgency")
                .HasKey(u => u.UrgencyId);
            builder.Property(u => u.UrgencyId)
                .HasColumnName("urgencyId");
            builder.Property(u => u.Title)
                .HasColumnName("title")
                .IsRequired()
                .IsUnicode(false);
            builder.Property(u => u.Summary)
                .HasColumnName("summary")
                .IsUnicode(false);
            builder.Property(u => u.Latitude)
                .HasColumnName("latitude")
                .IsRequired()
                //Para que tenga acceso a la base de datos del double
                .UsePropertyAccessMode(PropertyAccessMode.Field);
            //O sino -> .HasColumnType("decimal(11,2)");
            builder.Property(u => u.Longitude)
                .HasColumnName("longitude")
                .IsRequired()
                .UsePropertyAccessMode(PropertyAccessMode.Field);
            builder.Property(u => u.ReportedAt)
                .HasColumnName("reportedAt")
                .IsRequired();

            //Foreing Key
            builder.HasOne(g => g.Guardian)
                .WithMany(g => g.Urgencies)
                .HasForeignKey(g => g.GuardianId)
                .HasConstraintName("fk_urgency_guardian")
                .IsRequired(true);

        }
    }
}
