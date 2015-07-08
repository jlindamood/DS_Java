/*
 * CS201
 * Edited by James Lindamood (added Average Wait Time display)
 * 27 October 2014
 */


import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.Border;

/************************************
 * This class holds all of the interesting stats information
 * about the Process Scheduling Simulator
 * @author exley
 *
 */
public class StatsComponent extends JComponent {

  private static final long serialVersionUID = 1L;

  private JLabel gNTField;
  private JLabel gTTField;
  private JLabel gTPField;
  private JLabel gNJCField;
  private JLabel gCPUField;
  private JLabel gAWTField; // Initializes the Average Wait Time field to display on the GUI. Added by James Lindamood.
  private DecimalFormat gFormat;
  
  public StatsComponent() {
    this.setMinimumSize(new Dimension(400, 125));
    this.setPreferredSize(new Dimension(400, 125));
    
    this.setLayout(new GridLayout(5,1));
    Border b = BorderFactory.createEmptyBorder(0, 10, 0, 0);
    
    gNTField = new JLabel();
    gNTField.setBorder(b);
    this.add(gNTField);
    
    gTTField = new JLabel();
    gTTField.setBorder(b);
    this.add(gTTField);
    
    gTPField = new JLabel();
    gTPField.setBorder(b);
    this.add(gTPField);
    

    gNJCField = new JLabel();
    gNJCField.setBorder(b);
    this.add(gNJCField);
    
    gCPUField = new JLabel();
    gCPUField.setBorder(b);
    this.add(gCPUField);

    gAWTField = new JLabel();  // Initializes the Average Wait Time field to display on the GUI. Added by James Lindamood.
    gAWTField.setBorder(b);    //
    this.add(gAWTField);       //
    
    gFormat = new DecimalFormat("#.####");
    
    init();
  }
  
  public void init() {
    setTicks(0);
    setTurnaround(0);
    setThroughput(0);
    setNumJobsFinished(0);  
    setCPUUtilization(0);
    setAverageWaitTime(0); // Sets the default average wait time to 0. Added by James Lindamood.
  }
  
  public void setTicks(int mTicks) { gNTField.setText("Number of cycles: " + mTicks); }
  public void setTurnaround(double mTT) { gTTField.setText("Average Turnaround Time: " + gFormat.format(mTT)); }
  public void setThroughput(double mTP) { gTPField.setText("Throughput: " + gFormat.format(mTP)); }
  public void setNumJobsFinished(int mNJC) { gNJCField.setText("Number of Jobs completed: " + mNJC); }
  public void setCPUUtilization(double mCPU) { gCPUField.setText("CPU Utilization: " + gFormat.format(mCPU) + "%");}
  public void setAverageWaitTime(double mAWT) { gAWTField.setText("Average Wait Time: " + gFormat.format(mAWT)); } // Method to display the average wait time on the GUI. Added by James Lindamood.
}
