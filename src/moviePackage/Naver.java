package moviePackage;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Naver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Naver window = new Naver();
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
	public Naver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String naver="./image/userInput/naver1.png";
		ImagePanel naverimage=new ImagePanel(new ImageIcon(naver).getImage());
		frame.setPreferredSize(naverimage.getDim());
		frame.getContentPane().add(naverimage);
		
		JButton btnNewButton = new JButton("<-"); // -뒤로가기 버튼- 
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInput.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(0, 0, 53, 41);
		naverimage.add(btnNewButton);
		frame.pack();
	}

}
