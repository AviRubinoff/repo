import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provides methods for checking the validity and strength of a password.
 * @author Avi Rubinoff
 */
public class PasswordCheckerUtility
{
    /**
     * Compare equality of two passwords
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @throws UnmatchedException  - thrown if not same (case sensitive)
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
    {
        if (!comparePasswordsWithReturn(password, passwordConfirm))
            throw new UnmatchedException();
    }

    /**
     * Compare equality of two passwords
     * @param password - password string to be checked for
     * @param passwordConfirm - passwordConfirm string to be checked against password for
     * @return true if both same (case sensitive)
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
    {
        if (password.equals(passwordConfirm))
            return true;
        else return false;
    }

    /**
     * Reads a file of passwords and the passwords that failed the check will be added to an invalidPasswords with the reason
     * @param passwords - list of passwords read from a file
     * @return invalidPasswords - ArrayList of invalid passwords in the correct format
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
    {
        ArrayList<String> invalidPasswords = new ArrayList<String>();
        for (String p : passwords)
        {
            try
            {
                isValidPassword(p);
            }
            catch (PasswordException pe)
            {
                invalidPasswords.add(p + " - " + pe.getMessage());
            }
        }
        return invalidPasswords;
    }

    /**
     * Weak password length check - Password contains 6 to 9 characters , still considers valid, just weak
     * @param password - password string to be checked for Sequence requirement
     * @return true if password contains 6 to 9 characters
     */
    public static boolean hasBetweenSixAndNineChars(String password)
    {
        return (password.length() > 5 && password.length() < 10);
    }

    /**
     * Checks the password Digit requirement - Password must contain a numeric character
     * @param password - password string to be checked for Digit requirement
     * @return true if meet Digit requirement
     * @throws NoDigitException - thrown if does not meet Digit requirement
     */
    public static boolean hasDigit(String password) throws NoDigitException
    {
        Pattern pattern = Pattern.compile(".*[0-9].*");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new NoDigitException();
        return true;
    }

    /**
     * Checks the password lowercase requirement - Password must contain a lowercase alpha character
     * @param password - password string to be checked for lowercase requirement
     * @return true if meet lowercase requirement
     * @throws NoLowerAlphaException - thrown if does not meet lowercase requirement
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
    {
        Pattern pattern = Pattern.compile(".*[a-z].*");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new NoLowerAlphaException();
        return true;
    }

    /**
     * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
     * @param password - password string to be checked for Sequence requirement
     * @return true if meet Sequence requirement
     * @throws InvalidSequenceException - thrown if does not meet Sequence requirement
     */
    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException
    {
        char c1, c2, c3;
        if (password.length() > 2)
        {
            for (int i = 2; i < password.length(); i++)
            {
                c1 = password.charAt(i - 2);
                c2 = password.charAt(i - 1);
                c3 = password.charAt(i);
                if (c1 == c2 && c2 == c3)
                    throw new InvalidSequenceException();
            }
        }
        return true;
    }

    /**
     * Checks the password SpecialCharacter requirement - Password must contain a Special Character
     * @param password - password string to be checked for SpecialCharacter requirement
     * @return true if meet SpecialCharacter requirement
     * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches())
            throw new NoSpecialCharacterException();
        return true;
    }

    /**
     * Checks the password alpha character requirement - Password must contain an uppercase alpha character
     * @param password - password string to be checked for alpha character requirement
     * @return true if meet alpha character requirement
     * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
     */
    public static boolean hasUpperAlpha (String password) throws NoUpperAlphaException
    {
        Pattern pattern = Pattern.compile(".*[A-Z].*");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new NoUpperAlphaException();
        return true;
    }

    /**
     * Checks the password length requirement - – The password must be at least 6 characters long
     * @param password - password string to be checked for length
     * @return true if meet min length requirement
     * @throws LengthException - thrown if does not meet min length requirement
     */
    public static boolean isValidLength(String password) throws LengthException
    {
        if (password.length() < 6)
            throw new LengthException();
        return true;
    }

    /**
     * Return true if valid password (follows all rules from above), throws an exception if an invalid password
     * @param input - string to be checked for validity
     * @return true if valid password (follows all rules from above)
     * @throws LengthException - thrown if length is less than 6 characters
     * @throws NoUpperAlphaException - thrown if no uppercase alphabetic
     * @throws NoLowerAlphaException - thrown if no lowercase alphabetic
     * @throws NoDigitException - thrown if no digit
     * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
     * @throws InvalidSequenceException - thrown if more than 2 of same character.
     */
    public static boolean isValidPassword(String input) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
    {
        return (isValidLength(input) &&
                hasLowerAlpha(input) &&
                hasUpperAlpha(input) &&
                hasDigit(input) &&
                hasSpecialChar(input) &&
                hasSameCharInSequence(input));
    }

    /**
     * Checks if password is valid but between 6 -9 characters
     * @param input - string to be checked if weak password
     * @return true if length of password is between 6 and 9 (inclusive).
     */
    public static boolean isWeakPassword(String input)
    {

        return hasBetweenSixAndNineChars(input);
    }
}
