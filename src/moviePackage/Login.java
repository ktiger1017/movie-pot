package moviePackage;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class Login {
	
	public static String loginID;
	public static String loginName;
	public static String loginPWD;
	public static String loginTel;
	public static String loginAdress;
	
	private JFrame frame;
	private JTextField txtID;
	private JPasswordField txtPassword;
	private JButton btnNewJoin;
	
	//관리자 ID, Password
	private String adminID = "admin";
	private String adminPWD = "1234";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//첫화면 프레임
		frame = new JFrame();
		frame.setTitle("영화 예매 프로그램");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ID 입력란
		txtID = new JTextField();
		txtID.setBounds(314, 214, 144, 33);
		txtID.setFont(new Font("굴림", Font.PLAIN, 13));
		txtID.setColumns(10);
		frame.getContentPane().add(txtID);
		
		//패스워드 입력란
		txtPassword = new JPasswordField();
		txtPassword.setBounds(314, 276, 144, 33);
		txtPassword.setFont(new Font("굴림", Font.PLAIN, 13));
		txtPassword.setColumns(10);
		frame.getContentPane().add(txtPassword);
		
		//로그인 버튼
		JButton btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("굴림", Font.PLAIN, 13));
		btnLogin.setBorder(null);
		btnLogin.setBounds(245, 349, 97, 23);
		frame.getContentPane().add(btnLogin);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(e -> {
					
			//아이디 입력체크
			if(!txtID.getText().isEmpty()) {
				//패스워드 입력체크
				if(!String.valueOf(txtPassword.getPassword()).isEmpty()) {
					//관리자 확인
					if(txtID.getText().equals(adminID) && String.valueOf(txtPassword.getPassword()).equals(adminPWD)) {
						JOptionPane.showMessageDialog(frame, "관리자 화면으로 이동합니다.");
						frame.dispose();
						AdminIndex.main(null);
					}else {
						Boolean check_id = false;
						List<List<String>> userList = new ReadCSV("user.csv").getList();
						for(int i=1; i<userList.size();i++) {
							if(txtID.getText().equals(userList.get(i).get(1))&&String.valueOf(txtPassword.getPassword()).equals(userList.get(i).get(2))){
								loginID = txtID.getText();
								loginName = userList.get(i).get(0);
								loginPWD = userList.get(i).get(2);
								loginTel = userList.get(i).get(3);
								loginAdress = userList.get(i).get(4);
								JOptionPane.showMessageDialog(frame, userList.get(i).get(0)+"님 환영합니다!");
								frame.dispose();
								Index.main(null);
								check_id = true;
								break;
							}
						}
						if(check_id == false) {
							JOptionPane.showMessageDialog(frame, "아이디와 패스워드를 확인해 주세요.");
							txtID.requestFocus();
						}
					}
				}else {
					JOptionPane.showMessageDialog(frame, "패스워드를 입력해 주세요.");
					txtPassword.requestFocus();
				}
			}else {
				JOptionPane.showMessageDialog(frame, "ID를 입력해 주세요.");
				txtID.requestFocus();
			}
		});
				
		//회원가입 버튼
		btnNewJoin = new JButton("회원가입");
		btnNewJoin.setFont(new Font("굴림", Font.PLAIN, 13));
		btnNewJoin.setBorder(null);
		btnNewJoin.setBounds(361, 349, 97, 23);
		btnNewJoin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnNewJoin);
		btnNewJoin.addActionListener(e -> {
			frame.dispose();
			UserInput.main(null);
		});
		
		//백그라운드 이미지 라벨
		JLabel loginBackLabel = new JLabel("");
		loginBackLabel.setBounds(0, 0, 684, 510);
		ImageIcon imageIcon = new ImageIcon("./image/LoginBackground2.png");
		Image image = imageIcon.getImage().getScaledInstance(700, 510, Image.SCALE_SMOOTH);
		loginBackLabel.setIcon(new ImageIcon(image));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(loginBackLabel);
	}
}
