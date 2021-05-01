import java.util.LinkedList;

public class CarQueue
{
    private LinkedList<Integer> numbers;

    public CarQueue()
    {
        numbers = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            numbers.add((int) (Math.random() * 4));
    }

    public void addToQueue()
    {
        class QueueRunnable implements Runnable
        {
            @Override
            public void run()
            {
                while (true)
                {
                    numbers.add((int) (Math.random() * 4));
                    try
                    {
                        Thread.sleep(500);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        QueueRunnable qr = new QueueRunnable();
        Thread thread = new Thread(qr);
        thread.start();
    }

    public int deleteQueue()
    {
        return numbers.remove();
    }
}