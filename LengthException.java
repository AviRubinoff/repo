/**
 * Exception to be thrown when the password is less than 6 characters long.
 * @author Avi Rubinoff
 */
public class LengthException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public LengthException()
    {
        super("The password must be at least 6 characters long");
    }

}
