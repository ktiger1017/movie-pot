package moviePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;


public class UserList {

	private JFrame frame;
	private UserTableData userData;
	private JTable tableUser;
	BufferedWriter bw;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList window = new UserList();
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
	public UserList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("관리자모드");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/** 헤더부분 START */
		JLabel lblNewLabel = new JLabel("유저리스트 (등급설정)");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(24, 10, 300, 52);
		frame.getContentPane().add(lblNewLabel);

		JButton btnAdminHome = new JButton("관리자홈");
		btnAdminHome.setBounds(560, 15, 100, 30);
		btnAdminHome.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnAdminHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnAdminHome);
		btnAdminHome.addActionListener(e -> {
			frame.dispose();
			AdminIndex.main(null);
		});
		/** 헤더부분 END */
		
		//본문 테이블 불러오기
		SetUserTable();
		
		/** 아래 버튼 START */
		//저장버튼
		JButton btnSave = new JButton("저장");
		btnSave.setBounds(270, 450, 120, 35);
		btnSave.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					bw = new BufferedWriter(new FileWriter("User.csv"));
					FileWriter fileWriter = new FileWriter("User.csv");
					String dummy = "이름,아이디,패스워드,전화번호,주소,등급\r\n";
					for(int row=0; row<tableUser.getRowCount(); row++) {
						for(int col=0; col<tableUser.getColumnCount(); col++) {
							if(col == tableUser.getColumnCount()-1) {
								dummy += (tableUser.getValueAt(row, col) + "\r\n");
							}else {
								dummy += (tableUser.getValueAt(row, col) + ",");
							}
						}
					}
					fileWriter.write(dummy);
					fileWriter.close();
					JOptionPane.showMessageDialog(frame, "저장되었습니다.");
					SetUserTable();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		/** 아래 버튼 END */
	}
	
	void SetUserTable() {
		//테이블 넣을 패널
		JPanel panel = new JPanel();
		panel.setBounds(15, 70, 680, 360);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		//유저테이블
		userData = new UserTableData(); 
		tableUser = new JTable(userData.getUserListinfo(), userData.getHeaders());
		tableUser.setAutoCreateRowSorter(true);
		tableUser.setSize(650, 350);
		tableUser.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		
		//등급 셀 콤보박스로 변경
		changeCellEditor(tableUser, tableUser.getColumnModel().getColumn(5));
		//컬럼사이즈 조절 클래스 호출
		new ResizeColumnWidth(tableUser);
		//테이블 헤더
		JTableHeader header = tableUser.getTableHeader();
		header.setForeground(Color.DARK_GRAY);
		header.setPreferredSize(new Dimension(tableUser.getWidth(), 30));
		header.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(tableUser);
		JScrollPane scrollPane = new JScrollPane(tableUser);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(650, 350);
		panel.add(scrollPane);
	}
	
	//등급 콤보박스
	void changeCellEditor(JTable table, TableColumn column) {
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		comboBox.addItem("일반");
		comboBox.addItem("실버");
		comboBox.addItem("골드");
		comboBox.addItem("VIP");
		comboBox.addItem("VVIP");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		column.setCellRenderer(cellRenderer);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				//클릭한 행 배경색 변경
				tableUser.setSelectionBackground(Color.CYAN);
			}
		});
	}
}
