import java.util.*;

/**
 * A class for representing a network of towns and roads as a graph.
 * Has a method for calculating the shortest path between two towns,
 * using Dijkstra's algorithm.
 *
 * @author Avi Rubinoff
 */
public class Graph implements GraphInterface<Town, Road>
{
    private TreeSet<Town> towns;
    private TreeSet<Road> roads;
    private HashMap<Town, HashMap<Town, ArrayList<String>>> solvedVertices;

    /**
     * Constructor method.
     */
    public Graph()
    {
        towns = new TreeSet<>();
        roads = new TreeSet<>();
        solvedVertices = new HashMap<>();
    }

    /**
     * Returns the Road connecting the two given Towns, or null if
     * no such road exists. As this is an undirected graph, the
     * method does not care which of the two Towns is which vertex
     * of the Road.
     * @param sourceVertex one of the Towns.
     * @param destinationVertex the other Town.
     *
     * @return the Road connecting the Towns, or null if no such road exists.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex)
    {
        for (Road r: roads)
        {
            if (r.contains(sourceVertex) && r.contains(destinationVertex))
                return r;
        }
        return null;
    }

    /**
     * Adds a new Road to the graph, connecting the specified Towns. If the Towns
     * are not already in the graph, the method throws an IllegalArgumentException.
     * Returns null if the graph already contains the Road to be added.
     * @param sourceVertex Town at one end of the Road.
     * @param destinationVertex Town at the other end of the Road.
     * @param weight length of the Road.
     * @param description name of the Road.
     *
     * @return the Road that was added, or null if the Road already existed
     * @throws IllegalArgumentException thrown if either of the given Towns is not in the graph.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException
    {
        Road r = new Road(sourceVertex, destinationVertex, weight, description);
        if (roads.contains(r))
            return null;
        else
        {
            if (!towns.contains(sourceVertex) || !towns.contains(destinationVertex))
                throw new IllegalArgumentException();
            roads.add(r);
            return r;
        }
    }

    /**
     * Adds the given Town to the graph, if it is not already present.
     * Returns false if the graph already contains the Town.
     *
     * @param town the Town to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town town)
    {
        if (towns.contains(town))
            return false;
        else
        {
            towns.add(town);
            return true;
        }
    }

    /**
     * Returns true if the graph contains a Road connecting the two specified
     * Towns, and false otherwise.
     * @param sourceVertex the first Town.
     * @param destinationVertex the second Town.
     *
     * @return true if there's a Road connecting the two Towns, false otherwise.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex)
    {
        for (Road r: roads)
        {
            if (r.contains(sourceVertex) && r.contains(destinationVertex))
                return true;
        }
        return false;
    }

    /**
     * Returns true if the graph contains the specified Town, and false otherwise.
     *
     * @param town the Town whose presence is to be checked.
     * @return true if the Town is present; false otherwise.
     */
    @Override
    public boolean containsVertex(Town town)
    {
        return towns.contains(town);
    }

    /**
     * Returns a Set containing the Roads in the graph.
     *
     * @return the Set of Roads.
     */
    @Override
    public Set<Road> edgeSet()
    {
        return roads;
    }

