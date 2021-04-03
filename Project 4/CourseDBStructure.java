import java.io.IOException;
import java.util.LinkedList;

/**
 * A class that is a database of CourseDBElements.
 * @author Avi Rubinoff
 */
public class CourseDBStructure implements CourseDBStructureInterface
{
    public LinkedList<CourseDBElement>[] hashTable;

    /**
     * Constructor method.
     * @param size the size of the database.
     */
    public CourseDBStructure(int size)
    {
        hashTable = new LinkedList[size];
    }

    /**
     * A variant constructor used in testing, apparently?
     * @param text A String supposedly used in testing, somehow.
     * @param size the size of the database.
     */
    public CourseDBStructure(String text, int size)
    {
        hashTable = new LinkedList[size];
    }

    /**
     * Adds a CDE to the database.
     * @param element the CDE to be added.
     */
    @Override
    public void add(CourseDBElement element)
    {
        int crnHash = element.hashCode() % hashTable.length;
        if (hashTable[crnHash] == null)
            hashTable[crnHash] = new LinkedList<>();
        if (!hashTable[crnHash].contains(element))
            hashTable[crnHash].add(element);
    }

    /**
     * Retrieves a CDE with the specified CRN.
     * @param crn the CRN of the desired CDE.
     * @return the CDe with the specified CRN.
     * @throws IOException thrown if no CDE has the specified CRN.
     */
    @Override
    public CourseDBElement get(int crn) throws IOException
    {
        String crnString = "" + crn;
        int crnHash = crnString.hashCode() % hashTable.length;
        for (CourseDBElement c: hashTable[crnHash])
        {
            if (c.getCRN() == crn)
                return c;
        }
        throw new IOException();
    }

    /**
     * Getter method for the size of the hash table.
     * @return the size of the hash table.
     */
    @Override
    public int getTableSize()
    {
        return hashTable.length;
    }
}
