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
        return front == null;
    }

    public T add(T item)
    {
        // TODO: Create a new LLNode with item
        // consider special case if queue is empty

        LLNode<T> newNode = new LLNode<T>(item);

        size++;

        if (empty()){
            front = newNode;
            rear = newNode;
            return item;
        }

        rear.setLink(newNode);
        rear = newNode;
        return item;
    }

    public T peek()
    {
        // TODO: Return info from front node without removing
        // Throw QueueUnderflowException if empty

        if (empty()){
            throw new QueueUnderflowException();
        }

        return front.getInfo();
    }

    public T remove()
    {
        // TODO: Remove front node and return its info

        T item = peek();

        front = front.getLink();

        size --;

        return item;
    }

    public void clear()
    {
        // TODO: write method
        front = null;
        rear = null;
        size = 0;
    }
}
