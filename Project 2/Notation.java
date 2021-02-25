/**
 * Provides methods for converting between postfix and infix expressions, and for evaluating postfix expressions.
 * @author Avi Rubinoff
 */
public class Notation
{
    /**
     * Constructor method - I'm not sure what it's for, but the provided Javadoc had one, so here it is.
     */
    public Notation()
    {

    }

    /**
     * Converts an infix expression to a postfix expression.
     * @param infix the infix expression to be converted.
     * @return the converted postfix expression.
     * @throws InvalidNotationFormatException - if the infix expression format is invalid.
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
    {
        NotationStack<Character> stack = new NotationStack<>(infix.length());
        NotationQueue<Character> queue = new NotationQueue<>(infix.length());
       for (int i = 0; i < infix.length(); i++)
       {
           char currentChar = infix.charAt(i);
           if (isDigit(currentChar))
           {
               try
               {
                   queue.enqueue(currentChar);
               }
               catch (QueueOverflowException e) {}
           }
           else if (isLeftParens(currentChar))
           {
               try
               {
                   stack.push(currentChar);
               }
               catch (StackOverflowException e) {}
           }
           else if (isOperator(currentChar))
           {
               try
               {
                   while (isOperator((char) stack.top()) && isHigherPrecedence((char) stack.top(), currentChar))
                       queue.enqueue(stack.pop());
               }
               catch (StackUnderflowException | QueueOverflowException sue){}
               try
               {
                   stack.push(currentChar);
               } catch (StackOverflowException e){}
           }
           else if (isRightParens(currentChar))
           {
               try
               {
                   while(isOperator((char)stack.top()))
                   {
                       try
                       {
                           queue.enqueue(stack.pop());
                       }
                       catch (QueueOverflowException e){}
                       catch (StackUnderflowException e){}
                   }
               }
               catch (StackUnderflowException sue){}
               try
               {
                   if (isLeftParens((char)stack.top()))
                       stack.pop();
                   else throw new InvalidNotationFormatException();
               }
               catch (StackUnderflowException sue)
               {
                   throw new InvalidNotationFormatException();
               }
           }
        }
       while (!stack.isEmpty())
       {
           try
           {
               queue.enqueue(stack.pop());
           }
           catch (Exception e){}
       }
        return queue.toString();
    }

    /**
     * Converts a postfix expression to an infix expression.
     * @param postfix the postfix expression to be converted
     * @return the converted infix expression.
     * @throws InvalidNotationFormatException - if the postfix expression is invalid.
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
    {
        NotationStack<String> stack = new NotationStack<>(postfix.length());
        for (int i = 0; i < postfix.length(); i++)
        {
            char currentChar = postfix.charAt(i);
            if (isDigit(currentChar))
            {
                try
                {
                    String entry = "" + currentChar;
                    stack.push(entry);
                }
                catch (StackOverflowException e){}
            }
            else if (isOperator(currentChar))
            {
                try
                {
                    String s1 = (String) stack.pop();
                    String s2 = (String) stack.pop();
                    String sequence = "(" + s2 + currentChar + s1 + ")";
                    stack.push(sequence);
                }
                catch (StackUnderflowException sue)
                {
                    throw new InvalidNotationFormatException();
                }
                catch (StackOverflowException soe){}
            }
        }
        if (stack.size() != 1)
            throw new InvalidNotationFormatException();
        String result = "";
        try
        {
            result = (String) stack.pop();
        }
        catch (StackUnderflowException sue){}
        return result;
    }

    /**
     * Evaluates a postfix expression from a string to a double.
     * @param expression the postfix expression to evaluate.
     * @return the evaluation of the postfix expression as a double.
     * @throws InvalidNotationFormatException - if the postfix expression format is invalid.
     */
    public static double evaluatePostfixExpression(String expression) throws InvalidNotationFormatException
    {
        NotationStack<String> stack = new NotationStack<>(expression.length());
        for (int i = 0; i < expression.length(); i++)
        {
            char currentChar = expression.charAt(i);
            if (isDigit(currentChar) || isLeftParens(currentChar))
            {
                try
                {
                    String charString = "" + currentChar;
                    stack.push(charString);
                }
                catch(StackOverflowException soe){}
            }
            else if (isOperator(currentChar))
            {
                double d1, d2;
                try
                {
                    String s1 = (String) stack.pop();
                    String s2 = (String) stack.pop();
                    d1 = Double.parseDouble(s1);
                    d2 = Double.parseDouble(s2);
                }
                catch (StackUnderflowException soe)
                {
                    throw new InvalidNotationFormatException();
                }
                double result = 0;
                if (currentChar == '+')
                    result = d2 + d1;
                else if (currentChar == '-')
                    result = d2 - d1;
                else if (currentChar == '*')
                    result = d2 * d1;
                else if (currentChar == '/')
                    result = d2 / d1;
                try
                {
                    String resultString = "" + result;
                    stack.push(resultString);
                } catch (StackOverflowException e){}
            }
        }
        if (stack.size() != 1)
            throw new InvalidNotationFormatException();
        String resultString = "";
        double result = 0;
        try
        {
            resultString = (String) stack.pop();
        }
        catch (StackUnderflowException soe){}
        result = Double.parseDouble(resultString);
        return result;
    }

    /**
     * Private method used by algorithms to determine if a character is a digit.
     * @param input the character to be evaluated.
     * @return true if the character is a digit; false otherwise.
     */
    private static boolean isDigit(char input)
    {
        return ((input == '0')
                || (input == '1')
                || (input == '2')
                || (input == '3')
                || (input == '4')
                || (input == '5')
                || (input == '6')
                || (input == '7')
                || (input == '8')
                || (input == '9'));
    }

    /**
     * Private method used by algorithms to determine if a character is a left parenthesis.
     * @param input the character to be evaluated.
     * @return true if the character is a left parenthesis; false otherwise.
     */
    private static boolean isLeftParens(char input)
    {
        return (input == '(');
    }

    /**
     * Private method used by algorithms to determine if a character is an operator.
     * @param input the character to be evaluated.
     * @return true if the character is an operator; false otherwise.
     */
    private static boolean isOperator(char input)
    {
        return ((input == '+') || (input == '-') || (input == '*') || (input == '/'));
    }

    /**
     * Private method used by algorithms to determine if a given operator is higher in precedence
     * than another operator.
     * @param input1 the first operator.
     * @param input2 the second operator.
     * @return true if input1 is higher in precedence than input2; false otherwise.
     */
    private static boolean isHigherPrecedence(char input1, char input2)
    {
        if (!(isOperator(input1) && isOperator(input2)))
            return false;
        else if (input1 == '*')
            return true;
        else if (input1 == '/')
            return ((input2 == '+') || (input2 == '-'));
        else if (input1 == '+')
            return (input2 == '-');
        else return false;
    }

    /**
     * Private method used by algorithms to determine if a character is a right parenthesis.
     * @param input the character to be evaluated.
     * @return true if the character is a right parenthesis; false otherwise.
     */
    private static boolean isRightParens(char input)
    {
        return (input == ')');
    }

}
