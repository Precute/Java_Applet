/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author precute
 */
public class AppletWork extends Applet implements ActionListener {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    boolean buttonClicked = false;
    String[] txt;
    String mean;
    String median;
    TextArea txtEntry;
    Button analyzeButton;
    Button resetButton;
    Button filesButton;
    int[] txtFreq = new int[50];

    public void init() {
        // TODO start asynchronous download of heavy resources
        analyzeButton = new Button("Analyze");
        resetButton = new Button("Refresh");
        filesButton = new Button("Files");
        txtEntry = new TextArea("Please Enter Text Here", 20, 50, txtEntry.SCROLLBARS_VERTICAL_ONLY);
        add(analyzeButton);
        add(resetButton);
        add(filesButton);
        add(txtEntry);
        analyzeButton.addActionListener(this);
        resetButton.addActionListener(this);
        filesButton.addActionListener(this);
    }

    public void paint(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);
        setSize(1400, 1000);

        g.setColor(Color.magenta);
        g.fillOval(1220, 910, 60, 60);

        g.setColor(Color.white);
        g.fillOval(1235, 925, 30, 30);

        g.setColor(Color.MAGENTA);
        int x2[] = {1310, 1310, 1320, 1320, 1310, 1310, 1340, 1340, 1330, 1330, 1340, 1340};
        int y2[] = {910, 920, 920, 960, 960, 970, 970, 960, 960, 920, 920, 910};
        g.fillPolygon(x2, y2, x2.length);






        // for(int i=txtFreq.length; i<8; i++){   // i IS REFERED TO THE NUMBERS OF ROWS WHICH IS 8 (0-7)
        // for (int j =0; j<8; j++){ // J IS REFERED TO THE NUMBERS OF COLUMN WHICH IS 8 (0-7)

        //X AND Y ARE THE AXIS THAT FOR THE SQUARE AND I HAVE SET IT TO 25
        //int x = j*25;                
        //int y = i*25;
        //if ((i%2)==(j%2))
        //   g.setColor(Color.BLACK);
        //else
        //  g.setColor(Color.white);
        // g.fillRect(x, y, 25, 25);



        if (buttonClicked) {
            //repaint();

            calculateLength(g);
            mean = "Mean word length of entered text is: " + meanTxtLength(txt);
            g.setColor(Color.MAGENTA);
            g.drawString(mean, 15, 75);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == analyzeButton) {
            buttonClicked = true;
            txtFreq = new int[txtFreq.length];

            // median = "Median word length of entered text is: " + medianTxtLength(txt);
            repaint();

        } else if (ae.getSource() == resetButton) {
            txtEntry.setText("Please Enter Text Here");
            txtFreq = new int[txtFreq.length];
            buttonClicked = false;
            repaint();
        }
    }

    public void calculateLength(Graphics g) {
        String text = txtEntry.getText();
        text = text.replaceAll("'", "");
        txt = text.split("\\P{L}+");

        for (String word : txt) {
            if (word.length() < txtFreq.length) {
                txtFreq[word.length()] += 1;
                //System.out.println(txtFreq[word.length()] + "word = " + word);
            }


        }

        int x = 15;
        int y = 100;
        for (int i = 1; i < txtFreq.length; i++) {
            while (txtFreq[i] != 0) {
                g.setColor(Color.MAGENTA);
                g.drawString(txtFreq[i] + " words of length " + i, x, y);
                y += 20;
                i++;

            }
        }
        // the co-ordinates (x,y axis) for lines on the graph the graph 
        g.drawLine(150, 400, 150, 750);
        g.drawLine(150, 750, 700, 750);

        // declaring new varibles to check for the longest word and the highest frequency
        double xBar = 160;
        double yBar;
        double yAxis;
        //int width = 30;
        double highFreq = 0;
        double longWord = 0;
        double width;
        double height;

        // this loop runs to check the hightest length of word   

        for (int i = 1; i < txtFreq.length; i++) {
            if (txtFreq[i] > 0) {
                longWord = i;
                if (highFreq < txtFreq[i]) {
                    highFreq = txtFreq[i];
                }
            }
        }

        //sets my graph to a scale, so it won't extends out of the graph area
        height = 300 / highFreq;
        width = 250 / longWord;
        //lebel the x-axis
        g.drawString("The Word Length", 300, 780);
        for (int j = 1; j <= longWord; j++) {
            while (txtFreq[j] != 0) {
                yAxis = (750 - (txtFreq[j] * height));
                yBar = height * txtFreq[j]; // txtFreq[i]*scale

                Color c1 = new Color(156, 0, 99);
                g.setColor(c1);
                g.drawRect((int) xBar, (int) yAxis, (int) width, (int) yBar);
                g.drawString("    " + j, (int) xBar, 760);
                xBar += width + 15;
                j++;


            }
            // g.fillRect(150,hddValue*4,50,500-(hddValue*4));
        }
    }

    private double meanTxtLength(String txt[]) {
        double totalTxt;
        double totalLength = 0;
        for (String word : txt) {
            totalLength += word.length();

        }
        totalTxt = txt.length;
        return totalLength / totalTxt;
    }
}
