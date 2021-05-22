using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Bookmarks.Data;
using Bookmarks.Entities;
using Bookmarks.Web.Models;

namespace Bookmarks.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Produces("application/json")]
    public class BooksmarksController : ControllerBase
    {
        private readonly DbContextBookmarks _context;

        public BooksmarksController(DbContextBookmarks context)
        {
            _context = context;
        }

        // GET: api/Booksmarks
        [HttpGet]
        public async Task<IEnumerable<BookmarkModel>> GetBookmarks()
        {
            var bookList = await _context.Bookmarks.ToListAsync();
            return bookList.Select(b => new BookmarkModel
            {
                BookmarkId = b.BookmarkId,
                Title = b.Title,
                Latitude = b.Latitude,
                Longitude = b.Longitude
            });

        }
    

        // GET: api/Booksmarks/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetBooksmarkById(int id)
        {
            var booksmark = await _context.Bookmarks.FindAsync(id);

            if (booksmark == null)
            {
                return NotFound();
            }

            return Ok(new BookmarkModel
            {
                BookmarkId=booksmark.BookmarkId,
                Title=booksmark.Title,
                Latitude=booksmark.Latitude,
                Longitude=booksmark.Longitude
            });
        }

        // PUT: api/Booksmarks/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
      
        public async Task<IActionResult> PutBookmark([FromBody] UpdateBookmarkModel bmodel)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (bmodel.BookmarkId <= 0)
            {
                return BadRequest();
            }

            var bookmark = await _context.Bookmarks.FirstOrDefaultAsync(b => b.BookmarkId == bmodel.BookmarkId);
            if (bookmark == null)
            {
                return NotFound();
            }
            bookmark.Title = bmodel.Title;
            bookmark.Latitude = bmodel.Latitude;
            bookmark.Longitude = bmodel.Longitude;

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

        // POST: api/Booksmarks
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<IActionResult> PostBookmark([FromBody] CreateBookmarkModel bmodel)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            Booksmark booksmark = new Booksmark
            {
                Title=bmodel.Title,
                Latitude = bmodel.Latitude,
                Longitude=bmodel.Longitude
        
            };

            _context.Bookmarks.Add(booksmark);

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

        // DELETE: api/Booksmarks/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteBooksmark([FromRoute]int id)
        {
            var booksmark = await _context.Bookmarks.FindAsync(id);
            if (booksmark == null)
            {
                return NotFound();
            }

            _context.Bookmarks.Remove(booksmark);
            try
            {
                await _context.SaveChangesAsync();
            } 
            catch(Exception e)
            {
                return BadRequest(e.Message);
            }
         

            return Ok();

        }

        
    }
}
