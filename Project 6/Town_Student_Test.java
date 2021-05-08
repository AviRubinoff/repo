import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Town_Student_Test
{
    private Town town;

    @Before
    public void setUp() throws Exception
    {
        town = new Town("Minas Tirith");
    }

    @After
    public void tearDown() throws Exception
    {
        town = null;
    }

    @Test
    public void testGetName()
    {
        assertEquals("Minas Tirith", town.getName());
    }

    @Test
    public void testCompareTo()
    {
        assertEquals(0, town.compareTo(new Town("Minas Tirith")));
    }



}