    /**
     * Returns a Set containing all of the Roads adjacent to the specified Town.
     * @param vertex the Town whose adjacent Roads are desired.
     *
     * @return the Set of adjacent Roads.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException, NullPointerException
    {
        if (vertex == null)
            throw new NullPointerException();
        if (!towns.contains(vertex))
            throw new IllegalArgumentException();
        HashSet<Road> result = new HashSet<>();
        for (Road r: roads)
        {
            if (r.contains(vertex))
                result.add(r);
        }
        return result;
    }

    /**
     * Removes the Road connecting the two specified Towns and with the specified
     * name and length, if one exists. Name is disregarded if it is null. Length
     * is disregarded if it is less than 0.
     *
     * @param sourceVertex first Town.
     * @param destinationVertex second Town.
     * @param weight length of the Road (ignored if less than 0).
     * @param description name of the Road (ignored if null).
     *
     * @return the Road that was removed, or null if no Road matched the input.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
        Road detritus = null;
        for (Road r: roads)
        {
            if ((r.contains(sourceVertex)) &&
                    (r.contains(destinationVertex)) &&
                    ((r.getLength() == weight) || weight < 0) &&
                    ((r.getName().equals(description)) || description == null))
            {
                detritus = r;
                roads.remove(detritus);
                break;
            }
        }
        return detritus;
    }

    /**
     * Removes the specified Town and all Roads that are adjacent to it
     * from the graph. Returns false if no matching Town was found.
     *
     * @param town the Town to be removed.
     * @return true if the Town was removed; false if no such Town was found.
     */
    @Override
    public boolean removeVertex(Town town)
    {
        if (towns.contains(town))
        {
            Set<Road> roadsToRemove = this.edgesOf(town);
            for (Road r: roadsToRemove)
                roads.remove(r);
            towns.remove(town);
            return true;
        }
        return false;
    }

    /**
     * Returns a Set containing the Towns in the graph.
     *
     * @return the Set of Towns.
     */
    @Override
    public Set<Town> vertexSet()
    {
        return towns;
    }

    /**
     * Finds the shortest path from sourceVertex to destinationVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
    {
        if (this.containsVertex(sourceVertex) && this.containsVertex(destinationVertex))
        {
            if (solvedVertices.get(sourceVertex) == null)
                dijkstraShortestPath(sourceVertex);
            return solvedVertices.get(destinationVertex).get(destinationVertex);
        }
        return null;
    }

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex)
    {
        HashMap<Town, Integer> costs = new HashMap<>();
        HashMap<Town, Town> previous = new HashMap<>();
        HashSet<Town> vertexSet = new HashSet<>();

        for (Town t: towns)
        {
            if (!t.equals(sourceVertex))
            {
                costs.put(t, -1);
                previous.put(t, null);
                vertexSet.add(t);
            }
        }
        costs.put(sourceVertex, 0);
        previous.put(sourceVertex, null);
        vertexSet.add(sourceVertex);

        //The count serves as a hedge against disjoint graphs. The longest possible path
        //is equal to the number of vertices in the graph, so if the loop runs that many
        //times, it needn't continue.
        int count = 0;
        while (!vertexSet.isEmpty() && count < towns.size())
        {
            count++;
            Town current = null;
            int min = Integer.MAX_VALUE;
            for (Town t: vertexSet)
            {
                if (costs.get(t) < min && costs.get(t) != -1)
                {
                    current = t;
                    min =  costs.get(t);
                }
            }
            vertexSet.remove(current);
            try
            {
                for (Road r : this.edgesOf(current))
                {
                    int alt = costs.get(current) + r.getLength();
                    if (alt < costs.get(r.otherVertex(current)) || costs.get(r.otherVertex(current)) == -1)
                    {
                        costs.replace(r.otherVertex(current), alt);
                        previous.replace(r.otherVertex(current), current);
                    }
                }
            }
            catch (NullPointerException npe)
            {}
        }

        for(Town t: towns)
        {
            Town prev = t;
            HashMap<Town, ArrayList<String>> path = new HashMap<>();
            ArrayList<String> steps = new ArrayList<>();
            while (!prev.equals(sourceVertex))
            {
                String step = prev.getName();
                Town doublePrev = previous.get(prev);
                for (Road r: this.edgesOf(prev))
                {
                    if (r.contains(doublePrev))
                    {
                        step = doublePrev.getName() + " via " + r.getName() + " to " + step + " " + r.getLength() + " mi";
                        break;
                    }
                }
                if (doublePrev != null)
                {
                    steps.add(step);
                    prev = doublePrev;
                }
                else break;
            }

            ArrayList<String> properOrderSteps = new ArrayList<>();
            if (steps.size() > 0)
            {
                for (int i = 0; i < steps.size(); i++)
                    properOrderSteps.add(steps.get(steps.size() - (i + 1)));
            }

            path.put(t, properOrderSteps);
            solvedVertices.put(t, path);
        }
    }
}