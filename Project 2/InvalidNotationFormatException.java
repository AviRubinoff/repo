/**
 * An exception class to be thrown when a method in Notation is handed
 * an expression with an invalid format.
 */
public class InvalidNotationFormatException extends Exception
{
    /**
     * Creates an InvalidNotationFormatExpression.
     */
    public InvalidNotationFormatException()
    {
        super("This should have thrown an InvalidNotationFormatException");
    }
}
