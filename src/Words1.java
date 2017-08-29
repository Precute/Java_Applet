


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
// for median sort; import java.util.Arrays;

/**
 *
 * @author Huseyin Arpalikli - 13153439
 */
public class Words1 extends Applet implements ActionListener {
    Button analyzeButton;
    Button resetButton;
    TextArea textArea;
    int[] lengthFreq = new int[100];
    String[] words;
    boolean isButtonPressed = false;

    public void init() {
// Set applet font details
        setFont(new Font("Helvetica", Font.PLAIN, 12));
// Declare new elements on the applet canvas
        analyzeButton = new Button("Analyze");
        resetButton = new Button("Reset");
        textArea = new TextArea("Enter text to analyze", 10, 100,TextArea.SCROLLBARS_VERTICAL_ONLY);
        add(textArea);
        add(analyzeButton);
        add(resetButton);

// Attach actions to the components 
        analyzeButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

// Here we will show the results of our actions 
    public void paint(Graphics g) {
// This sets the canvas size            
        setSize(1300, 750);
        setBackground(Color.WHITE);
        setInitialsPrint(g);
        if (isButtonPressed) {
            getWordLengths(lengthFreq, g);
            String mean = "Mean word length of entered text is: " + getMeanWordLength(words);
            g.drawString(mean, 180, 220);
            setBarChart(lengthFreq, g);
            // String median = "Median word length of entered text is: " + medianWordLength(lengthFreq);
            // medianWordLength(lengthFreq);
            // g.drawString(median, 180, 235);
        }
    }

    // When a button is clicked this method will be called
    // All actions specified here.
    public void actionPerformed(ActionEvent evt) {
// Here we will ask what component called this method 
// Actions of the analyzeButton
        if (evt.getSource() == analyzeButton) {
            isButtonPressed = true;
            lengthFreq = new int[lengthFreq.length];
            repaint();
        } // Actions of the resetButton
        else if (evt.getSource() == resetButton) {
// Resets textField to initial state
            textArea.setText("Enter text to analyze");
            lengthFreq = new int[lengthFreq.length];
            isButtonPressed = false;
            repaint();
        }

    }

    public void setInitialsPrint(Graphics initials) {
        initials.setColor(Color.RED);
        int xH[] = {1140, 1140, 1150, 1150, 1180, 1180, 1190, 1190, 1180, 1180, 1150, 1150};
        int yH[] = {660, 740, 740, 710, 710, 740, 740, 660, 660, 690, 690, 660};
        initials.fillPolygon(xH, yH, xH.length);

        initials.setColor(Color.BLUE);
        int xA[] = {1230, 1200, 1215, 1220, 1240, 1245, 1260};
        int yA[] = {660, 740, 740, 720, 720, 740, 740};
        initials.fillPolygon(xA, yA, xA.length);

        initials.setColor(Color.WHITE);
        int xA1[] = {1220, 1240, 1230};
        int yA1[] = {710, 710, 685};
        initials.fillPolygon(xA1, yA1, xA1.length);
        initials.setColor(Color.BLACK);
    }

    public void getWordLengths(int[] lengthFreq, Graphics g) {
        String text = textArea.getText();
        text = text.replaceAll("'", "");
        words = text.split("\\P{L}+");
        //int i;

        for (String word : words) {
            if (word.length() < lengthFreq.length) {
                lengthFreq[word.length()] += 1;
            }
        }
// Set positions for string outputs 
        int x = 20;
        int y = 220;
// Loop that goes through the array used in lengthAnalysis() method 
// Prints out the word length strings            
        for (int i = 1; i < lengthFreq.length; i++) {
            while (lengthFreq[i] != 0) {
                g.drawString(lengthFreq[i] + " words of length " + i, x, y);
                y += 15;
                i++;
            }
        }
        /* for (i = 1; i < lengthFreq.length; i++) {
         while (lengthFreq[i] != 0) {
         System.out.println(lengthFreq[i] + " words of length " + i);
         i++;
         }
         }*/
    }

    private double getMeanWordLength(String words[]) {
        double totalWords;
        double wordLengthSum = 0;
        for (String word : words) {

            wordLengthSum += word.length();
        }

        totalWords = words.length;
        return wordLengthSum / totalWords;
    }

    /*private double getMedianWordLength(int words[]) {
       
     Arrays.sort(word);
     int middle = i / 2;
     if (i % 2 == 1) {
     return word[middle];
     } else {
     return (word[middle - 1] + word[middle]) / 2.0;
     }
     }*/
    public void setBarChart(int[] lengthFreq, Graphics g) {
        double highestFreq = 0;
        double longestLength = 0;
        double heightScale=0;
        double widthScale=0;
        for (int i = 0; i < lengthFreq.length; i++) {

            if (lengthFreq[i] > 0) {
                longestLength = i;
                if (highestFreq < lengthFreq[i]) {
                    highestFreq = lengthFreq[i];
                }
            }

        }
        heightScale = 400 / highestFreq; //calculate percentage height
        widthScale = 500 / longestLength; //calculate percentage width
        g.drawString("Word Length", 520, 740);
        g.drawLine(300, 300, 300, 700);//y line
        g.drawLine(300, 700, 900, 700);//x line
        double xStart = /*(i * 50) +*/ 300;
        for (int i = 1; i <= longestLength; i++) {
            while (lengthFreq[i] != 0) {

                double yStart = 700 - (lengthFreq[i] * heightScale);
                double yBar = lengthFreq[i] * heightScale;
                //System.out.println(lengthFreq[i]);
                //int yVal = lengthFreq

                g.setColor(Color.BLUE);
                g.fillRect((int)xStart, (int)yStart, (int)widthScale, (int)yBar);

                g.drawString("    " + i, (int)xStart, 720);
                g.drawString("    " + lengthFreq[i], (int)xStart-10, (int)yStart - 5);
                xStart += widthScale + 5;
                i++;
            }
        }
    }
}
