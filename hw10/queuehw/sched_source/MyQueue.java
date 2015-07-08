import java.util.LinkedList;
public class MyQueue<T> implements QueueInterface<T> {

  private LinkedList<T> q;

  public MyQueue() { q = new LinkedList<T>(); }

  public void clear() { q.clear(); }

  public boolean isEmpty() { return q.isEmpty(); }

  public T getFront() { return q.get(0); }

  public T dequeue() { return q.remove(0); }

  public void enqueue(T item) { q.add(item); }

}
