/*******************
 * Simple priority queue class
 * @author exley
 *
 * @param <T> the type to use
 */
public interface PriorityQueueInterface<T extends Comparable<? super T>> {
  public void add(T mItem);
  
  public T remove();
  
  public T getFront();
  
  public boolean isEmpty();

  public void clear();
  
}
