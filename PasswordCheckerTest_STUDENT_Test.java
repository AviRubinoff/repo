
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT_Test {

	ArrayList<String> badPasswordList;
	String goodPassword = "1234abCD!!";
	String tooShortPassword = "12345";
	String noUpperAlphaPassword = "1234abcd!!";
	String noLowerAlphaPassword = "1234ABCD!!";
	String invalidSequencePassword = "12344444444444";
	String noDigitPassword = "abcdabCD!!";
	String noSpecialCharacterPassword = "abCD123456";
	String weakPassword = "abCD1!";

	@Before
	public void setUp() throws Exception
	{
		String[] p = {"12345", "abcd1234"};
		badPasswordList = new ArrayList<String>();
		badPasswordList.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception
	{
		badPasswordList = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidLength(goodPassword));
			assertFalse(PasswordCheckerUtility.isValidLength(tooShortPassword));
		}
		catch (LengthException le)
		{
			le.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasUpperAlpha(goodPassword));
			assertFalse(PasswordCheckerUtility.hasUpperAlpha(noUpperAlphaPassword));
		}
		catch (NoUpperAlphaException nuae)
		{
			nuae.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(goodPassword));
			assertFalse(PasswordCheckerUtility.hasLowerAlpha(noLowerAlphaPassword));
		}
		catch (NoLowerAlphaException nlae)
		{
			nlae.printStackTrace();
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertTrue(PasswordCheckerUtility.isWeakPassword(weakPassword));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence(goodPassword));
			assertFalse(PasswordCheckerUtility.hasSameCharInSequence(invalidSequencePassword));
		}
		catch (InvalidSequenceException ise)
		{
			ise.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasDigit(goodPassword));
			assertFalse(PasswordCheckerUtility.hasDigit(noDigitPassword));
		}
		catch (NoDigitException nde)
		{
			nde.printStackTrace();
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword(goodPassword));
		}
		catch (NoLowerAlphaException e)
		{
			e.printStackTrace();
		} catch (LengthException e)
		{
			e.printStackTrace();
		} catch (NoDigitException e)
		{
			e.printStackTrace();
		} catch (NoUpperAlphaException e)
		{
			e.printStackTrace();
		} catch (NoSpecialCharacterException e)
		{
			e.printStackTrace();
		} catch (InvalidSequenceException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords()
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(badPasswordList);
		assertEquals(results.size(), 2);
		assertEquals("12345 - The password must be at least 6 characters long", results.get(0));
		assertEquals("abcd1234 - The password must contain at least one uppercase alphabetic character", results.get(1));
	}
	
}
