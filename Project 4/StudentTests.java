import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class StudentTests
{
    private CourseDBManager manager;
    private CourseDBStructure structure;

    @Before
    public void setUp() throws Exception
    {
        manager = new CourseDBManager();
        structure = new CourseDBStructure(20);
    }

    @After
    public void tearDown() throws Exception
    {
        manager = null;
        structure = null;
    }

    @Test
    public void testAddToDB()
    {
        try
        {
            manager.add("CMSC204", 12345, 4, "SC450", "Steven von Teachmaster");
        } catch (Exception e)
        {
            fail("This should not have caused an Exception");
        }
    }

    @Test
    public void testShowAll()
    {
        manager.add("CMSC204", 12345, 4, "SC450", "Steven von Teachmaster");
        manager.add("CMSC207", 45454, 4, "SC887", "Alan Turing's Ghost");
        manager.add("CMSC321", 36998, 4, "Distance Learning", "A small bird");

        ArrayList<String> list = manager.showAll();

        assertEquals(list.get(0), "\nCourse:CMSC207 CRN:45454 Credits:4 Instructor:Alan Turing's Ghost Room:SC887");
        assertEquals(list.get(1), "\nCourse:CMSC321 CRN:36998 Credits:4 Instructor:A small bird Room:Distance Learning");
        assertEquals(list.get(2), "\nCourse:CMSC204 CRN:12345 Credits:4 Instructor:Steven von Teachmaster Room:SC450");
    }

    @Test
    public void testRead()
    {
        try
        {
            File inputFile = new File("Test2.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("CMSC204 12345 4 SC450 Steven von Teachmaster");
            inFile.print("CMSC 207 45454 4 SC887 Alan Turing's Ghost");

            inFile.close();
            manager.readFile(inputFile);
            assertEquals(manager.get(12345).getCourseID(), "CMSC204");
            //System.out.println(dataMgr.showAll());
        } catch (Exception e)
        {
            fail("Should not have thrown an exception");
        }
    }

    @Test
    public void testGetTableSize()
    {
        assertEquals(20, structure.getTableSize());
    }

    @Test
    public void testHashTable()
    {
        assertEquals(20, structure.hashTable.length);
        CourseDBElement cde = new CourseDBElement("CMSC207", 45454, 4, "SC887", "Alan Turing's Ghost");
        structure.add(cde);
        LinkedList<CourseDBElement> list = structure.hashTable[cde.hashCode()%structure.getTableSize()];
        assertEquals(45454, list.get(0).getCRN());
    }
}