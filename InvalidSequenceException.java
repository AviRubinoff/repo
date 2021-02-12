/**
 * Exception to be thrown when the password contains a sequence of three or more characters.
 * @author Avi Rubinoff
 */
public class InvalidSequenceException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public InvalidSequenceException()
    {
        super("The password cannot contain more than two of the same character in sequence");
    }
}
