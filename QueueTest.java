/**
 * Test runner for ArrayQueue and LinkedQueue implementations.
 */
public class QueueTest
{
    private static boolean failed = false;

    public static void main(String[] args)
    {
        System.out.println("-> Queue Project Tester <-\n");

        testQueue("ArrayQueue", new ArrayQueue<Integer>());
        boolean arrayFailed = failed;

        failed = false;
        testQueue("LinkedQueue", new LinkedQueue<Integer>());
        boolean linkedFailed = failed;

        System.out.println("--------------------------------------------------");
        if (arrayFailed || linkedFailed)
        {
            System.out.println("Result: Bummer, the Queue assignment is incomplete. Try again.");
        }
        else
        {
            System.out.println("Result: Success! You have completed the Queue assignment.");
        }
    }

    private static void testQueue(String className, QueueInterface<Integer> queue)
    {
        System.out.println("Testing " + className + ":");

        // 1. Initial State
        if (queue.size() != 0 || !queue.empty())
        {
            failure(className + " failed: Initial queue must be empty with size 0.");
            return;
        }

        // 2. Underflow Exception Checks
        try
        {
            queue.peek();
            failure(className + " failed: peek() on empty queue did not throw QueueUnderflowException.");
            return;
        }
        catch (QueueUnderflowException e) { /* Expected */ }

        try
        {
            queue.remove();
            failure(className + " failed: remove() on empty queue did not throw QueueUnderflowException.");
            return;
        }
        catch (QueueUnderflowException e) { /* Expected */ }

        // 3. Single Element Operations
        queue.add(100);
        if (queue.size() != 1 || queue.empty() || queue.peek() != 100)
        {
            failure(className + " failed: Error during single add/peek.");
            return;
        }

        if (queue.remove() != 100 || queue.size() != 0 || !queue.empty())
        {
            failure(className + " failed: Error during single remove. Queue should be empty.");
            return;
        }

        // 4. Fixed-Capacity Wrap-Around Test (Checks rear < front modulo logic)
        System.out.println("  - Testing wrap-around behavior...");
        for (int i = 1; i <= 8; i++)
        {
            queue.add(i); // Fill 8 elements
        }

        for (int i = 1; i <= 5; i++)
        {
            if (queue.remove() != i) // Remove 5 elements (front advances)
            {
                failure(className + " failed: Incorrect value returned during wrap-around setup.");
                return;
            }
        }

        // Add 5 more elements (forces rear to wrap around to front of array)
        for (int i = 9; i <= 13; i++)
        {
            queue.add(i);
        }

        if (queue.size() != 8)
        {
            failure(className + " failed: Dynamic size calculation incorrect during wrap-around. Expected size 8, got " + queue.size());
            return;
        }

        // Drain wrapped elements and verify FIFO order
        for (int expected = 6; expected <= 13; expected++)
        {
            if (queue.remove() != expected)
            {
                failure(className + " failed: Order corrupted during wrap-around dequeue.");
                return;
            }
        }

        // 5. Expansion & Bulk Operations Test
        System.out.println("  - Testing dynamic array expansion...");
        for (int i = 1; i <= 500; i++)
        {
            queue.add(i);
        }

        if (queue.size() != 500)
        {
            failure(className + " failed: Incorrect size after bulk insertions.");
            return;
        }

        for (int i = 1; i <= 500; i++)
        {
            if (queue.remove() != i)
            {
                failure(className + " failed: Incorrect FIFO ordering during bulk removal.");
                return;
            }
        }

        // 6. Clear Test
        for (int i = 0; i < 50; i++)
        {
            queue.add(i);
        }
        queue.clear();

        if (queue.size() != 0 || !queue.empty())
        {
            failure(className + " failed: clear() did not reset queue to empty state.");
            return;
        }

        System.out.println("  Passed all " + className + " tests!\n");
    }

    private static void failure(String message)
    {
        System.out.println("*** " + message);
        failed = true;
    }
}