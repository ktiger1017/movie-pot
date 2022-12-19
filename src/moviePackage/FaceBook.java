package moviePackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FaceBook {
   private JFrame frame;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               FaceBook window = new FaceBook();
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
   public FaceBook() {
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
      String FaceBook= "./image/userInput/face1.png";
      ImagePanel FaceBookImage= new ImagePanel(new ImageIcon(FaceBook).getImage());
      FaceBookImage.setBounds(0, 0, 494, 809);
      frame.setPreferredSize(FaceBookImage.getDim());
      frame.getContentPane().add(FaceBookImage);
      FaceBookImage.setLayout(null);
      
      JButton btnNewButton = new JButton("<-");   // -뒤로가기 버튼- 
      btnNewButton.setBackground(Color.WHITE);
//      btnNewButton.setForeground(Color.BLACK);
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
      btnNewButton.setBorder(null);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.dispose();
         }
      });
      btnNewButton.setBounds(455, 5,30,30);
      btnNewButton.setForeground(Color.blue);
      FaceBookImage.add(btnNewButton);
      frame.pack();
      frame.getContentPane().add(panel);
   }

}