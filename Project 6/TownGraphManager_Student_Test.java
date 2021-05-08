import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TownGraphManager_Student_Test
{
    private TownGraphManager graph;
    private String[] towns;

    @Before
    public void setUp() throws Exception
    {
        graph = new TownGraphManager();
        towns = new String[12];

        for (int i = 0; i < 12; i++)
        {
            towns[i] = "Town #" + i;
            graph.addTown(towns[i]);
        }

        graph.addRoad(towns[0], towns[4], 6, "Road #1");
        graph.addRoad(towns[0], towns[1], 3, "Road #2");
        graph.addRoad(towns[0], towns[7], 11, "Road #3");
        graph.addRoad(towns[4], towns[11], 2, "Road #4");
        graph.addRoad(towns[11], towns[3], 9, "Road #5");
        graph.addRoad(towns[3], towns[6], 101, "Road #6");
        graph.addRoad(towns[6], towns[9], 3, "Road #7");
        graph.addRoad(towns[6], towns[2], 12, "Road #8");
        graph.addRoad(towns[6], towns[5], 4, "Road #9");
        graph.addRoad(towns[5], towns[7], 5, "Road #10");
        graph.addRoad(towns[5], towns[10], 4, "Road #11");
        graph.addRoad(towns[5], towns[8], 7, "Road #12");
    }

    @After
    public void tearDown() throws Exception
    {
        graph = null;
        towns = null;
    }

    @Test
    public void testAddRoad()
    {
        ArrayList<String> roads = graph.allRoads();
        assertEquals(12, roads.size());
        graph.addRoad(towns[9], towns[10], 3, "The Road of DOOM!");
        roads = graph.allRoads();
        assertEquals(13, roads.size());
    }

    @Test
    public void testGetRoad()
    {
        assertEquals("Road #1", graph.getRoad(towns[0], towns[4]));
    }

    @Test
    public void testAddTown()
    {
        assertFalse(graph.containsTown("Isolated Hamlet"));
        graph.addTown("Isolated Hamlet");
        assertTrue(graph.containsTown("Isolated Hamlet"));
    }

    @Test
    public void testDisjointGraph()
    {
        assertFalse(graph.containsTown("Isolated Hamlet"));
        graph.addTown("Isolated Hamlet");
        ArrayList<String> path = graph.getPath(towns[1], "Isolated Hamlet");
        assertEquals(0, path.size());
    }

    @Test
    public void testContainsTown()
    {
        assertTrue(graph.containsTown(towns[3]));
    }

    @Test
    public void testContainsRoadConnection()
    {
        assertTrue(graph.containsRoadConnection(towns[0], towns[4]));
    }

    @Test
    public void testAllRoads()
    {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("Road #1", roads.get(0));
    }

    @Test
    public void testDeleteRoadConnection()
    {
        assertTrue(graph.containsRoadConnection(towns[0], towns[4]));
        graph.deleteRoadConnection(towns[0], towns[4], "Road #1");
        assertFalse(graph.containsRoadConnection(towns[0], towns[4]));
    }

    @Test
    public void testAllTowns()
    {
        ArrayList<String> towns = graph.allTowns();
        assertEquals("Town #0", towns.get(0));
    }

    @Test
    public void testGetPath()
    {
        ArrayList<String> path = graph.getPath(towns[0], towns[3]);
        assertNotNull(path);
        assertEquals(3, path.size());
        assertEquals("Town #0 via Road #1 to Town #4 6 mi", path.get(0));
        assertEquals("Town #4 via Road #4 to Town #11 2 mi", path.get(1));
        assertEquals("Town #11 via Road #5 to Town #3 9 mi", path.get(2));
    }
}
