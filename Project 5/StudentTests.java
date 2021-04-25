import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StudentTests
{
    public MorseCodeTree tree;

    @Before
    public void setUp() throws Exception
    {
        tree = new MorseCodeTree();
    }

    @After
    public void tearDown() throws Exception
    {
        tree = null;
    }

    @Test
    public void testConvertToEnglishString()
    {
        String morse = ".--. .-. --- --. .-. .- -- -- .. -. --. / .. ... / .-.. --- - ... / --- ..-. / ..-. ..- -.";
        String english = MorseCodeConverter.convertToEnglish(morse);
        assertEquals(english, "programming is lots of fun");
    }

    @Test
    public void testConvertToEnglishFile() throws FileNotFoundException
    {
        File test = new File("morse.txt");
        String english = MorseCodeConverter.convertToEnglish(test);
        assertEquals(english, "converting from files is within the capability of this software");
    }

    @Test
    public void testTree()
    {
        ArrayList<String> list = tree.toArrayList();
        assertEquals("h", list.get(0));
        assertEquals("s", list.get(1));
        assertEquals("v", list.get(2));
        assertEquals("i", list.get(3));
    }

    @Test
    public void testInvalidInput()
    {
        String badString = "this is a mistake!";
        String error = MorseCodeConverter.convertToEnglish(badString);
        assertEquals("Invalid input", error);

        String notMorse = "...---";
        String alsoWrong = MorseCodeConverter.convertToEnglish(notMorse);
        assertEquals("Invalid input", alsoWrong);

        String notMorse2 = "...--- .-.-.-.-.-.- /";
        String alsoWrong2 = MorseCodeConverter.convertToEnglish(notMorse2);
        assertEquals("Invalid input", alsoWrong2);

        String notMorse3 = ".--. .-. --- --. .-. .- -- -- .. -. --./";
        String alsoWrong3 = MorseCodeConverter.convertToEnglish(notMorse3);
        assertEquals("Invalid input", alsoWrong3);
    }
}
