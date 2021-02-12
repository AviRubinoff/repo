/**
 * Exception to be thrown when the password does not contain a digit.
 * @author Avi Rubinoff
 */
public class NoDigitException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public NoDigitException()
    {
        super("The password must contain at least one digit");
    }
}
