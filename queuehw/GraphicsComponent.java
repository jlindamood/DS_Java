import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/************************
 * GraphicsComponent is the part of the SimulationComponent that 
 * keeps track of the graphical representation of the VM memory
 * @author Andy
 *
 */
public class GraphicsComponent extends JComponent {

  private static final long serialVersionUID = 1L;
  private ArrayList<Process> gProcList;
  private static final Color[] COLORS = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.ORANGE, Color.MAGENTA};
  private Color[] gTime;
  private final int GRID_SIZE_X = 40;
  private final int GRID_SIZE_Y = 14;
  private Process gCurrent;

  /*********
   * Constructor
   */
  public GraphicsComponent() {
    this.setMinimumSize(new Dimension(450, 300));
    this.setPreferredSize(new Dimension(450, 300));
    this.setVisible(true);
    gTime = new Color[GRID_SIZE_X * GRID_SIZE_Y];
  }

  /************
   * Overriding paintComponent method.
   */
  public void paintComponent(Graphics mGraphics) {
    Graphics2D g2 = (Graphics2D) mGraphics;
    int GRID_WIDTH = 8;
    int GRID_HEIGHT = 20;
    g2.drawLine(0, 0, 500, 0);
    g2.drawString("CPU RUN OVER TIME", 10, 15);

    // draw grid
    int time = 0;
    int counter = 0;
    for(int i=0; i < GRID_SIZE_Y; i++) {
      for(int j=0; j < GRID_SIZE_X; j++) {
        g2.setColor(Color.WHITE);
        if(gTime[counter] != null) { g2.setColor(gTime[counter]); }
        else if(time == 0) { time = counter; }
        g2.fillRect(10 + GRID_WIDTH*j, 20 + GRID_HEIGHT*i, GRID_WIDTH-1, GRID_HEIGHT-2);
        counter++;
      }
    }

    // draw process list
    g2.setColor(Color.BLACK);
    g2.drawLine(345, 0, 345, 300);
    g2.drawString("PROCESS LIST", 350, 15);
    if(gProcList != null) {
      for(int i=0; i < gProcList.size(); i++) {
        g2.setColor(COLORS[i % COLORS.length]);
        Process p = gProcList.get(i);
        g2.drawString(p.getName(), 370, 30 + 15*i);
        if(p.isFinished()) {
          g2.setColor(Color.BLACK);
          g2.drawString("x", 480, 30+15*i);
        }
        g2.setColor(Color.BLACK);
        if(p.getBeginTime() < time) {
          g2.drawString(">", 350, 30+15*i);
        } else {
          g2.drawString("" + p.getBeginTime(), 350, 30+15*i);
        }
        if(p.equals(gCurrent)) {
          g2.setColor(Color.BLACK);
          g2.drawRect(348, 18+15*i, 140, 15);
        }
      }
    }
  }

  /**************
   * Resets the time grid and clears the process list
   */
  public void init() {
    gTime = new Color[GRID_SIZE_X * GRID_SIZE_Y];
    if(gProcList != null) gProcList.clear();
    repaint();
  }

  /******************
   * Sets this GraphicsComponent's process list
   * @param mList the list to set it to
   */
  public void setProcList(ArrayList<Process> mList) { gProcList = mList; repaint(); }
  
  /*****************
   * Sets the process p that is run at time mTime 
   * @param mTime the time to set
   * @param p the process run at the given time
   */
  public void setTime(int mTime, Process p) { 
    gCurrent = p;
    if(p.equals(Process.SYSTEM_PROCESS)) { gTime[mTime] = Color.DARK_GRAY; }
    else { gTime[mTime] = COLORS[gProcList.indexOf(p) % COLORS.length]; }
    repaint();
  }
}
