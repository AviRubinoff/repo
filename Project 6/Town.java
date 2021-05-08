/**
 * A class for representing the vertices on a Town Graph.
 *
 * @author Avi Rubinoff
 */
public class Town implements Comparable<Town>
{
    private String name;

    /**
     * Constructor method.
     * @param name the Town's name
     */
    public Town(String name)
    {
        this.name = name;
    }

    /**
     * Copy constructor method - returns a deep copy of the specified Town.
     * @param templateTown the Town to be copied.
     */
    public Town(Town templateTown)
    {
        this(templateTown.getName());
    }

    /**
     * Getter method for the Town's name.
     * @return the Town's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Represents the Town as a String.
     * @return a String representatio of the Town
     */
    @Override
    public String toString()
    {
        return "Town{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Compares the two Towns based on their names.
     * @param o the Town to be compared to this one
     * @return <0 if this Town comes before o, 0 if they are equal, >0 if o comes before this Town
     */
    @Override
    public int compareTo(Town o)
    {
        return this.name.compareTo(o.getName());
    }

    /**
     * Returns the hashcode of the Town.
     * @return the hashcode of the Town
     */
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    /**
     * Tests an Object to see if it is equal to this Town.
     * @param obj the Object to be tested
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Town)
            return (name.equals(((Town) obj).getName()));
        return false;
    }
}
