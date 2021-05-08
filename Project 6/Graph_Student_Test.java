import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;

public class Graph_Student_Test
{
    private Graph graph;
    private Town[] towns;

    @Before
    public void setUp() throws Exception
    {
        graph = new Graph();
        towns = new Town[12];

        for (int i = 0; i < 12; i++)
        {
            towns[i] = new Town("Town #" + i);
            graph.addVertex(towns[i]);
        }

        graph.addEdge(towns[0], towns[4], 6, "Road #1");
        graph.addEdge(towns[0], towns[1], 3, "Road #2");
        graph.addEdge(towns[0], towns[7], 11, "Road #3");
        graph.addEdge(towns[4], towns[11], 2, "Road #4");
        graph.addEdge(towns[11], towns[3], 9, "Road #5");
        graph.addEdge(towns[3], towns[6], 101, "Road #6");
        graph.addEdge(towns[6], towns[9], 3, "Road #7");
        graph.addEdge(towns[6], towns[2], 12, "Road #8");
        graph.addEdge(towns[6], towns[5], 4, "Road #9");
        graph.addEdge(towns[5], towns[7], 5, "Road #10");
        graph.addEdge(towns[5], towns[10], 4, "Road #11");
        graph.addEdge(towns[5], towns[8], 7, "Road #12");
    }

    @After
    public void tearDown() throws Exception
    {
        graph = null;
        towns = null;
    }

    @Test
    public void testGetEdge()
    {
        assertEquals(new Road(towns[0], towns[4], 6, "Road #1"), graph.getEdge(towns[0], towns[4]));
    }

    @Test
    public void testAddEdge()
    {
        assertFalse(graph.containsEdge(towns[0], towns[3]));
        graph.addEdge(towns[0], towns[3], 2, "Shortcut");
        assertTrue(graph.containsEdge(towns[0], towns[3]));
    }

    @Test
    public void testAddVertex()
    {
        Town town = new Town("Space City");
        assertFalse(graph.containsVertex(town));
        graph.addVertex(town);
        assertTrue(graph.containsVertex(town));
    }

    @Test
    public void testContainsEdge()
    {
        assertTrue(graph.containsEdge(towns[6], towns[9]));
    }

    @Test
    public void testContainsVertex()
    {
        assertTrue(graph.containsVertex(towns[7]));
    }

    @Test
    public void testEdgeSet()
    {
        Set<Road> edgeSet = graph.edgeSet();
        assertEquals(12, edgeSet.size());
    }

    @Test
    public void testEdgesOf()
    {
        Set<Road> edges = graph.edgesOf(towns[6]);
        assertEquals(4, edges.size());
    }

    @Test
    public void testRemoveEdge()
    {
        assertTrue(graph.containsEdge(towns[5], towns[8]));
        graph.removeEdge(towns[5], towns[8], 7, "Road #12");
        assertFalse(graph.containsEdge(towns[5], towns[8]));
    }

    @Test
    public void testRemoveVertex()
    {
        assertTrue(graph.containsVertex(towns[6]));
        graph.removeVertex(towns[6]);
        assertFalse(graph.containsVertex(towns[6]));
    }

    @Test
    public void testVertexSet()
    {
        Set<Town> vertices = graph.vertexSet();
        assertEquals(12,vertices.size());
    }

    @Test
    public void testFindPath()
    {
        ArrayList<String> path = graph.shortestPath(towns[0], towns[3]);
        assertNotNull(path);
        assertEquals(3, path.size());
        assertEquals("Town #0 via Road #1 to Town #4 6 mi", path.get(0));
        assertEquals("Town #4 via Road #4 to Town #11 2 mi", path.get(1));
        assertEquals("Town #11 via Road #5 to Town #3 9 mi", path.get(2));
    }

}
