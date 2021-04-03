import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A class for managing a database of courses.
 * @author Avi Rubinoff
 */
public class CourseDBManager implements CourseDBManagerInterface
{
    private CourseDBStructure structure;

    /**
     * Default constructor. Uses a size 20 hash table.
     */
    public CourseDBManager()
    {
        structure = new CourseDBStructure(20);
    }

    /**
     * Adds a course to the database.
     * @param id the course id.
     * @param crn the course CRN.
     * @param credits the number of credits the course is worth.
     * @param roomNum the room the course is taught in.
     * @param instructor the name of the instructor.
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor)
    {
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    /**
     * Retrieves a CourseDBElement with the specified CRN.
     * @param crn the CRN of the CourseDBElement to be retrieved.
     * @return the CourseDBElement with the specified CRN.
     */
    @Override
    public CourseDBElement get(int crn)
    {
        try
        {
            return structure.get(crn);
        }
        catch (IOException e)
        {
            return null;
        }
    }

    /**
     * Takes a file containing properly formatted course listings and
     * adds them all to the database. Proper format is:
     *      CourseID CRN Credits RoomNumber Instructor
     * @param input the file to be read.
     * @throws FileNotFoundException thrown if the file is not found.
     */
    @Override
    public void readFile(File input) throws FileNotFoundException
    {
        Scanner reader = new Scanner(input);
        while (reader.hasNext())
        {
            String text = reader.nextLine();
            int space = text.indexOf(' ');
            String id = text.substring(0, space);
            text = text.substring(space + 1);
            space = text.indexOf(' ');
            int crn = Integer.parseInt(text.substring(0, space));
            text = text.substring(space + 1);
            space = text.indexOf(' ');
            int credits = Integer.parseInt(text.substring(0, space));
            text = text.substring(space + 1);
            space = text.indexOf(' ');
            String roomNumber = text.substring(0, space);
            text = text.substring(space + 1);
            this.add(id, crn, credits, roomNumber, text);
        }
        reader.close();
    }

    /**
     * Creates an ArrayList of Strings containing a representation of every
     * course in the database.
     * @return the database as an ArrayList.
     */
    @Override
    public ArrayList<String> showAll()
    {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < structure.getTableSize(); i++)
        {
            LinkedList<CourseDBElement> list = structure.hashTable[i];
            if (list != null)
            {
                for (CourseDBElement e: list)
                    result.add(e.toString());
            }
        }

        return result;
    }
}
