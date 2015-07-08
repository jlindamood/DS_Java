public interface StackInterface<T> {
		
  /***************
   * Adds an item to the top of this stack.
   * @param item
   */
  public void push(T item);

  /**************
   * Removes and returns the item from the top of this stack. 
   * @return the top of the stack
   */
  public T pop();
	  
  /**************
   * Returns the item on top of the stack, without removing it
   * @return the top of the stack
   */
  public T peek();
	
  public boolean isEmpty();
	
  public void clear();
}
