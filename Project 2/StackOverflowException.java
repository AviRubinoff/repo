/**
 * An exception class to be thrown when a NotationStack attempts
 * to add an element when it is full.
 */
public class StackOverflowException extends Exception
{
    /**
     * Creates a StackOverflowException.
     */
    public StackOverflowException()
    {
        super("This should have caused an StackOverflowException");
    }
}
