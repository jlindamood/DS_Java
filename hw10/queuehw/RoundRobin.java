public class RoundRobin implements Scheduler {

  private MyQueue<Process> q;
  private static final int QUANTUM = 10;
  private int tickCounter;

  public RoundRobin() {
    this.q = new MyQueue<Process>();
    this.tickCounter = 0;
  }

  public void add(Process p) { q.enqueue(p); }

  public Process getNext() {
    tickCounter++;
    if(tickCounter >= QUANTUM) {
      q.enqueue(q.dequeue());
      tickCounter = 0;
    }
    while(!q.isEmpty() && q.getFront().isFinished()) {
      q.dequeue();
    }
    if(q.isEmpty()) {
      return null;
    } else {
      return q.getFront();
    }
  }

  public void clear() { q.clear(); }

}
