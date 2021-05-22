﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Safeteefy.Data;

namespace Safeteefy.Data.Migrations
{
    [DbContext(typeof(DbContextSafettefy))]
    partial class DbContextSafettefyModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.4")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("Safeteefy.Entities.Guardian", b =>
                {
                    b.Property<int>("GuardiansId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("guardianId")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Address")
                        .IsUnicode(false)
                        .HasColumnType("varchar(max)")
                        .HasColumnName("address");

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasMaxLength(30)
                        .IsUnicode(false)
                        .HasColumnType("varchar(30)")
                        .HasColumnName("email");

                    b.Property<string>("FirstName")
                        .HasMaxLength(60)
                        .IsUnicode(false)
                        .HasColumnType("varchar(60)")
                        .HasColumnName("firstName");

                    b.Property<string>("Gender")
                        .IsRequired()
                        .IsUnicode(false)
                        .HasColumnType("varchar(max)")
                        .HasColumnName("gender");

                    b.Property<string>("LastName")
                        .HasMaxLength(60)
                        .IsUnicode(false)
                        .HasColumnType("varchar(60)")
                        .HasColumnName("lastName");

                    b.Property<string>("Username")
                        .IsRequired()
                        .HasMaxLength(30)
                        .IsUnicode(false)
                        .HasColumnType("varchar(30)")
                        .HasColumnName("username");

                    b.HasKey("GuardiansId");

                    b.ToTable("Guardian");
                });

            modelBuilder.Entity("Safeteefy.Entities.Urgency", b =>
                {
                    b.Property<int>("UrgencyId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasColumnName("urgencyId")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<int>("GuardianId")
                        .HasColumnType("int");

                    b.Property<double>("Latitude")
                        .HasColumnType("float")
                        .HasColumnName("latitude");

                    b.Property<double>("Longitude")
                        .HasColumnType("float")
                        .HasColumnName("longitude");

                    b.Property<DateTime>("ReportedAt")
                        .HasColumnType("datetime2")
                        .HasColumnName("reportedAt");

                    b.Property<string>("Summary")
                        .IsUnicode(false)
                        .HasColumnType("varchar(max)")
                        .HasColumnName("summary");

                    b.Property<string>("Title")
                        .IsRequired()
                        .IsUnicode(false)
                        .HasColumnType("varchar(max)")
                        .HasColumnName("title");

                    b.HasKey("UrgencyId");

                    b.HasIndex("GuardianId");

                    b.ToTable("Urgency");
                });

            modelBuilder.Entity("Safeteefy.Entities.Urgency", b =>
                {
                    b.HasOne("Safeteefy.Entities.Guardian", "Guardian")
                        .WithMany("Urgencies")
                        .HasForeignKey("GuardianId")
                        .HasConstraintName("fk_urgency_guardian")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Guardian");
                });

            modelBuilder.Entity("Safeteefy.Entities.Guardian", b =>
                {
                    b.Navigation("Urgencies");
                });
#pragma warning restore 612, 618
        }
    }
}
