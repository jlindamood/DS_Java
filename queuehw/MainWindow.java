/*
CS201
Data Structures
by James L, Suhas T.
22 October 2014

Note: Null pointer exceptions that we ran out of time to fix. Pretty sure the rest the philosophy behind the code works and does what is expected.

*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**************************
 * The class that contains all of the other HW4 stuff
 * 
 * Also has main
 * 
 * @author exley
 *
 */
public class MainWindow extends JFrame implements ActionListener, ChangeListener {

  // The scheduler array. Whenever you write a new scheduler, add its name to this array.
  private static final String[] SCHEDULERS = { "FifoScheduler", "LongestJobFirst", "ShortestJobFirst", "ShortestTimeRemaining", "RoundRobin" };

  private static final long serialVersionUID = 1L;
  private JToolBar gToolBar;
  private SimulationComponent gSimComp;
  private JComboBox<SchedulerItem> gSchedulerBox;
  private JToggleButton gRunButton;
  private JButton gLoadButton;
  private Timer gRunTimer;
  
  public static void main(String[] args) {
    MainWindow tWindow = new MainWindow();
    tWindow.setVisible(true);
  }
  
  /*************
   * Simple constructor sets up GUI
   *
   */
  public MainWindow() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // set the window's title
    setTitle("Process Scheduling Simulator");
    
    // set the layout
    setLayout(new BorderLayout());
    
    // add the toolbar
    add(getToolBar(), BorderLayout.NORTH);
    
    // add the simulation component
    add(getSimComp(), BorderLayout.SOUTH);

    // set up the timer
    gRunTimer = new Timer(120, new TimerTranslator(this, "tick"));
    
    this.setResizable(false);
    
    pack();
  }
  
  private JToolBar getToolBar() {
    if(gToolBar == null) {
      gToolBar = new JToolBar();
      
      gLoadButton = new JButton("Load");
      gLoadButton.addActionListener(this);
      gLoadButton.setActionCommand("load");
      gToolBar.add(gLoadButton);
      
      gToolBar.add(new JToolBar.Separator());
      
      gToolBar.add(new JLabel("Scheduler:"));
      gToolBar.add(getSchedulerComboBox());
      
      gToolBar.add(new JToolBar.Separator());
      
      gToolBar.add(getRunButton());
      
      gToolBar.add(new JToolBar.Separator());
      JButton tReset = new JButton("Reset");
      tReset.addActionListener(this);
      tReset.setActionCommand("reset");
      gToolBar.add(tReset);
      gToolBar.add(new JToolBar.Separator());
      
      JButton tQuit = new JButton("Quit");
      tQuit.addActionListener(this);
      tQuit.setActionCommand("quit");
      gToolBar.add(tQuit);
      
      gToolBar.setFloatable(false);
    }
    return gToolBar;
  }
  
  public void halt() {
    gRunButton.setEnabled(true);
    gRunButton.setSelected(false);
    gRunTimer.stop();
  }
  
  private JToggleButton getRunButton() {
    if(gRunButton == null) {
      gRunButton = new JToggleButton("Run");
      gRunButton.addChangeListener(this);
    }
    return gRunButton;
  }
  
  private SimulationComponent getSimComp() {
    if(gSimComp == null) {
      gSimComp = new SimulationComponent(this);
    }
    return gSimComp;
  }

  private JComboBox<SchedulerItem> getSchedulerComboBox() {
    if(gSchedulerBox == null) {
      Vector<SchedulerItem> tVec = new Vector<SchedulerItem>();
      for(String s : SCHEDULERS) {
        try {
          tVec.add(new SchedulerItem(s));
        } catch(RuntimeException e) {
          System.err.println("Unable to add scheduler " + s);
        }
      }    
      gSchedulerBox = new JComboBox<SchedulerItem>(tVec);
      getSimComp().setScheduler(tVec.get(0).getScheduler());
      gSchedulerBox.addActionListener(this);
      gSchedulerBox.setActionCommand("scheduler");
    }
    return gSchedulerBox;
  }
  
  /*************
   * Button event handler
   */
  public void actionPerformed(ActionEvent mEvent) {
    String tmp = mEvent.getActionCommand();
    if(tmp.equals("load")) {
      openFile();
    } else if(tmp.equals("quit")) {
      System.exit(0);
    } else if(tmp.equals("scheduler")) {
      getSimComp().setScheduler(((SchedulerItem)gSchedulerBox.getSelectedItem()).getScheduler());
    } else if(tmp.equals("tick")) {
      getSimComp().tick();
    } else if(tmp.equals("reset")) {
      getSimComp().reset();
    }
  }
  
  /**
   * Open a file.
   */
  private void openFile() {
    JFileChooser c = new JFileChooser(System.getProperty("user.dir", "~"));
    if (c.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      try {
        getSimComp().loadFile(c.getSelectedFile());
      } catch (FileNotFoundException e) {
        System.err.println("Error, cannot load file " + c.getSelectedFile().getName());
        e.printStackTrace();
      }
    }
  }
  
  /*******************************
   * Translate Timer ActionEvents into action events for another listener.
   * @author Michael Ekstrand <ekstrand@cs.umn.edu>
   *
   * In Java 6, the Timer supports action commands.  This is not, however, the
   * case in Java 5.  Therefore, we need this adaptor class to translate timer
   * events into events with commands.
   */
  private class TimerTranslator implements ActionListener {
    private ActionListener delegate;
    private String command;

    public TimerTranslator(ActionListener l, String cmd) {
      delegate = l;
      command = cmd;
    }

    public void actionPerformed(ActionEvent e) {
      ActionEvent e2 = new ActionEvent(e.getSource(), e.getID(), command);
      delegate.actionPerformed(e2);
    }
  }

  /********************
   * Private inner class to help dynamically instantiate Schedulers that 
   * are defined in the array at the top of this class.
   * @author exley
   *
   */
  private class SchedulerItem {
    private String gName;
    private Scheduler gScheduler;
    public String toString() { return gName; }
    
    @SuppressWarnings("unchecked")
    public SchedulerItem(String mName) {
      gName = mName;
      try {    
        gScheduler = (Scheduler)((Class.forName(mName)).newInstance());
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new RuntimeException("Error, cannot find class " + mName, e);
      } catch (InstantiationException e) {
        throw new RuntimeException("Error, cannot instantiate class " + mName, e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Error, cannot access constructor of " + mName, e);
      }
    }
    
    public Scheduler getScheduler() {
      return gScheduler;
    }
  }

  /***********************
   * Run button toggle handler
   */
  public void stateChanged(ChangeEvent mEvent) {
    Object obj = mEvent.getSource();
    if(obj == gRunButton) {
      onRunToggled();
    }
  }
  
  /*****************
   * Run button toggle handler helper
   */
  private void onRunToggled() {
    if(gRunButton.isSelected()) {
      gLoadButton.setEnabled(false);
      gSchedulerBox.setEnabled(false);
      gRunTimer.start();
    } else {
      gLoadButton.setEnabled(true);
      gSchedulerBox.setEnabled(true);
      gRunTimer.stop();
    }
  }
}
