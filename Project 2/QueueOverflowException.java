/**
 * An exception class to be thrown when a NotationQueue attempts
 * to add an element when it is full.
 */
public class QueueOverflowException extends Exception
{
    /**
     * Creates a QueueOverflowException.
     */
    public QueueOverflowException()
    {
        super("This should have caused an QueueOverflowException");
    }
}
