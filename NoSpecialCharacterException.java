/**
 * Exception to be thrown when the password does not contain a special character.
 * @author Avi Rubinoff
 */
public class NoSpecialCharacterException extends PasswordException
{
    /**
     * Constructs an instance of this class.
     */
    public NoSpecialCharacterException()
    {
        super("The password must contain at least one special character");
    }
}
