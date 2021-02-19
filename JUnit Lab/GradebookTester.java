import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradebookTester
{
    GradeBook bookOne;
    GradeBook bookTwo;

    @BeforeEach
    void setUp() throws Exception
    {
        bookOne = new GradeBook(5);
        bookTwo = new GradeBook(5);
        bookOne.addScore(3);
        bookOne.addScore(4);
        bookOne.addScore(5);
        bookTwo.addScore(17438);
        bookTwo.addScore(19761);
    }

    @AfterEach
    void tearDown() throws Exception
    {
        bookOne = null;
        bookTwo = null;
    }

    @Test
    void testAddScore()
    {
        assertTrue(bookOne.toString().equals("3.0 4.0 5.0 0.0 0.0"));
        assertTrue(bookTwo.toString().equals("17438.0 19761.0 0.0 0.0 0.0"));
        assertEquals(3, bookOne.getScoresSize());
        assertEquals(2, bookTwo.getScoresSize());
    }

    @Test
    void testSum()
    {
        assertEquals(12.0, bookOne.sum());
        assertEquals(37199.0, bookTwo.sum());
    }

    @Test
    void testMinimum()
    {
        assertEquals(3.0, bookOne.minimum());
        assertEquals(17438.0, bookTwo.minimum());
    }

    @Test
    void testFinalScore()
    {
        assertEquals(9.0, bookOne.finalScore());
        assertEquals(19761.0, bookTwo.finalScore());
    }
}
