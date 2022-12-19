package moviePackage;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Information {

   private JFrame frame;

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Information window = new Information();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   public Information() {
      initialize();
   }

   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 700, 550);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JPanel InfoPaenl = new JPanel();
      InfoPaenl.setBounds(56, 10, 581, 464);
      frame.getContentPane().add(InfoPaenl);
      InfoPaenl.setLayout(null);
      
      JLabel ImageLabel1 = new JLabel("");
      ImageLabel1.setIcon(new ImageIcon("./image/userInput/checkImage.png"));
      ImageLabel1.setBounds(190, 10, 219, 46);
      InfoPaenl.add(ImageLabel1);
      
      JLabel ImageLabel2 = new JLabel("");
      ImageLabel2.setIcon(new ImageIcon("./image/userInput/information1.png"));
      ImageLabel2.setBounds(64, 85, 465, 383);
      InfoPaenl.add(ImageLabel2);
      
      JButton btnNewButton = new JButton("뒤로가기");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.dispose();
         }
      });
      btnNewButton.setBounds(489, 484, 100, 23);
      frame.getContentPane().add(btnNewButton);
   }

}