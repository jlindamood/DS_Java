/***************
 * A simple interface to describe a generic-type Queue
 * @author Andy
 *
 * @param <T> the type of data the queue uses
 */
public interface QueueInterface<T> {
  
  /************
   * Adds the given item to the end of the Queue
   * @param item the item to add
   */
  public void enqueue(T item);
  
  /************
   * Removes an item from the front of the Queue and returns it
   * @return the item or null, if empty
   */
  public T dequeue();
  
  /************
   * Returns true if the Queue is empty
   * @return true if the Queue is empty
   */
  public boolean isEmpty();
  
  /************
   * Gets the item on the front of the Queue (without removing)
   * @return the item or null, if empty
   */
  public T getFront();
  
  /************
   * Clears the items in the queue
   */
  public void clear();
}
