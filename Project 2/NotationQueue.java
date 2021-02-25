import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A queue used in infix/postfix conversion algorithms.
 * @author Avi Rubinoff
 * @param <T> The data type stored by the queue.
 */
public class NotationQueue<T> implements QueueInterface
{
    private T[] queue;
    private int size;

    /**
     * Creates a NotationQueue of size 5.
     */
    public NotationQueue()
    {
        queue = (T[]) new Object[5];
        size = 0;
    }

    /**
     * Creates a NotationQueue of the specified size.
     * @param sizeIn the size of the NotationQueue.
     */
    public NotationQueue(int sizeIn)
    {
        queue = (T[]) new Object[sizeIn];
        size = 0;
    }

    /**
     * Creates a NotationQueue containing the Strings in a given ArrayList.
     * @param inputStrings the ArrayList whose contents are added to the NotationQueue.
     */
    public NotationQueue(ArrayList<String> inputStrings)
    {
        size = inputStrings.size();
        ArrayList<String> secureStrings = new ArrayList<>(inputStrings);
        queue = (T[]) new Object[size];
        for (int i = 0; i < size; i++)
            queue[i] = (T) secureStrings.get(i);
    }


    /**
     * Checks whether the NotationQueue is empty.
     * @return true if the NotationQueue is empty; false if it is not empty.
     */
    @Override
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * Checks whether the NotationQueue is full.
     * @return true if the NotationQueue is full; false if it is not full.
     */
    @Override
    public boolean isFull()
    {
        return (size == queue.length);
    }

    /**
     * A method for obtaining the number of items in the NotationQueue.
     * @return the number of items in the NotationQueue.
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Deletes and returns the element at the front of the Queue
     * @return the element at the front of the Queue
     */
    @Override
    public Object dequeue() throws QueueUnderflowException
    {
        if (size == 0)
            throw new QueueUnderflowException();

        Object escapee = queue[0];
        for (int i = 1; i < size; i++)
            queue[i-1] = queue[i];
        size--;

        return escapee;
    }

    /**
     * Adds an element to the end of the Queue
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful, false if not
     */
    @Override
    public boolean enqueue(Object e) throws QueueOverflowException
    {
        if (this.isFull())
            throw new QueueOverflowException();
        else
        {
            queue[size] = (T) e;
            size++;
            return true;
        }
    }

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString()
    {
        String result = "";
        for (int i = 0; i < size; i++)
            result = result + queue[i].toString();
        return result;
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     * @return string representation of the Queue with elements separated with the delimiter
     */
    @Override
    public String toString(String delimiter)
    {
        String result = queue[0].toString();
        for (int i = 1; i < size; i++)
            result = result + delimiter + queue[i].toString();
        return result;
    }
}
