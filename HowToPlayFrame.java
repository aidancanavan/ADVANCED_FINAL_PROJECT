import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Displays a jframe for users to learn how to play
 *
 * @author Aidan Canavan
 * @version 1.0
 */
////////////////////////////////////////////////////////////////////////////////
public class HowToPlayFrame extends JFrame
{
    /**
     * Constructor for the JFRAME
     */
    public HowToPlayFrame() {
        super("How to Play");
        setLayout(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,550);
        setVisible(true);

    }
    
    /**
     * Creates the GUI for frame, makes visible
     * @param b A JButton
     * 
     */
    public void createGUI(JButton b){
        b.setEnabled(false);
        setLocationRelativeTo(null);
        JLabel head = new JLabel("How to Play");
        head.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        head.setBounds(150,20,200,60);
        head.setVisible(true);
        head.setLayout(null);
        add(head);

        String c1 = "1)Click the let";
        c1 = c1+ "ters to make words.";

        String c2 = "2)Press enter when you thi";
        c2 = c2+ "nk you have a correct word.";

        String c3 = "3)You win when you find";
        c3 = c3+  " all the correct words.";

        JLabel content1 = new JLabel(c1);
        JLabel content2 = new JLabel(c2);
        JLabel content3 = new JLabel(c3);
        content1.setBounds(10,50,500,200);
        content2.setBounds(10,80,500,200);
        content3.setBounds(10,110,500,200);
        content1.setVisible(true);
        content2.setVisible(true);
        content3.setVisible(true);
        add(content1);
        add(content2);
        add(content3);
        content1.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        content2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        content3.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    b.setEnabled(true);
                }
            });

        JButton closeButton = new JButton("Close Window");
        closeButton.setBounds(200,250,120,40);
        closeButton.addActionListener(new closeListener(b));
        add(closeButton);
    }

    class closeListener implements ActionListener{
        JButton b;
        
        /**
         * Constructor for closeListener
         */
        closeListener(JButton b){
            this.b = b;
        }
        
        /**
         * Sets the buttom to true, then disposes
         * @param e ActionEvent for set enabled
         */
        public void actionPerformed(ActionEvent e){
            b.setEnabled(true);
            dispose();
        }
    }
}
