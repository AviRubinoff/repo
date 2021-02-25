import java.util.ArrayList;

/**
 * A stack used in infix/postfix conversion algorithms.
 * @author Avi Rubinoff
 * @param <T> The data type stored by the stack.
 */
public class NotationStack<T> implements StackInterface
{
    private T[] stack;
    private int size;

    /**
     * Creates a NotationStack of size 5.
     */
    public NotationStack()
    {
        stack = (T[]) new Object[5];
        size = 0;
    }

    /**
     * Creates a NotationStack of the specified size.
     * @param sizeIn the size of the NotationStack.
     */
    public NotationStack(int sizeIn)
    {
        stack = (T[]) new Object[sizeIn];
        size = 0;
    }

    /**
     * Creates a NotationStack containing the Strings in a given ArrayList.
     * @param inputStrings the ArrayList whose contents are added to the NotationStack.
     */
    public NotationStack(ArrayList<String> inputStrings)
    {
        size = inputStrings.size();
        ArrayList<String> secureStrings = new ArrayList<>(inputStrings);
        stack = (T[]) new Object[size];
        for (int i = 0; i < size; i++)
            stack[i] = (T) secureStrings.get(i);
    }

    /**
     * Determines if Stack is empty
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * Determines if Stack is full
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull()
    {
        return (size == stack.length);
    }

    /**
     * Deletes and returns the element at the top of the Stack
     * @return the element at the top of the Stack
     */
    @Override
    public Object pop() throws StackUnderflowException
    {
        if (size == 0)
            throw new StackUnderflowException();
        size--;
        Object escapee = stack[size];
        return escapee;
    }

    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     * @return the element at the top of the Stack
     */
    @Override
    public Object top() throws StackUnderflowException
    {
        if (size == 0)
            throw new StackUnderflowException();
        return stack[size - 1];
    }

    /**
     * Number of elements in the Stack
     * @return the number of elements in the Stack
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Adds an element to the top of the Stack
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     */
    @Override
    public boolean push(Object e) throws StackOverflowException
    {
        if (this.isFull())
            throw new StackOverflowException();
        stack[size] = (T) e;
        size++;
        return true;
    }

    /**
     * Returns the elements of the Stack in a string from bottom to top, the beginning
     * of the String is the bottom of the stack
     * @return an string which represent the Objects in the Stack from bottom to top
     */
    @Override
    public String toString()
    {
        String result = "";
            for (int i = 0; i < size; i++)
                result = result + stack[i].toString();
        return result;
    }

    /**
     * Returns the string representation of the elements in the Stack, the beginning of the
     * string is the bottom of the stack
     * Place the delimiter between all elements of the Stack
     * @return string representation of the Stack from bottom to top with elements
     * separated with the delimiter
     */
    @Override
    public String toString(String delimiter)
    {
        String result = stack[0].toString();
        for (int i = 1; i < size; i++)
            result = result + delimiter + stack[i].toString();
        return result;
    }
}
