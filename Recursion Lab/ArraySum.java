public class ArraySum
{
    public ArraySum(){}

    public int sumOfArray(Integer[] a, int index)
    {
        if (index == 0)
            return a[0];
        else
        {
            Integer[] b = new Integer[index];
            for (int i = 0; i < b.length; i++)
                b[i] = a[i];
            return a[index] + sumOfArray(b, index - 1);
        }
    }
}
