public class LongestJobFirst implements Scheduler {

	public class QueueItem implements Comparable<QueueItem> {

		private Process proc;

		public QueueItem(Process p) {
			this.proc = p;
		}

		public Process getProcess() {
			return this.proc;
		}

		public int compareTo(QueueItem item) {
			if (this.getRunTime() > item.getRunTime()) {
				return 1;
			} else if (this.getRunTime() < item.getRunTime()) {
				return -1;
			} else {
				return 0;
			}
		}


}
}