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
    public class GuardiansController : ControllerBase
    {
        private readonly DbContextSafettefy _context;

        public GuardiansController(DbContextSafettefy context)
        {
            _context = context;
        }

        // GET: api/Guardians
        [HttpGet]
        public async Task<IEnumerable<GuardianModel>> GetGuardians()
        {
            var guardianList = await _context.Guardians.ToListAsync();

            return guardianList.Select(g => new GuardianModel
            {
                GuardiansId = g.GuardiansId,
                Username=g.Username,
                Email=g.Email,
                FirstName=g.FirstName,
                LastName=g.LastName,
                Gender=g.Gender,
                Address=g.Address
            });
        }

        // GET: api/Guardians/GetGuardianById/5
        [HttpGet("[action]/{id}")]
        public async Task<IActionResult> GetGuardianById([FromRoute]int id)
        {
            var guardian = await _context.Guardians.FindAsync(id);

            if (guardian == null)
            {
                return NotFound();
            }

            return Ok(new GuardianModel
            {
                GuardiansId = guardian.GuardiansId,
                Username = guardian.Username,
                Email = guardian.Email,
                FirstName = guardian.FirstName,
                LastName = guardian.LastName,
                Gender = guardian.Gender,
                Address = guardian.Address
            });
        }

        // PUT: api/Guardians/PutGuardian
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("[action]")]
        public async Task<IActionResult> PutGuardian([FromBody] PutGuardianModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            if (model.GuardiansId <= 0)
            {
                return BadRequest();
            }

            var guardian = await _context.Guardians.FirstOrDefaultAsync(g => g.GuardiansId == model.GuardiansId);
            if (guardian == null)
            {
                return NotFound();
            }

            guardian.Username = model.Username;
            guardian.Email = model.Email;
            guardian.FirstName = model.FirstName;
            guardian.LastName = model.LastName;
            guardian.Gender = model.Gender;
            guardian.Address = model.Address;

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

        // POST: api/Guardians
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<IActionResult> PostGuardian([FromBody] PostGuardianModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Guardian guardian = new Guardian
            {
                Username = model.Username,
                Email = model.Email,
                FirstName = model.FirstName,
                LastName = model.LastName,
                Gender = model.Gender,
                Address = model.Address
            };
            _context.Guardians.Add(guardian);
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

        // DELETE: api/Guardians/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteGuardian([FromRoute]int id)
        {
            var guardian = await _context.Guardians.FindAsync(id);
            if (guardian == null)
            {
                return NotFound();
            }

            _context.Guardians.Remove(guardian);
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
