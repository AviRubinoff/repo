/**
 * Exception to be thrown when the password does not contain an uppercase letter.
 * @author Avi Rubinoff
 */
public class NoUpperAlphaException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public NoUpperAlphaException()
    {
        super("The password must contain at least one uppercase alphabetic character");
    }
}
