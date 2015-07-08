/**********************
 * A class that represents a Process in an operating system.
 * @author exley
 *
 */
public class Process {

  /***********
   * Special static process that means the system is running
   */
  public static final Process SYSTEM_PROCESS = new Process();
  
  private String gName;
  private int gBeginTime;
  private int gRunTime;
  private int gTicks;
  private int gEndTime;

  private Process() {  }

  /*******************
   * Public constructor requires a line of text in the form
   * name begin_time run_time
   * where name is a string, begin_time and run_time are integers.
   * begin_time is the clock tick that the process will enter the scheduling system
   * run_time is the number of clock ticks it takes this process to finish.
   * @param mLine the line of text
   */
  public Process(String mLine) {
    gEndTime = 0;
    String[] tmp = mLine.split(" ");
    try {
      gName = tmp[0];
      gBeginTime = Integer.parseInt(tmp[1]);
      gRunTime = Integer.parseInt(tmp[2]);
    } catch(Exception e) {
      throw new RuntimeException("Syntax error in process file, line: " + mLine);
    }
    gTicks = 0;
  }

  /***************
   * "Spends" one cycle running this process
   */
  public void tick() { gTicks++; }
  
  /****************
   * Resets this Process's tick counter and end time.
   */
  public void reset() { gTicks = 0; gEndTime = 0; }
  
  /****************
   * Gets the number of times this process has ticked
   * @return number of ticks
   */
  public int getTicks() { return gTicks; }

  /****************
   * Gets this process's name
   * @return name
   */
  public String getName() { return gName; }
  
  /*****************
   * Gets this process's name
   */
  public String toString() { return gName; }

  /******************
   * Gets the time that this process is supposed to enter the scheduling system
   * @return the beginning time
   */
  public int getBeginTime() { return gBeginTime; }
  
  /******************
   * Gets the time that this process finished, or 0 if it hasn't finished.
   * @return end time
   */
  public int getEndTime() { return gEndTime; }

  /******************
   * Sets the time at which the process completed. Ideally, this should be public, but
   * processes have no concept of outside time... should change in future versions
   * @param mTime the end time
   */
  public void setEndTime(int mTime) { gEndTime = mTime; }
  
  /******************
   * Returns true if the process has been ticked as many times as is required to complete.
   * @return true if finished
   */
  public boolean isFinished() { return gTicks >= gRunTime; }

  /******************
   * Returns the amount of time that this process needs to run before finishing
   * @return the run time
   */
  public int getRunTime() { return gRunTime; }
}
