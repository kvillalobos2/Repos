using Microsoft.EntityFrameworkCore.Migrations;

namespace Bookmarks.Data.Migrations
{
    public partial class BookmarkMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "bookmark",
                columns: table => new
                {
                    BookmarkId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    title = table.Column<string>(type: "nvarchar(60)", maxLength: 60, nullable: false),
                    latitude = table.Column<string>(type: "nvarchar(30)", maxLength: 30, nullable: false),
                    longitude = table.Column<string>(type: "nvarchar(30)", maxLength: 30, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_bookmark", x => x.BookmarkId);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "bookmark");
        }
    }
}
