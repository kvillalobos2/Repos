using NUnit.Framework;
using Safeteefy.Entities;

namespace TestEntities
{
    public class TestEntities
    {

        [Test]
        public void UsernameTest()
        {
            Guardian guardian = new Guardian();
            guardian.Username = "jair";
            Assert.IsNotNull(guardian.Username);
        }
    }
}