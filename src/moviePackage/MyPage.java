package moviePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyPage {

	private JFrame frame;
	JPanel panel_MyInfo;
	JPanel panel_MyReserv;
	JTextField[] textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage window = new MyPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MyPage() {
		initialize();
	}

	private void initialize() {
		//첫화면 프레임
		frame = new JFrame();
		frame.setTitle(Login.loginName + "님 환영합니다.");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(frame) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//상위 메뉴버튼 호출
		new Menu(frame);

		JTabbedPane tab_Pane = new JTabbedPane();
		tab_Pane.setBounds(5, 70, 675, 440);
		tab_Pane.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		frame.getContentPane().add(tab_Pane);
		
		//내 정보 패널
		panel_MyInfo = new JPanel();
		panel_MyInfo.setLayout(null);
		Get_MyInfo();
		tab_Pane.add("내 정보", panel_MyInfo);
		

		//예매내역 패널
		panel_MyReserv = new JPanel();
		panel_MyReserv.setLayout(null);
		Get_MyReserv();
		tab_Pane.add("예매 내역", panel_MyReserv);
		
	}
	
	//내 정보 탭
	private void Get_MyInfo(){
		
		JLabel[] label = new JLabel[5];
		String[] lbl_Name = {"이름", "아이디", "비밀번호", "연락처", "주소"};
		
		for(int i=0, col=20; i<label.length; i++, col+=50) {
			label[i] = new JLabel(lbl_Name[i]);
			label[i].setBounds(100, col, 80, 30);
			label[i].setHorizontalAlignment(JLabel.RIGHT);
			label[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			panel_MyInfo.add(label[i]);
		}
		
		
		textField = new JTextField[5];
		String[] userInfo = {Login.loginName, Login.loginID, Login.loginPWD, Login.loginTel, Login.loginAdress};
		
		for(int i=0, col=20; i<textField.length; i++, col+=50) {
			textField[i] = new JTextField(userInfo[i]);
			textField[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			// 주소란은 더 넓게 보여주기
			if(i<4) textField[i].setBounds(200, col, 200, 30);
			else    textField[i].setBounds(200, col, 350, 30);
			// ID와 이름은 변경불가
			if(i<2) textField[i].setEditable(false);
			
			panel_MyInfo.add(textField[i]);
		}
		
		JButton btn_Mod = new JButton("수정");
		btn_Mod.setBounds(220, 300, 100, 30);
		btn_Mod.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btn_Mod.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_MyInfo.add(btn_Mod);
		btn_Mod.addActionListener(new EditUserInfo());
		
		JButton btn_Reset = new JButton("리셋");
		btn_Reset.setBounds(340, 300, 100, 30);
		btn_Reset.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btn_Reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_MyInfo.add(btn_Reset);
 
		btn_Reset.addActionListener(e -> {
			textField[2].setText(Login.loginPWD);
			textField[3].setText(Login.loginTel);
			textField[4].setText(Login.loginAdress);
		});
	}
	
	//예매내역 탭
	private void Get_MyReserv() {

		//예매내역 데이터
		ArrayList<ArrayList<String>> list_Reserv = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("ReservationList.csv"));
			String line;
			while((line = br.readLine())!=null) {
				String[] arr_line = line.split(",");
				
				if(arr_line[0].equals(Login.loginID)) {
					ArrayList<String> list_line = new ArrayList<>(Arrays.asList(arr_line));
					list_Reserv.add(list_line);
				}
			}
			br.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(list_Reserv.size()==0) {
			JLabel lbl_Empty = new JLabel("예매내역이 없어요...");
			lbl_Empty.setBounds(50, 30, 550, 200);
			lbl_Empty.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
			lbl_Empty.setIcon(new ImageIcon(new ImageIcon("./image/empty.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH))); 	
			panel_MyReserv.add(lbl_Empty);
			
		}else {
			
			JPanel panel_TicketList = new JPanel();
			panel_TicketList.setLayout(new GridLayout(0, 1, 10,20));
			JScrollPane scrollPanel = new JScrollPane(panel_TicketList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPanel.setBounds(50, 10, 620, 350);
			scrollPanel.setBorder(null);
			panel_MyReserv.add(scrollPanel);
			
			String date = "";
			String theater = "";
			String movie =""; 
			String time = "";
			ImagePanel[] panel_Ticket = new ImagePanel[list_Reserv.size()];
			
			for(int i=0, px = 10; i<list_Reserv.size(); i++, px+=260) {

				date = list_Reserv.get(i).get(1);
				theater = list_Reserv.get(i).get(2);
				movie = list_Reserv.get(i).get(3);
				time = list_Reserv.get(i).get(4);
				
				panel_Ticket[i] = new ImagePanel(new ImageIcon("./image/ticket.jpg").getImage());
				panel_Ticket[i].setBounds(10, px, 500, 250);
				panel_Ticket[i].setLayout(null);
				panel_TicketList.add(panel_Ticket[i]);
				
				JLabel[] lbl_Reserv = new JLabel[4];
				
				for(int j=0, y=30; j<lbl_Reserv.length; j++, y+=40) {
					lbl_Reserv[j] = new JLabel();
					lbl_Reserv[j].setFont(new Font("맑은 고딕", Font.PLAIN, 18));
					lbl_Reserv[j].setBounds(30, y, 250, 30);
				    lbl_Reserv[j].setForeground(Color.GRAY);
				    panel_Ticket[i].add(lbl_Reserv[j]);
				    switch(j) {
				    case 0 : lbl_Reserv[j].setText(movie); break;
				    case 1 : lbl_Reserv[j].setText("예매일 : " + date); break;
				    case 2 : lbl_Reserv[j].setText("상영시간 : " + time); break;
				    case 3 : lbl_Reserv[j].setText("상영관 : " + theater); break;
				    }
				}
				
			}
			
//			JButton btn_Del = new JButton("예매취소");
//			btn_Del.setBounds(260, 320, 110, 40);
//			btn_Del.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
//			panel_MyReserv.add(btn_Del);
//			btn_Del.addActionListener(e -> {
//				
//				
//			});
			
		}
	} 
	
	//내 정보 : 수정버튼 이벤트
	private class EditUserInfo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserTableData userData = new UserTableData();
			DefaultTableModel user_Model = new DefaultTableModel(userData.getUserListinfo(), userData.getHeaders());

			Login.loginPWD = textField[2].getText();
			Login.loginTel = textField[3].getText();
			Login.loginAdress = textField[4].getText();
			
			for(int i=0; i<userData.getRowCount(); i++) {
				if(Login.loginID.equals(user_Model.getValueAt(i, 1))) {
					user_Model.setValueAt(textField[2].getText(), i, 2);
					user_Model.setValueAt(textField[3].getText(), i, 3);
					user_Model.setValueAt(textField[4].getText(), i, 4);
				}
			}
			
			try {
				FileWriter csv = new FileWriter("User.csv");
				
				int row = user_Model.getRowCount();
				int col = user_Model.getColumnCount();
				
				for(int i=0; i<col; i++) {
					if(i == col-1)
						csv.write(user_Model.getColumnName(i));
					else
						csv.write(user_Model.getColumnName(i) + ",");
				}
				csv.write("\n");
				for(int i=0; i<row; i++) {
					for(int j=0; j<col; j++) {
						if(j == col-1)
							csv.write(user_Model.getValueAt(i, j).toString());
						else
							csv.write(user_Model.getValueAt(i, j).toString() + ",");
					}
					csv.write("\n");
				}
				csv.close();
				JOptionPane.showMessageDialog(frame, "저장되었습니다.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}

