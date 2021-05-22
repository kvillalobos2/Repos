using Microsoft.EntityFrameworkCore;
using Safeteefy.Data.Mapping;
using Safeteefy.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Safeteefy.Data
{
    public class DbContextSafettefy: DbContext
    {
        public DbContextSafettefy(DbContextOptions<DbContextSafettefy> options) : base(options)
        {
        }

        public DbSet<Guardian> Guardians { get; set; }
        public DbSet<Urgency> Urgencies { get; set; }

        //override OnModel -> Buscar y darle enter, y se autocompletará

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.ApplyConfiguration(new GuardianMap());
            modelBuilder.ApplyConfiguration(new UrgencyMap());
            //Si sale subrayado rojo, es porque debemos llenar el Map antes
        }
    }
}
