package moviePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminIndex {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminIndex window = new AdminIndex();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminIndex() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("관리자모드");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JButton btnAdminHome = new JButton("메인페이지");
		btnAdminHome.setBounds(560, 15, 100, 30);
		btnAdminHome.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnAdminHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnAdminHome);
		btnAdminHome.addActionListener(e -> {
			frame.dispose();
			Index.main(null);
		});
		
		JLabel adminLabel = new JLabel("관리자메뉴");
		adminLabel.setBounds(210, 15, 300, 150);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		
//		JPanel panel = new JPanel();
//		panel.setBounds(170, 132, 338, 314);
		//배경화면
		JLabel backLabel = new JLabel();
		backLabel.setBounds(0, 0, 684, 510);
		backLabel.setLayout(null);
		frame.getContentPane().add(backLabel);
		
		String[] btnName = {"회원관리", "영화관리", "극장관리"};
		for(int i=0,x=32; i<btnName.length; i++, x+=210) {
			JButton btn = new JButton(btnName[i]);
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//btn.setBorder(null);
			btn.setBounds(x, 180, 200, 200);
			btn.setBackground(SystemColor.menu);
			btn.setForeground(new Color(102,102,102));
			btn.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
			btn.setPreferredSize(new Dimension(270, 50));
			btn.addMouseListener(new MenuBtnAction(btn));
			btn.addActionListener(new BtnAction(btnName[i]));
			backLabel.add(btn);
		}
		
		ImageIcon imageIcon = new ImageIcon("./image/LoginBackground.jpg");
		Image image = imageIcon.getImage().getScaledInstance(700, 510, Image.SCALE_SMOOTH);
		backLabel.setIcon(new ImageIcon(image));
		backLabel.add(adminLabel);
		
	}
	private class BtnAction implements ActionListener {

		String btnName;
		
		public BtnAction(String btnName) {
			this.btnName = btnName;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(btnName) {
			case "회원관리" :
				frame.dispose();
				UserList.main(null);
				break;
			case "영화관리" :
				frame.dispose();
				MovieAdmin.main(null);
				break;
			case "극장관리" :
				frame.dispose();
				Theater.main(null);
				break;
			}
		}
	}
	private class MenuBtnAction implements MouseListener{
	
	JButton btn;
	
	public MenuBtnAction(JButton btn) {
		this.btn = btn;
	}
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btn.setBackground(Color.LIGHT_GRAY);
			btn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btn.setBackground(SystemColor.menu);
			btn.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
			
		}
		
	}
}
