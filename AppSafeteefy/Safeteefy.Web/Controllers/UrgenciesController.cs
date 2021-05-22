using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Safeteefy.Data;
using Safeteefy.Entities;
using Safeteefy.Web.Models;

namespace Safeteefy.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UrgenciesController : ControllerBase
    {
        private readonly DbContextSafettefy _context;

        public UrgenciesController(DbContextSafettefy context)
        {
            _context = context;
        }

        // GET: api/Urgencies
        [HttpGet]
        public async Task<IEnumerable<UrgencyModel>> GetUrgencies()
        {
            var urgencyList = await _context.Urgencies.ToListAsync();
            
            return urgencyList.Select(u => new UrgencyModel
            {
                UrgencyId = u.UrgencyId,
                Title = u.Title,
                Latitude = u.Latitude,
                Longitude = u.Longitude,
                ReportedAt = u.ReportedAt
            });
        }

        // GET: api/Urgencies/5
        [HttpGet("[action]/{id}")]
        public async Task<IActionResult> GetUrgencyById([FromRoute]int id)
        {
            var urgency = await _context.Urgencies.FindAsync(id);

            if (urgency == null)
            {
                return NotFound();
            }

            return Ok(new UrgencyModel
            {
                UrgencyId=urgency.UrgencyId,
                Title=urgency.Title,
                Latitude=urgency.Latitude,
                Longitude=urgency.Longitude,
                ReportedAt=urgency.ReportedAt
            });
        }

        // PUT: api/Urgencies/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("[action]")]
        public async Task<IActionResult> PutUrgency([FromBody] PutUrgencyModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (model.UrgencyId <= 0)
            {
                return BadRequest();
            }

            var urgency = await _context.Urgencies.FirstOrDefaultAsync(u => u.UrgencyId == model.UrgencyId);
            if (urgency == null)
            {
                return NotFound();
            }
            urgency.Title = model.Title;
            urgency.Latitude = model.Latitude;
            urgency.Longitude = model.Longitude;
            urgency.ReportedAt = model.ReportedAt;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException ex)
            {
                return BadRequest(ex.Message);
            }
            return Ok();
        }

        // POST: api/Urgencies
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<IActionResult> PostUrgency([FromBody] PostUrgencyModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Urgency urgency = new Urgency
            {
                Title = model.Title,
                Latitude = model.Latitude,
                Longitude = model.Longitude,
                ReportedAt = model.ReportedAt,
                GuardianId = model.GuardianId
            };

            _context.Urgencies.Add(urgency);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
            return Ok();
        }

        // DELETE: api/Urgencies/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteUrgency([FromRoute]int id)
        {
            var urgency = await _context.Urgencies.FindAsync(id);
            if (urgency == null)
            {
                return NotFound();
            }

            _context.Urgencies.Remove(urgency);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }


            return Ok();
        }

    }
}
