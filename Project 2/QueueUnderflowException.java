/**
 * An exception class to be thrown when a NotationQueue attempts
 * to remove an element when it is empty.
 */
public class QueueUnderflowException extends Exception
{
    /**
     * Creates a QueueUnderflowException.
     */
    public QueueUnderflowException()
    {
        super("This should have caused an QueueUnderflowException");
    }
}
