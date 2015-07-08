public class ShortestJobFirst implements Scheduler {

  private Process currentProc;

  private class QueueItem implements Comparable<QueueItem> {

    Process proc;

    public QueueItem(Process p) {
      proc = p;
    }

    public int compareTo(QueueItem q) {
      return proc.getRunTime() - q.proc.getRunTime();
    }
  }

  private MyPQ<QueueItem> pq;

  public ShortestJobFirst() { 
    pq = new MyPQ<QueueItem>(); 
    currentProc = null;
  }

  public void add(Process p) { pq.add(new QueueItem(p)); }

  public Process getNext() {
    if(currentProc == null || currentProc.isFinished()) {
      while(!pq.isEmpty() && pq.getFront().proc.isFinished()) {
        pq.remove();
      }
      currentProc = pq.isEmpty()? null : pq.getFront().proc;
    }
    return currentProc;
  }

  public void clear() { pq.clear(); }

}
