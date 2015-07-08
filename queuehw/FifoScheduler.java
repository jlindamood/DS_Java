/*
CS201
Data Structures
by James L, Suhas T.
22 October 2014

Note: Null pointer exceptions that we ran out of time to fix. Pretty sure the rest the philosophy behind the code works and does what is expected.

*/



public class FifoScheduler implements Scheduler {

	ArrayQueue<Process> schedule = new ArrayQueue<Process>();

	// Adds the process mProc to the front of the queue
	public void add(Process mProc) {

		schedule.enqueue(mProc);

	}

	// Returns the process that is at the front of the list, unless it is finished.
	// If finished, it then dequeues the finished item, and returns the next.
	public Process getNext() {

		if (schedule.getFront().isFinished()) {
			schedule.dequeue();
			return schedule.getFront();
		} else {
		return schedule.getFront();
	}

	}

	// Clears the Schedule
	public void clear() {

		schedule.clear();

	}

}