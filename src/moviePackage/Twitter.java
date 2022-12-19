package moviePackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Twitter {

   private JFrame frame;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Twitter window = new Twitter();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public Twitter() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 700, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBounds(0, 10, 684, 351);
      String twitter= "./image/userInput/twitter1.png";
      ImagePanel twitterImage= new ImagePanel(new ImageIcon(twitter).getImage());
      twitterImage.setBounds(0, 0, 494, 809);
      frame.setPreferredSize(twitterImage.getDim());
      frame.getContentPane().add(twitterImage);
      twitterImage.setLayout(null);
      
      JButton btnNewButton = new JButton("<-");   // -뒤로가기 버튼- 
      btnNewButton.setBackground(Color.WHITE);
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
      btnNewButton.setBorder(null);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.dispose();
         }
      });
      btnNewButton.setBounds(4, 5,30, 30);
      btnNewButton.setForeground(Color.blue);
      twitterImage.add(btnNewButton);
      frame.pack();
      frame.getContentPane().add(panel);
   }

}