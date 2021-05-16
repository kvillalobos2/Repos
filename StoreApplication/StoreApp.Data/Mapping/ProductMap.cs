using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using StoreApp.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StoreApp.Data.Mapping
{
    public class ProductMap : IEntityTypeConfiguration<Product>
    {
        public void Configure(EntityTypeBuilder<Product> builder)
        {
            builder.ToTable("product")
                .HasKey(c => c.ProductId);
            builder.Property(c => c.ProductId)
                .HasColumnName("product_id");
            builder.Property(c => c.CategoryId)
                .HasColumnName("category_id");
            builder.Property(c => c.code)
                .HasColumnName("code")
                .HasMaxLength(5)
                .IsUnicode(false);
            builder.Property(c => c.Name)
                .HasColumnName("name")
                .HasMaxLength(50)
                .IsUnicode(false);
            builder.Property(c => c.UnitPrice)
                .HasColumnName("unit_price")
                .HasColumnType("decimal(11,2)");
            builder.Property(c => c.Stock)
                .HasColumnName("stock")
                .HasColumnType("int");
            builder.Property(c => c.Description)
                .HasColumnName("description")
                .HasMaxLength(250)
                .IsUnicode(false);
            builder.Property(c => c.Condition)
                .HasColumnName("condition")
                .HasDefaultValueSql("((1))");
            
            //creacion de la FK del Category
            builder.HasOne(c => c.Category)
                .WithMany(c => c.Products)
                .HasForeignKey(c => c.CategoryId)
                .HasConstraintName("fk_product_category")
                .IsRequired(true);
        }
    }
}
