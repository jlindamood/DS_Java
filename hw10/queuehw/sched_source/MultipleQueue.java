public class MultipleQueue implements Scheduler {

  private MyQueue<Process> q2;
  private MyQueue<Process> q4;
  private MyQueue<Process> q8;
  private MyQueue<Process> q16;

  private int tickCounter;

  public MultipleQueue() {
    q2 = new MyQueue<Process>();
    q4 = new MyQueue<Process>();
    q8 = new MyQueue<Process>();
    q16 = new MyQueue<Process>();
    this.tickCounter = 0;
  }

  public void clear() {
    q2.clear();
    q4.clear();
    q8.clear();
    q16.clear();
  }

  public void add(Process p) {
    q2.enqueue(p);
  }

  public Process getNext() {
    Process retProc = null;
    tickCounter++;
    while(!q2.isEmpty() && q2.getFront().isFinished()) {
      q2.dequeue();
      tickCounter = 0;
    }
    if(!q2.isEmpty()) {
      if(tickCounter <= 2) {
        retProc = q2.getFront();
      } else {
        q4.enqueue(q2.dequeue());
        tickCounter = 0;
        if(!q2.isEmpty()) {
          retProc = q2.getFront();
        }
      }
    }

    while(!q4.isEmpty() && q4.getFront().isFinished()) {
      q4.dequeue();
      tickCounter = 0;
    }
    if(retProc == null && !q4.isEmpty()) {
      if(tickCounter <= 4) {
        retProc = q4.getFront();
      } else {
        q8.enqueue(q4.dequeue());
        tickCounter = 0;
        if(!q4.isEmpty()) {
          retProc = q4.getFront();
        }
      }
    }

    while(!q8.isEmpty() && q8.getFront().isFinished()) {
      q8.dequeue();
      tickCounter = 0;
    }
    if(retProc == null && !q8.isEmpty()) {
      if(tickCounter <= 8) {
        retProc = q8.getFront();
      } else {
        q16.enqueue(q8.dequeue());
        tickCounter = 0;
        if(!q8.isEmpty()) {
          retProc = q8.getFront();
        }
      }
    }

    while(!q16.isEmpty() && q16.getFront().isFinished()) {
      q16.dequeue();
      tickCounter = 0;
    }
    if(retProc == null && !q16.isEmpty()) {
      if(tickCounter <= 16) {
        retProc = q16.getFront();
      } else {
        q16.enqueue(q16.dequeue());
        tickCounter = 0;
        retProc = q16.getFront();
      }
    }
    return retProc;
  }
}
