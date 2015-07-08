/*
 * CS201
 * Edited by James Lindamood (added Average Wait Time display)
 * 27 October 2014
 */



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComponent;
import javax.swing.JPanel;

/***********************
 * SimulationComponent is the class
 * that holds most of the GUI information about this 
 * Process Scheduling Simulator
 * 
 * @author exley
 *
 */
public class SimulationComponent extends JComponent {

  private static final long serialVersionUID = 1L;
  private Scheduler gCurrentScheduler;
  private ArrayList<Process> gProcList;
  private int gTicks;
  private File gFile;
  private Process gPreviousProc;
  private GraphicsComponent gGraphicsComp;
  private StatsComponent gStatsComp;

  private MainWindow gParentWindow;

  public SimulationComponent(MainWindow mWindow) {
    gParentWindow = mWindow;
    this.setMinimumSize(new Dimension(500, 425));
    this.setPreferredSize(new Dimension(500, 425));

    this.setLayout(new BorderLayout());

    gGraphicsComp = new GraphicsComponent();

    JPanel tPanel = new JPanel();
    tPanel.setLayout(new BorderLayout());
    tPanel.add(gGraphicsComp, BorderLayout.CENTER);

    this.add(tPanel, BorderLayout.NORTH);

    gStatsComp = new StatsComponent();
    this.add(gStatsComp, BorderLayout.SOUTH);

    gProcList = new ArrayList<Process>();
    gTicks = 0;
  }

  /********************
   * Loads a file as a process list
   * @param mFile the file to load
   * @throws FileNotFoundException
   */
  public void loadFile(File mFile) throws FileNotFoundException {
    gProcList.clear();
    gPreviousProc = null;
    gParentWindow.halt();
    gCurrentScheduler.clear();
    gTicks = 0;
    gGraphicsComp.init();
    gStatsComp.init();
    Process.SYSTEM_PROCESS.reset();
    gFile = mFile;
    Scanner tScanner = new Scanner(gFile);
    while(tScanner.hasNextLine()) {
      String tmp = tScanner.nextLine();
      if(tmp.length()>0 && tmp.charAt(0) != '#') { 
        try {
          gProcList.add(new Process(tmp)); 
        } catch(RuntimeException e) {
          System.err.println(e.getMessage());
        }
      }
      gGraphicsComp.setProcList(gProcList);
    }
  }

  /*******************
   * Resets the graphics, lists, processes, stats, etc.
   *
   */
  public void reset() {
    if(gFile != null) { 
      try { loadFile(gFile); }
      catch (FileNotFoundException e) { e.printStackTrace(); } 
    } else {
      gProcList.clear();
      gPreviousProc = null;
      gParentWindow.halt();
      gCurrentScheduler.clear();
      gTicks = 0;
      gGraphicsComp.init();
      gStatsComp.init();
      Process.SYSTEM_PROCESS.reset();
    }
  }

  /******************
   * Attempts to run the computer for 1 clock cycle.
   * Asks the current scheduler for which process to run, attempts to tick() that process.
   *
   */
  public void tick() {
    int completedcounter = 0; //                                                                                    // Number of processes completed
    int turnaround = 0;
    int waittime = 0; //                                                                                            // Time to first tick / (# processes). Added by James Lindamood
    for(Process p : gProcList) {
      if(p.getTicks() == 1) { p.setFirstProcessTick(gTicks); }                                                      // Sets process variable of time when it was first ticked. Added by James Lindamood
      if(p.getBeginTime() == gTicks) { gCurrentScheduler.add(p); }
      if(p.isFinished()) { 
        completedcounter++;
        turnaround += (p.getEndTime() - p.getBeginTime());
        waittime += p.getFirstProcessTick(); //                                                                     // Keeps track of the added, cumulative first ticked times.
      }
    }
    Process p = gCurrentScheduler.getNext();
    if(p == null) {
      gGraphicsComp.setTime(gTicks, Process.SYSTEM_PROCESS);
      Process.SYSTEM_PROCESS.tick();
      gTicks++;
      gStatsComp.setTicks(gTicks);
      gStatsComp.setNumJobsFinished(completedcounter);
      gStatsComp.setTurnaround(completedcounter==0?0:(double)turnaround/(double)completedcounter);
      gStatsComp.setThroughput((double) completedcounter * 100.0 / (double) gTicks);
      gStatsComp.setCPUUtilization(100.0 * (double)(gTicks - Process.SYSTEM_PROCESS.getTicks()) / (double) gTicks);
      gStatsComp.setAverageWaitTime( (double) waittime / (double) completedcounter ); //                             // Reports the average wait time. Added by James Lindamood.
      gParentWindow.halt();
    } else {
      if(gPreviousProc != null && !p.equals(gPreviousProc)) {
        gGraphicsComp.setTime(gTicks, Process.SYSTEM_PROCESS);
        Process.SYSTEM_PROCESS.tick();
      } else {
        p.tick();
        gGraphicsComp.setTime(gTicks, p);
      }
      gTicks++;
      if(p.isFinished()) { p.setEndTime(gTicks); }
      gStatsComp.setTicks(gTicks);
      gStatsComp.setNumJobsFinished(completedcounter);
      gStatsComp.setTurnaround(completedcounter==0?0:(double)turnaround/(double)completedcounter);
      gStatsComp.setThroughput((double) completedcounter * 100.0 / (double) gTicks);
      gStatsComp.setCPUUtilization(100.0 * (double)(gTicks - Process.SYSTEM_PROCESS.getTicks()) / (double) gTicks);
      gStatsComp.setAverageWaitTime( (double) waittime / (double) completedcounter ); //                            // Reports the average wait time. Added by James Lindamood.
      gPreviousProc = p;
    }
  }

  /****************
   * Sets the scheduler to use
   * @param mScheduler the scheduler to use
   */
  public void setScheduler(Scheduler mScheduler) {
    if(gCurrentScheduler != null && gCurrentScheduler != mScheduler) { reset(); }
    gCurrentScheduler = mScheduler;
  }
}
