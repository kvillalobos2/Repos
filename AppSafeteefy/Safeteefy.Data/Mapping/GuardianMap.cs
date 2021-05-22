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
    public class GuardianMap: IEntityTypeConfiguration<Guardian>
    {
        //Damos implementar interfaz
        public void Configure(EntityTypeBuilder<Guardian> builder)
        {
            builder.ToTable("Guardian")
                .HasKey(g => g.GuardiansId);
            builder.Property(g => g.GuardiansId)
                .HasColumnName("guardianId");
            builder.Property(g => g.Username)
                .HasColumnName("username")
                .IsRequired()
                .HasMaxLength(30)
                .IsUnicode(false);
            builder.Property(g => g.Email)
                .HasColumnName("email")
                .IsRequired()
                .HasMaxLength(30)
                .IsUnicode(false);
            builder.Property(g => g.FirstName)
                .HasColumnName("firstName")
                .HasMaxLength(60)
                .IsUnicode(false);
            builder.Property(g => g.LastName)
                .HasColumnName("lastName")
                .HasMaxLength(60)
                .IsUnicode(false);
            builder.Property(g => g.Gender)
                .HasColumnName("gender")
                .IsRequired()
                .IsUnicode(false);
            builder.Property(g => g.Address)
                .HasColumnName("address")
                .IsUnicode(false);
        }
    }
}
