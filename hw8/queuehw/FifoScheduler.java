public class FifoScheduler implements Scheduler {

	String[] Schedule = new ArrayQueue<Process>();

	public void add(Process mProc) {

		Schedule.enqueue(mProc);

	}

	public Process getNext() {
		
		return Schedule.dequeue();

	}

	public void clear() {

		Schedule.clear();

	}

}