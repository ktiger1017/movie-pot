package moviePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.*;

public class UserInput {

   private JFrame frame;
   private JTextField name;
   private JTextField id;
   private JTextField phoneNumber;
   private JTextField adress;
   private JPasswordField password1;
   private JPasswordField password2;
   private JButton AddButton;
   UserTableData tableData;
   private JButton btnIDCheck;
   private boolean IDChk = false;
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               UserInput window = new UserInput();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
   public UserInput() {
      initialize();
   }

   private void initialize() {
      
      frame = new JFrame("회원가입");
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setBounds(100, 100, 700, 550);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setForeground(Color.white);
      
      JPanel ContentPanel = new JPanel();
      ContentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
      ContentPanel.setBackground(SystemColor.text);
      ContentPanel.setBounds(0, 141, 684, 310);
      frame.getContentPane().add(ContentPanel);
      ContentPanel.setLayout(null);
      
      //   ===== 이름 =====
      name = new JTextField();
      name.setBounds(218, 12, 151, 21);
      ContentPanel.add(name);
      name.setColumns(10);
      
      JLabel lblName = new JLabel("*이름(최대3글자)* :");
      lblName.setBounds(75, 10, 151, 18);
      lblName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      ContentPanel.add(lblName);
      
      JLabel lblID = new JLabel(" *아이디(최대10글자)* :");
      lblID.setBounds(45, 43, 181, 18);
      lblID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      ContentPanel.add(lblID);
      
      //        ===== 아이디 ===== 
      id = new JTextField();
      id.setBounds(218, 43, 151, 21);
      id.setColumns(10);
      ContentPanel.add(id);
      
      //=====아이디 중복확인 버튼=====
      btnIDCheck = new JButton("중복확인");
      btnIDCheck.setBounds(376, 44, 85, 23);
      btnIDCheck.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
      ContentPanel.add(btnIDCheck);
      btnIDCheck.addActionListener(new IDDoubleCheck());
      
      
      JLabel lblPWD = new JLabel(" *비밀번호(최대10글자)*:");
      lblPWD.setBounds(13, 71, 194, 18);
      lblPWD.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblPWD.setHorizontalAlignment(JLabel.RIGHT);
      ContentPanel.add(lblPWD);
      
      JLabel lblPWD_re = new JLabel("비밀번호 확인 :");
      lblPWD_re.setBounds(42, 99, 165, 18);
      lblPWD_re.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblPWD_re.setHorizontalAlignment(JLabel.RIGHT);
      ContentPanel.add(lblPWD_re);
      
      JLabel lblTel = new JLabel("연락처(ex:010-1111-2222) :");
      lblTel.setBounds(0, 127, 208, 18);
      lblTel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblTel.setHorizontalAlignment(JLabel.RIGHT);
      ContentPanel.add(lblTel);
      
      // ===== 연락처 =====
      phoneNumber = new JTextField();
      phoneNumber.setBounds(218, 129, 194, 21);
      phoneNumber.setColumns(10);
      ContentPanel.add(phoneNumber);
      
      JLabel lblAdress = new JLabel("주소(ex:부산 해운대) : ");
      lblAdress.setBounds(48, 155, 165, 18);
      lblAdress.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblAdress.setHorizontalAlignment(JLabel.RIGHT);
      ContentPanel.add(lblAdress);
      
      adress = new JTextField();
      adress.setBounds(218, 155, 230, 21);
      adress.setColumns(10);
      ContentPanel.add(adress);
      
      
      
      //===== 비밀번호 =====
      password1 = new JPasswordField();
      password1.setBounds(218, 71, 151, 21);
      ContentPanel.add(password1);

      //===== 비밀번호 확인 =====   
      password2 = new JPasswordField();
      password2.setBounds(218, 102, 151, 21);
      ContentPanel.add(password2);
      password2.addFocusListener(new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			if(!String.valueOf(password2.getPassword()).isEmpty()) {
				if(!(String.valueOf(password1.getPassword()).equals(String.valueOf(password2.getPassword())))) {
					JOptionPane.showMessageDialog(frame, "비밀번호가 다릅니다.");
					password2.requestFocus();
					password2.setBackground(Color.ORANGE);
				}else {
					password2.setBackground(Color.WHITE);
				}
			}
		}
		@Override
		public void focusGained(FocusEvent e) {}
	});
      
      //=====버튼 패널=====
      JPanel btnPanel = new JPanel();
      btnPanel.setBounds(460, 37, 220, 224);
      ContentPanel.add(btnPanel);
      btnPanel.setBackground(SystemColor.text);
      
      JButton btnNaver = new JButton("");
      btnNaver.setBounds(12, 20, 198, 40);
      btnNaver.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Naver.main(null);
         }
      });
      btnPanel.setLayout(null);
      btnNaver.setIcon(new ImageIcon("./image/userInput/naver.png"));
      btnPanel.add(btnNaver);
      
      JButton btnKakao = new JButton("");
      btnKakao.setBounds(12, 70, 198, 40);
      btnKakao.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Kakao.main(null);
         }
      });
      btnKakao.setIcon(new ImageIcon("./image/userInput/kakao.png"));
      btnKakao.setBorder(null);
      btnPanel.add(btnKakao);
      
      JButton btnFacebook = new JButton("");
      btnFacebook.setBounds(12, 120, 198, 40);
      btnFacebook.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FaceBook.main(null);
         }
      });
      btnFacebook.setIcon(new ImageIcon("./image/userInput/face.png"));
      btnPanel.add(btnFacebook);
      
      JButton btnTwitter = new JButton("");
      btnTwitter.setBounds(12, 173, 198, 40);
      btnTwitter.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Twitter.main(null);
         }
      });
      btnTwitter.setIcon(new ImageIcon("./image/userInput/twitter.png"));
      btnPanel.add(btnTwitter);
      
      JPanel panel = new JPanel();
      panel.setBorder(new LineBorder(Color.GRAY));
      panel.setBackground(Color.WHITE);
      panel.setBounds(0, 203, 452, 31);
      ContentPanel.add(panel);
      panel.setLayout(null);
      
      JCheckBox check1 = new JCheckBox("이용약관 동의");
      check1.setBounds(8, 5, 113, 23);
      
      
      panel.add(check1);
      check1.setBackground(SystemColor.text);
      
      
      //=====이용약관 동의 버튼=====
      JButton CheckButton1 = new JButton("");
      CheckButton1.setBounds(156, 5, 33, 12);
      panel.add(CheckButton1);
      CheckButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Information.main(null);
            
         }
      });
      //=====이용약관 상세정보 이미지=====
      CheckButton1.setIcon(new ImageIcon("./image/userInput/add.png"));
      
      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new LineBorder(Color.GRAY));
      panel_2.setBackground(Color.WHITE);
      panel_2.setLayout(null);
      panel_2.setBounds(0, 230, 452, 31);
      ContentPanel.add(panel_2);
      
      JCheckBox check2 = new JCheckBox("개인정보 수집 및 이용동의");
      check2.setBounds(7, 6, 181, 21);
      panel_2.add(check2);
      check2.setBackground(SystemColor.text);
      
      //=====개인정보 동의 버튼=====
      JButton CheckButton2 = new JButton("");
      CheckButton2.setBounds(196, 6, 33, 12);
      panel_2.add(CheckButton2);
      CheckButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Information.main(null);
            
         }
      });
      //=====개인정보 상세정보 이미지=====
      CheckButton2.setIcon(new ImageIcon("./image/userInput/add.png"));
      
      JPanel HeadPanel = new JPanel();
      HeadPanel.setBackground(SystemColor.controlHighlight);
      HeadPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
      HeadPanel.setBounds(0, -12, 684, 215);
      frame.getContentPane().add(HeadPanel);
      HeadPanel.setLayout(null);
      
      JLabel HeadImageLabel = new JLabel("New label");
      HeadImageLabel.setIcon(new ImageIcon("./image/userInput/userImage.png"));
      HeadImageLabel.setBounds(0, 10, 111, 139);
      HeadPanel.add(HeadImageLabel);
      
      JLabel HeadLabel = new JLabel("New label");
      HeadLabel.setIcon(new ImageIcon("./image/userInput/Adduser.png"));
      HeadLabel.setBounds(134, 23, 173, 31);
      HeadPanel.add(HeadLabel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(123, 52, 202, 30);
      HeadPanel.add(panel_1);
      panel_1.setBackground(SystemColor.controlHighlight);
      panel_1.setLayout(null);
      
      JLabel lblNewLabel_3 = new JLabel("는 필수 입력항목 입니다. >");
      lblNewLabel_3.setBounds(45, 10, 157, 27);
      panel_1.add(lblNewLabel_3);
      
      //===== 필수입력항목 라벨=====
      JLabel lblNewLabel_2 = new JLabel("* *");
      lblNewLabel_2.setBounds(12, 9, 23, 29);
      panel_1.add(lblNewLabel_2);
      lblNewLabel_2.setForeground(Color.RED);
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      
      JLabel lblNewLabel_4 = new JLabel("<");
      lblNewLabel_4.setBounds(1, 8, 13, 31);
      panel_1.add(lblNewLabel_4);
      
      JPanel AddbuttonPanel = new JPanel();
      AddbuttonPanel.setBackground(SystemColor.controlHighlight);
      AddbuttonPanel.setBounds(0, 454, 684, 57);
      frame.getContentPane().add(AddbuttonPanel);
      AddbuttonPanel.setLayout(null);
      
       // =====가입하기 버튼===== 
      AddButton = new JButton("가입하기");
      AddButton.setBounds(200, 5, 151, 43);
      AddbuttonPanel.add(AddButton);
      AddButton.setFont(new Font("맑은 고딕", Font.BOLD, 23));
      
       //=====취소 버튼=====
      JButton backButton = new JButton("취소");
      backButton.setBounds(384, 5, 61, 43);
      AddbuttonPanel.add(backButton);
      backButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Login.main(null);
            frame.dispose();
         }
      });
      //=====취소 버튼 끝=====
      
      AddButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
           
               //비밀번호 같고 체크박스 전부 체크 되있으면 가입
               if(check1.isSelected()&&check2.isSelected()) {
            	   if(IDChk) {
                  
	            	   JOptionPane.showMessageDialog(frame,"가입 완료 되었습니다");
	            	   
						Login.loginID = id.getText();
						Login.loginName = name.getText();
						Login.loginPWD = String.valueOf(password1.getPassword());
						Login.loginTel = phoneNumber.getText();
						Login.loginAdress = adress.getText();
						
	            	   frame.dispose();
	            	   Index.main(null);
	                  
	                   try {
	                	   AddUser();
	                   } catch (IOException e1) {
	                	   e1.printStackTrace();
	                   }
	                   
            	   }else {
            		   JOptionPane.showMessageDialog(frame, "아이디 중복확인을 해 주세요.");
            		   btnIDCheck.setBackground(Color.PINK);
            	   }
               }
               else if(check1.isSelected()) {
                  JOptionPane.showMessageDialog(frame,"개인정보 수집 및 이용동의에 동의하여 주십시오");
               }
               else if(check2.isSelected()) {
                  JOptionPane.showMessageDialog(frame,"약관에 동의하여 주십시오");
               }
             
               else if(name.getText().isEmpty()) {
                  JOptionPane.showMessageDialog(frame,"이름을 입력해 주십시오");
               }
               else if(id.getText().isEmpty()) {
                  JOptionPane.showMessageDialog(frame,"아이디를 입력해 주십시오");
               }
               else if(String.valueOf(password1.getPassword()) != null) {
                  JOptionPane.showMessageDialog(frame,"비밀번호를 입력해 주십시오");
               }
               else if(String.valueOf(password2.getPassword()) != null) {
                  JOptionPane.showMessageDialog(frame,"비밀번호 확인을 입력해 주십시오");
               }else {
                  JOptionPane.showMessageDialog(frame,"개인정보수집 및 약관에 동의하여 주십시오");
               }
          }
      });
   }
   //=====회원 추가 메소드=====
   
   public void AddUser() throws IOException,FileNotFoundException {
      BufferedWriter bw=new BufferedWriter(new FileWriter("User.csv",true));
//      bw.newLine();
      bw.write(name.getText());
      bw.write(",");
      bw.write(id.getText());
      bw.write(",");
      bw.write(password1.getPassword());
      bw.write(",");
      bw.write(phoneNumber.getText());
      bw.write(",");
      bw.write(adress.getText());
      bw.write(",일반\n");
      
      bw.flush();
      bw.close();
   }
   
   //아이디 중복체크 클래스
   private class IDDoubleCheck implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserTableData userData = new UserTableData();
			for(int i=0; i<userData.getRowCount(); i++) {
				if(id.getText().trim().equals(userData.getValueAt(i, 1))) {
					JOptionPane.showMessageDialog(frame, "중복된 아이디가 있습니다.");
					id.requestFocus();
					IDChk = false;
					break;
				}else {
					IDChk = true;
				}
			}
			if(IDChk) {
				JOptionPane.showMessageDialog(frame, "확인되었습니다.");
				btnIDCheck.setBackground(Color.WHITE);
				password1.requestFocus();
			}
		}
   }
}