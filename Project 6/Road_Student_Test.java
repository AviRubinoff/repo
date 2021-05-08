import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Road_Student_Test
{
    private Town t1;
    private Town t2;
    private Road road;

    @Before
    public void setUp() throws Exception
    {
        t1 = new Town("Barad Dur");
        t2 = new Town("Sammath Naur");
        road = new Road(t1, t2, 20, "Sauron's Road");
    }

    @After
    public void tearDown() throws Exception
    {
        t1 = null;
        t2 = null;
        road = null;
    }

    @Test
    public void testGetters()
    {
        assertEquals(t1, road.getVertex1());
        assertEquals(t2, road.getVertex2());
        assertEquals(20, road.getLength());
        assertEquals("Sauron's Road", road.getName());
    }

    @Test
    public void testContains()
    {
        assertTrue(road.contains(t1));
        assertTrue(road.contains(t2));
        assertFalse(road.contains(new Town("Minas Morgul")));
    }

    @Test
    public void testToString()
    {
        assertEquals("Road{vertex1=Town{name='Barad Dur', adjacentTowns=null}, vertex2=Town{name='Sammath Naur', adjacentTowns=null}, length=20, name='Sauron's Road'}", road.toString());
    }

    @Test
    public void testOtherVertex()
    {
        assertEquals(t1, road.otherVertex(t2));
        assertEquals(t2, road.otherVertex(t1));
        assertNull(road.otherVertex(new Town("Minas Morgul")));
    }
}
