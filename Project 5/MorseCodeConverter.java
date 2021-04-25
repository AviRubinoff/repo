import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class containing static methods to convert Morse code to English.
 * @author Avi Rubinoff
 */
public class MorseCodeConverter
{
    public static MorseCodeTree tree = new MorseCodeTree();

    /**
     * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
     * @param codeFile the file containing the Morse code to be converted.
     * @return the English translation of the file.
     * @throws FileNotFoundException
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException
    {
        Scanner reader = new Scanner(codeFile);
        String morseString = reader.nextLine();
        return convertToEnglish(morseString);
    }

    /**
     * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
     * @param code
     * @return
     */
    public static String convertToEnglish(String code)
    {
        String result = "";
        while (code.length() > 0)
        {
            if (!result.equals("Invalid input"))
            {
                String letter;
                int firsSpace = code.indexOf(' ');
                if (firsSpace != -1)
                {
                    letter = code.substring(0, firsSpace);
                    code = code.substring(firsSpace + 1);
                } else
                {
                    letter = code;
                    code = "";
                }
                if (!letter.equals("/"))
                {
                    String englishLetter = tree.fetch(letter);
                    if (englishLetter.equals("Invalid input"))
                        return englishLetter;
                    else result = result + englishLetter;
                }
                else result = result + " ";
            }
        }
        return result;
    }

    /**
     * Returns a String with all the data in the tree in LNR order, with an space between each element. Uses the
     * toArrayList method in MorseCodeTree It should return the data in this order:
     * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
     * Note the extra space between j and b - that is because there is an empty String that is the root, and in the LNR
     * traversal, the root would come between the right most child of the left tree (j) and the left most child of the
     * right tree (b). This is used for testing purposes to make sure the MorseCodeTree has been built properly.
     * @return the data in the tree in LNR order separated by a space.
     */
    public static String printTree()
    {
        String result = "";
        for (String s: tree.toArrayList())
            result = result + " " + s;
        return result;
    }
}
