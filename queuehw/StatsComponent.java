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
    
    gFormat = new DecimalFormat("#.####");
    
    init();
  }
  
  public void init() {
    setTicks(0);
    setTurnaround(0);
    setThroughput(0);
    setNumJobsFinished(0);  
    setCPUUtilization(0);
  }
  
  public void setTicks(int mTicks) { gNTField.setText("Number of cycles: " + mTicks); }
  public void setTurnaround(double mTT) { gTTField.setText("Average Turnaround Time: " + gFormat.format(mTT)); }
  public void setThroughput(double mTP) { gTPField.setText("Throughput (jobs/100 cycles): " + gFormat.format(mTP)); }
  public void setNumJobsFinished(int mNJC) { gNJCField.setText("Number of Jobs completed: " + mNJC); }
  public void setCPUUtilization(double mCPU) { gCPUField.setText("CPU Utilization: " + gFormat.format(mCPU) + "%");}
}
