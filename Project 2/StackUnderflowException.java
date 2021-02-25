/**
 * An exception class to be thrown when a NotationStack attempts
 * to add an element when it is full.
 */
public class StackUnderflowException extends Exception
{
    /**
     * Creates a StackUnderflowException.
     */
    public StackUnderflowException()
    {
        super("This should have caused an StackUnderflowException");
    }
}
