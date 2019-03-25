import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.io.*;
import java.util.Collections;

/**
 * Implements graphic user inferface for testtwist
 *
 * @author Aidan Canavan
 * @version 1
 */
////////////////////////////////////////////////////////////////////////////////
public class TextTwistGUI extends JFrame
{
    public String input = "";
    public String letters = "abcdef";
    public JLabel word;
    public JLabel time;
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    public int correctWords = 0;
    public TextTwist myTextTwist;
    public JLabel gameStatus;
    public ArrayList<String> displayList = new ArrayList<String>();
    public ArrayList<JLabel> displayLabels= new ArrayList<JLabel>();;
    public String ID = "#";
    public JButton enter;
    public JButton random;
    public JButton clear;
    public JButton help;
    int globalTime = -1;
    Timer myTimer;
    /**
     * TexTwistGUI constructor
     * @param fileName String that is name of text file to read from
     * @throws FileNotFoundExpception
     * @throws IOException
     */
    TextTwistGUI(String fileName)throws FileNotFoundException,IOException{

        super("Text Twist");
        myTextTwist = new TextTwist(fileName);
        letters = myTextTwist.letters;
        setLayout(null);
        this.letters = letters;
        getContentPane().setBackground(new Color(51,204,255));

        for(int k = 0;k<myTextTwist.wordNumbers.length;k++){
            if(myTextTwist.wordNumbers[k] !=0){
                for(int m=0;m<myTextTwist.wordNumbers[k];m++){
                    String tempo ="";
                    for(int g =0;g<k;g++){
                        tempo +=ID;

                    }
                    displayList.add(tempo);
                }
            }
        }
        //System.out.println(displayList);
        int count =0;
        for(String str:displayList){
            count++;
            JLabel n = new JLabel(str);
            n.setBounds(0,40*count,100,30);
            n.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            n.setVisible(true);
            n.setLayout(null);
            add(n);
            displayLabels.add(n);
        }
        //timer below
        myTimer = new Timer(1000,new TimerListener());

        myTimer.start();
        time = new JLabel(Integer.toString(globalTime));
        time.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        time.setBounds(100,10,250,80);
        time.setVisible(true);
        time.setLayout(null);
        add(time);
        //help button
        help = new JButton("Help");
        help.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        help.setBounds(450,30,100,50);
        help.addActionListener(new HelpListener());
        add(help);
        //Title image

        JLabel title = new JLabel("Text Twist");
        title.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        title.setBounds(200,10,250,80);
        title.setForeground(Color.BLUE);
        title.setVisible(true);
        title.setLayout(null);
        add(title);

        //End of title image
        for(int i =0;i<letters.length();i++){
            String it = letters.substring(i,i+1);
            JButton b =  new JButton(it);
            buttons.add(b);
            b.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            b.addActionListener(new ButtonListener(it,i));
            b.setBounds(200 + (i*50),250,50,50);
            add(b);
        }
        //Random Button
        random =  new JButton("Randomize");
        random.setBounds(letters.length()*50 -150 +250,300+10+60,100,50);

        random.addActionListener(new RandomListener());

        add(random);
        //Random Button

        //clear button
        clear =  new JButton("Clear");
        clear.addActionListener(new ClearListener());
        clear.setBounds(200,300+10,100,50);
        add(clear);
        //clear button

        //enter button
        enter =  new JButton("Enter");
        enter.addActionListener(new EnterListener());

        enter.setBounds(letters.length()*50 -150 +250,300+10,100,50);
        add(enter);
        //enter button

        //JLabel for input
        word = new JLabel(input);
        word.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        word.setBounds(200,150,300,100);
        add(word);
        word.setLayout(null);
        word.setVisible(true);

        //need a game label to determine game status! Write below

        gameStatus = new JLabel("");
        gameStatus.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        gameStatus.setBounds(200,350,300,100);
        gameStatus.setForeground(Color.RED);
        add(gameStatus);
        gameStatus.setLayout(null);
        gameStatus.setVisible(true);
    }

    //public void playGame(String fileName)throws FileNotFoundException{
    //}
    class TimerListener implements ActionListener{
        /**
         * Empty constructor
         */
        public TimerListener(){}
        
        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        public void actionPerformed(ActionEvent e){
            globalTime++;
            time.setText(Integer.toString(globalTime));
        }

    }

