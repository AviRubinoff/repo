/**
 * Exception to be thrown when the utility is given two passwords that do not match.
 * @author Avi Rubinoff
 */
public class UnmatchedException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public UnmatchedException()
    {
        super("Passwords do not match");
    }
}
