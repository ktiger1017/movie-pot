package moviePackage;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Kakao {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kakao window = new Kakao();
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
	public Kakao() {
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
		String kakao= "./image/userInput/kakao1.png";
		ImagePanel kakaoImage= new ImagePanel(new ImageIcon(kakao).getImage());
		kakaoImage.setBounds(0, 0, 494, 809);
		frame.setPreferredSize(kakaoImage.getDim());
		frame.getContentPane().add(kakaoImage);
		kakaoImage.setLayout(null);
		
		JButton btnNewButton = new JButton("<-");   // -뒤로가기 버튼- 
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(12, 10, 55, 30);
		kakaoImage.add(btnNewButton);
		frame.pack();
		frame.getContentPane().add(panel);
	}

}
