public class ShortestTimeRemaining implements Scheduler {

  private MyPQ<QueueItem> pq;

  public ShortestTimeRemaining() {
    pq = new MyPQ<QueueItem>();
  }

  public void clear() {
    pq.clear();
  }

  private class QueueItem implements Comparable<QueueItem> {

    private Process proc;
    public QueueItem(Process p) {
      this.proc = p;
    }

    public Process getProc() {
      return this.proc;
    }

    public int compareTo(QueueItem item) {
      return this.proc.getRunTime() - item.getProc().getRunTime();
    }

  }

  public void add(Process mProc) {
    pq.add(new QueueItem(mProc));
  }

  public Process getNext() {
    while(!pq.isEmpty() && pq.getFront().getProc().isFinished()) {
      pq.remove();
    }
    if(pq.isEmpty()) {
      return null;
    } else {
      return pq.getFront().getProc();
    }
  }

}
