import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Write a description of class HowToPlayFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HowToPlayFrame extends JFrame
{
    public HowToPlayFrame() {
    super("How to Play");
    setLayout(null);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600,550);
    setVisible(true);
    
    }
    
    public void createGUI(JButton b){
    b.setEnabled(false);
    setLocationRelativeTo(null);
    JLabel head = new JLabel("How to Play");
    head.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    head.setBounds(150,20,200,60);
    head.setVisible(true);
    head.setLayout(null);
    add(head);
    
    JLabel content1 = new JLabel("1)Click the letters to make words.");
    JLabel content2 = new JLabel("2)Press enter when you think you have a correct word.");
    JLabel content3 = new JLabel("3)You win when you find all the correct words.");
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
        closeListener(JButton b){
        this.b = b;
        }
        public void actionPerformed(ActionEvent e){
            b.setEnabled(true);
            dispose();
        }
    }
}
