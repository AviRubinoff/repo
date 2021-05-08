import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * A class for managing a Town Graph.
 *
 * @author Avi Rubinoff
 */
public class TownGraphManager implements TownGraphManagerInterface
{
    private Graph graph;

    /**
     * Constructor method.
     */
    public TownGraphManager()
    {
        graph = new Graph();
    }

    /**
     * Adds a road with 2 towns and a road name
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName)
    {
        try
        {
            Road r = graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
            return (r != null);
        }
        catch (IllegalArgumentException iae) { return false; }
    }

    /**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2)
    {
        if (graph.containsEdge(new Town(town1), new Town(town2)))
            return graph.getEdge(new Town(town1), new Town(town2)).getName();
        return null;
    }

    /**
     * Adds a town to the graph
     * @param v the town's name
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v)
    {
        return graph.addVertex(new Town(v));
    }

    /**
     * Gets a town with a given name
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name)
    {
        Set<Town> towns = graph.vertexSet();
        for (Town t: towns)
        {
            if (t.getName().equals(name))
                return t;
        }
        return null;
    }

    /**
     * Determines if a town is already in the graph
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v)
    {
        return graph.containsVertex(new Town(v));
    }

    /**
     * Determines if a road is in the graph
     * @param town1 name of town 1
     * @param town2 name of town 2
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2)
    {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads()
    {
        ArrayList<String> result = new ArrayList<>();
        for (Road r: graph.edgeSet())
            result.add(r.getName());
        return result;
    }

    /**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road)
    {
        return (graph.removeEdge(new Town(town1), new Town(town2), -1, road) != null);
    }

    /**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v)
    {
        return graph.removeVertex(new Town(v));
    }

    /**
     * Creates an arraylist of all towns in alphabetical order
     * @return an arraylist of all towns in alphabetical order
     */
    @Override
    public ArrayList<String> allTowns()
    {
        ArrayList<String> result = new ArrayList<>();
        for (Town t: graph.vertexSet())
            result.add(t.getName());
        return result;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together (if
     * there is no path between the towns, the ArrayList will be empty)
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2)
    {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    /**
     * Reads in a file and fills the graph with the data within.
     * @param input the file to be read
     * @throws IOException thrown if the file is null
     */
    public void populateTownGraph(File input) throws IOException
    {
        if (input == null)
            throw new FileNotFoundException();
        Scanner reader = new Scanner(input);
        while (reader.hasNext())
        {
            String line = reader.nextLine();
            int divisor = line.indexOf(',');
            String roadName = line.substring(0, divisor);
            line = line.substring(divisor + 1);
            divisor = line.indexOf(';');
            int weight = Integer.parseInt(line.substring(0, divisor));
            line = line.substring(divisor + 1);
            divisor = line.indexOf(';');
            String town1Name = line.substring(0, divisor);
            String town2Name = line.substring(divisor + 1);
            this.addTown(town1Name);
            this.addTown(town2Name);
            this.addRoad(town1Name, town2Name, weight, roadName);
        }
    }
}
