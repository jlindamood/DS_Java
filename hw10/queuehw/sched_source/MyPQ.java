public class MyPQ<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

  private class Node {
    T data;
    Node next;
    public Node(T d, Node n) {
      data = d;
      next = n;
    }
  }

  private Node front;

  public MyPQ() {
    front = null;
  }

  public void add(T item) {
    if(front == null || item.compareTo(front.data) < 0) {
      front = new Node(item, front);
    } else {
      Node ptr = front;
      while(ptr.next != null) {
        if(item.compareTo(ptr.next.data) < 0) {
          break;
        }
        ptr = ptr.next;
      }
      ptr.next = new Node(item, ptr.next);
    }
  }

  public T remove() {
    T data = front.data;
    front = front.next;
    return data;
  }

  public T getFront() {
    return front.data;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public void clear() {
    front = null;
  }
}
