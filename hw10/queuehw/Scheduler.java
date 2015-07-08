/****************************
 * An interface for any scheduler
 * @author exley
 *
 */
public interface Scheduler {

	/****************
	 * Adds mProc into the scheduling system.
	 * @param mProc
	 */
	public void add(Process mProc);
	
	/*****************
	 * Returns the process that should be run in the next clock tick, or null if 
	 * there are no pending processes
	 * @return
	 */
	public Process getNext();
	
	/*****************
	 * clears all processes in this scheduler.
	 */
	public void clear();
}
