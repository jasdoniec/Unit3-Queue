/**
 * An expandable array-based implementation of QueueInterface.
 * 
 * @author (your name)
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
    private static final int DEFAULT_CAPACITY = 1;

    private T[] queue;
    private int front;
    private int rear; // Set to -1 when the queue is empty

    @SuppressWarnings("unchecked")
    public ArrayQueue()
    {
        queue = (T[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = -1;
    }

    /**
     * Calculates the logical size of the queue dynamically using front, rear,
     * and the array capacity.
     */
    public int size()
    {
        // TODO: Calculate and return logical size
        
        if (rear == -1)
            return 0;

        return (rear - front + queue.length) % queue.length + 1;
    }

    public boolean empty()
    {
        // TODO: Return true if queue is empty
        return size()==0;
    }

    public T add(T item)
    {
        // TODO: Add item to the rear of the queue
        
        rear++;
        rear %= queue.length;

        queue[rear] = item;

        checkSize();

        return item;
    }

    public T peek()
    {
        // TODO: Return element at front without removing
        // Throw QueueUnderflowException if empty

        if (empty()){
            throw new QueueUnderflowException();
        }

        return queue[front];
    }

    public T remove()
    {
        // TODO: Remove and return element at front

//        System.out.println("remove");

        T item = peek();
        
        if (size() == 1){
            clear();
            return item;
        }

        queue[front] = null;

        front ++;
        front %= queue.length;

        checkSize();

        return item;
    }

    public void clear()
    {
        // TODO: Reset queue to default initial empty state
        front = 0;
        rear = -1;
        queue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    private void checkSize()
    {
        // TODO: Handle dynamic resizing
        // 1. If full (size() == queue.length), expand capacity (e.g., double length)
        // 2. If sparse (size() < queue.length / 4 && queue.length > DEFAULT_CAPACITY), shrink capacity
        // 3. Re-align remaining elements starting at index 0 of the new array
        // 4. Reset front = 0 and rear = newSize - 1 (or rear = -1 if empty)

        if (size() == queue.length)
            expand();

        if (size() < queue.length / 4 && queue.length > DEFAULT_CAPACITY){
            shrink();
        }

        if (size() == 0){
            front = 0;
            rear = -1;
        }

    }

    private void shrink(){
        T[] newArr = (T[]) new Object[queue.length / 4];

        int size = size();

        for (int i = 0; i < size; i++){
            int pos = front + i;
            pos %= queue.length;
            newArr[i] = queue[pos];
        }
        queue = newArr;
        front = 0;
        rear = size-1;
    }

    private void expand(){
        T[] newArr = (T[]) new Object[queue.length * 2];

        int size = size();

        for (int i = 0; i < size; i++){
            int pos = front + i;
            pos %= queue.length;
            newArr[i] = queue[pos];
        }
        queue = newArr;
        front = 0;
        rear = size-1;
    }
}
