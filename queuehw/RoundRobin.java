/*
CS201
Data Structures
by James L, Suhas T.
22 October 2014

Note: Null pointer exceptions that we ran out of time to fix. Pretty sure the rest the philosophy behind the code works and does what is expected.

*/


@SuppressWarnings("unchecked")
public class RoundRobin implements Scheduler {

	public class QueueItem implements Comparable<QueueItem> {

		private Process proc;

		public QueueItem(Process p) {
			this.proc = p;
		}

		public Process getProcess() {
			return this.proc;
		}

		public int compareTo(QueueItem item) {
			if (this.getProcess().getRunTime() > item.getProcess().getRunTime()) {
				return 1;
			} else if (this.getProcess().getRunTime() < item.getProcess().getRunTime()) {
				return -1;
			} else {
				return 0;
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
	 *
	 * The getNext() of RoundRobin is different in that it checks if the process is finished, but if it is not,
	 * will still switch out the process after a 4 ticks.
	 *
	 */
	public Process getNext() {

		if (this.prioq.getFront().getProcess().isFinished()) {
			this.prioq.remove();
			return this.prioq.getFront().getProcess();
			/*
			* This else if checks if the # of times the process has been ticked is a multiple of 4.
			* 4 is the size of the quantum. If a quantum has passed, it will pass to the next process,
			* and take the current process and take it to the end of the queue.
			*/
		} else if (this.prioq.getFront().getProcess().getTicks() % 4 == 0) {
			QueueItem item = this.prioq.remove();
			this.prioq.add(item);
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