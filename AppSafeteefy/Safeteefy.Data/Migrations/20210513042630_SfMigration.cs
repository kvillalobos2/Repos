using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Safeteefy.Data.Migrations
{
    public partial class SfMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Guardian",
                columns: table => new
                {
                    guardianId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    username = table.Column<string>(type: "varchar(30)", unicode: false, maxLength: 30, nullable: false),
                    email = table.Column<string>(type: "varchar(30)", unicode: false, maxLength: 30, nullable: false),
                    firstName = table.Column<string>(type: "varchar(60)", unicode: false, maxLength: 60, nullable: true),
                    lastName = table.Column<string>(type: "varchar(60)", unicode: false, maxLength: 60, nullable: true),
                    gender = table.Column<string>(type: "varchar(max)", unicode: false, nullable: false),
                    address = table.Column<string>(type: "varchar(max)", unicode: false, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Guardian", x => x.guardianId);
                });

            migrationBuilder.CreateTable(
                name: "Urgency",
                columns: table => new
                {
                    urgencyId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    title = table.Column<string>(type: "varchar(max)", unicode: false, nullable: false),
                    summary = table.Column<string>(type: "varchar(max)", unicode: false, nullable: true),
                    latitude = table.Column<double>(type: "float", nullable: false),
                    longitude = table.Column<double>(type: "float", nullable: false),
                    reportedAt = table.Column<DateTime>(type: "datetime2", nullable: false),
                    GuardianId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Urgency", x => x.urgencyId);
                    table.ForeignKey(
                        name: "fk_urgency_guardian",
                        column: x => x.GuardianId,
                        principalTable: "Guardian",
                        principalColumn: "guardianId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Urgency_GuardianId",
                table: "Urgency",
                column: "GuardianId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Urgency");

            migrationBuilder.DropTable(
                name: "Guardian");
        }
    }
}
