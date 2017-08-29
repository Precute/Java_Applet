import java.awt.*;
import java.applet.*;
public class exBarChart extends Applet

{  int hddValue=55;
    int camValue=80;
    int laptopValue=60;
    int ramValue=25;
    int dvdValue=70;
    
  public void paint(Graphics g)
  {
    setBackground(new Color(214, 147, 189));
   Color c1 = new Color(156, 0, 99);
    g.setColor(c1);
  
    g.drawLine(100,100,100,500);
    g.drawLine(100,500,700,500);
    g.fillRect(150,hddValue*4,50,500-(hddValue*4));
    g.fillRect(250,camValue*4,50,500-(camValue*4));
    g.fillRect(350,laptopValue*4,50,500-(laptopValue*4));
    g.fillRect(450,ramValue*4,50,500-(ramValue*4));
    g.fillRect(550,dvdValue*4,50,500-(dvdValue*4));
   
  }
}