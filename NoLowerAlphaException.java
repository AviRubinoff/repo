/**
 * Exception to be thrown when the password does not contain a lowercase letter.
 * @author Avi Rubinoff
 */
public class NoLowerAlphaException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public NoLowerAlphaException()
    {
        super("The password must contain at least one lower case alphabetic character");
    }
}