    class ButtonListener implements ActionListener{
        public String text ="";
        public int index;
        /**
         * Buttomlistener constructor
         */
        ButtonListener(String str,int ind){
            text = str;index =ind;
        }

        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals(text)){
                buttons.get(index).setEnabled(false);
                input +=text;
                //System.out.println(input);
                word.setText(input);
            }
        }
    }

    class RandomListener implements ActionListener{
        /**
         * Empty constructor
         */
        RandomListener(){}

        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        @Override
        public void actionPerformed(ActionEvent e){
            //clear first
            //System.out.println(letters);
            input = "";
            for(JButton f:buttons){
                f.setEnabled(true);
            }
            //System.out.println("Cleared");
            word.setText(input);

            //delete vuttons
            for(JButton b: buttons){
                b.setVisible(false);
            }
            //clear button arraylist
            buttons.clear();

            //shuffle letters
            ArrayList<String> lett = new ArrayList<String>();
            for(int i=0;i<letters.length();i++){
                lett.add(letters.substring(i,i+1));

            }
            Collections.shuffle(lett);
            String temp =letters;
            letters="";
            for(int p=0;p<temp.length();p++){
                letters = letters+lett.get(p);
            }

            //System.out.println(letters);
            //make new buttons with new letters
            for(int i =0;i<letters.length();i++){
                String it = letters.substring(i,i+1);
                JButton b =  new JButton(it);
                buttons.add(b);
                b.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                b.addActionListener(new ButtonListener(it,i));
                b.setBounds(200 + (i*50),250,50,50);
                add(b);
            }

        }

    }
    class HelpListener implements ActionListener{
        /**
         * Empty constructor
         */
        HelpListener(){

        }

        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        @Override
        public void actionPerformed(ActionEvent e){
            HowToPlayFrame h = new HowToPlayFrame();
            h.createGUI(help);
        }
    }
    class ClearListener implements ActionListener{
        /**
         * Empty constructor
         */
        ClearListener(){}

        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        @Override
        public void actionPerformed(ActionEvent e){
            input = "";
            for(JButton f:buttons){
                f.setEnabled(true);
            }
            //System.out.println("Cleared");
            word.setText(input);
        }
    }

    class EnterListener implements ActionListener{
        /**
         * Empty constructor
         */
        EnterListener(){

        }

        /**
         * Declares an action performed
         * @param e ActionEvent to use
         */
        @Override
        public void actionPerformed(ActionEvent e){
            String guess = input;

            if(myTextTwist.inputGameValueForGUI(guess)){
                gameStatus.setForeground(Color.BLUE);
                gameStatus.setText(input+" was a good input.");
                //search through display list for a string that matches
                //replace it with guess
                //remove visibility to all JLabels
                //create new ones over top
                String insert = "";
                for(int h =0;h<guess.length();h++){
                    insert+=ID;
                }
                displayList.set(displayList.indexOf(insert),guess);
                for(JLabel j: displayLabels){
                    j.setVisible(false);
                }

                displayLabels.clear();
                int countt =0;
                for(String str:displayList){
                    countt++;
                    JLabel n = new JLabel(str);
                    n.setBounds(0,40*countt,100,30);
                    n.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    n.setVisible(true);
                    n.setLayout(null);
                    add(n);
                    displayLabels.add(n);
                }

            }
            else{
                gameStatus.setForeground(Color.RED);
                gameStatus.setText("*Bad input*");
            }

            if(!myTextTwist.endCondition()){
                myTimer.stop();
                gameStatus.setForeground(Color.BLACK);
                gameStatus.setText("You win in: "+globalTime+"s");
                enter.setEnabled(false);
                clear.setEnabled(false);
                random.setEnabled(false);
                for(JLabel j: displayLabels){
                    j.setForeground(Color.BLUE);
                }
                for(JButton f:buttons){
                    f.setEnabled(false);
                }
            }
            //check end condition!!!!!
            //below does exactly what the clear method does to finish things
            input = "";
            if(myTextTwist.endCondition()){
                for(JButton f:buttons){
                    f.setEnabled(true);
                }
            }
            //System.out.println("Cleared");
            word.setText(input);
        }
    }

    /**
     * Runs JFRAME
     */
    public void run(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,700);
        setVisible(true);
    }

    public static void main(String[] args) 
    throws FileNotFoundException, IOException{
        TextTwistGUI tt = new TextTwistGUI(args[0]);
        tt.run();

    }
}

