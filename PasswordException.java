/**
 * Superclass for all password exceptions.
 * @author Avi Rubinoff
 */
public class PasswordException extends Exception
{
    /**
     * Generates a PasswordException with the specified detail message.
     * @param message the detail message.
     */
    public PasswordException(String message) {
        super(message);
    }
}
