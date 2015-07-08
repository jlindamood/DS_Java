/*
CS201
Data Structures
by James L, Suhas T.
22 October 2014

Note: Null pointer exceptions that we ran out of time to fix. Pretty sure the rest the philosophy behind the code works and does what is expected.

*/


@SuppressWarnings("unchecked")
public class ShortestTimeRemaining implements Scheduler {

	public class QueueItem implements Comparable<QueueItem> {

		private Process proc;

		public QueueItem(Process p) {
			this.proc = p;
		}

		public Process getProcess() {
			return this.proc;
		}

		public int compareTo(QueueItem item) {
			if (this.getProcess().getRunTime() - this.getProcess().getTicks() < item.getProcess().getRunTime() - item.getProcess().getTicks()) {
				return 1;
			} else if (this.getProcess().getRunTime() - this.getProcess().getTicks() < item.getProcess().getRunTime() - item.getProcess().getTicks()) {
				return 0;
			} else {
				return -1;
			}
		}

		public void clear() {

		}

	}

	LinkedPQ<QueueItem> prioq = new LinkedPQ<QueueItem>();

	/****************
	 * Adds mProc into the scheduling system.
	 * @param mProc
	 */
	
	public void add(Process mProc) {

		QueueItem mItem = new QueueItem(mProc);

		this.prioq.add(mItem);

	}
	
	/*****************
	 * Returns the process that should be run in the next clock tick, or null if 
	 * there are no pending processes
	 * @return
	 */
	public Process getNext() {

		if (this.prioq.getFront().getProcess().isFinished()) {
			this.prioq.remove();
			return this.prioq.getFront().getProcess();
		} else {
		return this.prioq.getFront().getProcess();
	}

	}
	
	/*****************
	 * clears all processes in this scheduler.
	 */
	public void clear() {

		LinkedPQ newPrioq = new LinkedPQ();
		prioq = newPrioq;

	}


}