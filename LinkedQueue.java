/**
 * A linked-list implementation of QueueInterface using LLNode[cite: 12, 16].
 * 
 * @author (your name)
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
    private LLNode<T> front;
    private LLNode<T> rear;
    private int size;

    public LinkedQueue()
    {
        front = null;
        rear = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean empty()
    {
        // TODO: Return true if queue is empty
        return false;
    }

    public T add(T item)
    {
        // TODO: Create a new LLNode with item
        // consider special case if queue is empty
        return null;
    }

    public T peek()
    {
        // TODO: Return info from front node without removing
        // Throw QueueUnderflowException if empty
        return null;
    }

    public T remove()
    {
        // TODO: Remove front node and return its info

        return null;
    }

    public void clear()
    {
        // TODO: write method
    }
}
