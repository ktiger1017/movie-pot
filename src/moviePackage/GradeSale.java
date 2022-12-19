package moviePackage;

import javax.swing.*;
import java.awt.*;

public class GradeSale {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeSale window = new GradeSale();
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
	public GradeSale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.BOLD, 15));
		frame.setTitle("회원등급별 할인 이벤트(진행중)");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JLabel HeadLabel = new JLabel("");
		HeadLabel.setIcon(new ImageIcon("./image/Head.png"));
		HeadLabel.setBounds(-172, 0, 856, 104);
		frame.getContentPane().add(HeadLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(-12, 92, 696, 409);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel GradeImageLabel = new JLabel("");
		GradeImageLabel.setBounds(2, 0, 118, 87);
		panel.add(GradeImageLabel);
		GradeImageLabel.setIcon(new ImageIcon("./image/grade.png"));
		
		JLabel SilverImageLabel = new JLabel("New label");
		SilverImageLabel.setIcon(new ImageIcon("./image//Silver.png"));
		SilverImageLabel.setBounds(214, 0, 128, 87);
		panel.add(SilverImageLabel);
		
		JLabel GoldImageLabel = new JLabel("New label");
		GoldImageLabel.setIcon(new ImageIcon("./image/Gold.png"));
		GoldImageLabel.setBounds(332, 0, 128, 87);
		panel.add(GoldImageLabel);
		
		JLabel VipImageLabel = new JLabel("New label");
		VipImageLabel.setIcon(new ImageIcon("./image/Vip.png"));
		VipImageLabel.setBounds(457, 0, 135, 87);
		panel.add(VipImageLabel);
		
		JLabel VvipImageLabel = new JLabel("New label");
		VvipImageLabel.setIcon(new ImageIcon("./image/Vvip.png"));
		VvipImageLabel.setBounds(574, 0, 120, 87);
		panel.add(VvipImageLabel);
		
		JLabel SaleImageLabel = new JLabel("New label");
		SaleImageLabel.setIcon(new ImageIcon("./image/Sale.png"));
		SaleImageLabel.setBounds(2, 83, 118, 130);
		panel.add(SaleImageLabel);
		
		JLabel NewImageLabel = new JLabel("New label");
		NewImageLabel.setIcon(new ImageIcon("./image/new.png"));
		NewImageLabel.setBounds(120, 10, 98, 77);
		panel.add(NewImageLabel);
		
		JLabel NewLabel = new JLabel("할인 x");
		NewLabel.setBackground(Color.GRAY);
		NewLabel.setForeground(Color.GREEN);
		NewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		NewLabel.setBounds(120, 94, 98, 106);
		panel.add(NewLabel);
		
		JLabel SilverLabel = new JLabel("5% 할인");
		SilverLabel.setForeground(Color.CYAN);
		SilverLabel.setBackground(Color.BLACK);
		SilverLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		SilverLabel.setBounds(228, 92, 121, 106);
		panel.add(SilverLabel);
		
		JLabel GoldLabel = new JLabel("10% 할인");
		GoldLabel.setForeground(Color.PINK);
		GoldLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		GoldLabel.setBounds(343, 92, 121, 106);
		panel.add(GoldLabel);
		
		JLabel VipLabel = new JLabel("20% 할인");
		VipLabel.setForeground(Color.ORANGE);
		VipLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		VipLabel.setBounds(461, 94, 121, 106);
		panel.add(VipLabel);
		
		JLabel VvipLabel = new JLabel("30% 할인");
		VvipLabel.setForeground(Color.RED);
		VvipLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		VvipLabel.setBounds(574, 92, 121, 106);
		panel.add(VvipLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 210, 610, 68);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel TextLabel1 = new JLabel("");
		TextLabel1.setIcon(new ImageIcon("./image/Text1.png"));
		TextLabel1.setBounds(12, 10, 474, 33);
		panel_1.add(TextLabel1);
		
		JLabel TextLabel2 = new JLabel("");
		TextLabel2.setIcon(new ImageIcon("./image/Text2.png"));
		TextLabel2.setBounds(12, 43, 445, 25);
		panel_1.add(TextLabel2);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("./image/Black.png"));
		lblNewLabel_11.setBounds(143, 277, 552, 149);
		panel.add(lblNewLabel_11);
		
		JLabel ImageLabel = new JLabel("New label");
		ImageLabel.setIcon(new ImageIcon("./image/images.jpg"));
		ImageLabel.setBounds(2, 277, 146, 149);
		panel.add(ImageLabel);
		
		JButton btnBack = new JButton("닫기");
		btnBack.setBounds(610, 360, 60, 30);
		btnBack.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnBack.addActionListener(e -> {
			frame.dispose();
			//Index.main(null);
		});
		panel.add(btnBack);
	}
}
