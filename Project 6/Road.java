/**
 * A class for representing edges in a Town Graph.
 *
 * @author Avi Rubionff
 */
public class Road implements Comparable<Road>
{
    private Town vertex1;
    private Town vertex2;
    private int length;
    private String name;

    /**
     * Constructor method.
     * @param source the Town where the Road begins
     * @param destination the Town where the Road ends
     * @param length the length of the Road (weight of the edge)
     * @param name the name of the Road
     */
    public Road(Town source, Town destination, int length, String name)
    {
        this.vertex1 = source;
        this.vertex2 = destination;
        this.length = length;
        this.name = name;
    }

    /**
     * Constructor for unweighted edge. Sets weight to 1.
     * @param source the Town where the Road begins
     * @param destination the Town where the Road ends
     * @param name the name of the Road
     */
    public Road(Town source, Town destination, String name)
    {
        this(source, destination, 1, name);
    }

    /**
     * Getter method for vertex1 (source in constructor).
     * @return vertex1
     */
    public Town getVertex1()
    {
        return vertex1;
    }

    /**
     * Getter method for vertex2 (destination in constructor).
     * @return vertex2
     */
    public Town getVertex2()
    {
        return vertex2;
    }

    /**
     * Getter method for the Road's length.
     * @return the Road's length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Getter method for the Road's name.
     * @return the Road's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to test whether the Road connects to the specified Town.
     * Returns true if the Town is equal to either of the Road's vertices
     * @param town the Town in question
     * @return true if the Town is equal to either of the Road's vertices, false otherwise
     */
    public boolean contains(Town town)
    {
        return (town.equals(vertex1) || town.equals(vertex2));
    }

    /**
     * Returns a String representation of the Road.
     * @return a String representation of the Road.
     */
    @Override
    public String toString()
    {
        return "Road{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", length=" + length +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Compares the two Roads based on their names.
     * @param o the Road to be compared to this one
     * @return <0 if this Road comes before o, 0 if they are equal, >0 if o comes before this Road
     */
    @Override
    public int compareTo(Road o)
    {
        return name.compareTo(o.getName());
    }

    /**
     * Tests if the specified Road is equal to this Road. Two Roads are equal
     * if they have the same vertices.
     * @param r the other Road
     * @return true if the two Roads are equal; false otherwise
     */
    @Override
    public boolean equals(Object r)
    {
        if (r instanceof Road)
        {
            Road r2 = (Road) r;
            return ((this.vertex1.equals(r2.getVertex1()) && this.vertex2.equals(r2.getVertex2())) ||
                    (this.vertex1.equals(r2.getVertex2()) && this.vertex2.equals(r2.getVertex1())));
        }
        else return false;
    }

    /**
     * Returns the vertex of the Road that is not the specified vertex.
     * @param t the specified vertex
     * @return the other vertex of the Road, or null if t is not one of the Road's vertices
     */
    public Town otherVertex(Town t)
    {
        if (t.equals(vertex1))
            return vertex2;
        else if (t.equals(vertex2))
            return vertex1;
        else return null;
    }
}
