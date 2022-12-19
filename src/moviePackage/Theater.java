package moviePackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class Theater {

	private JFrame frame;
	private JTextField textTheaterName;
	private JTextField textAdress;
	private JTextField textNotice;
	private ButtonGroup radioGroup;
	private JTable tableTheater;
	private DefaultTableModel model;
	private TheaterTableData ttData;
	private JButton btnInput;
	JRadioButton[] radio;
	JPanel OutputPanel;
	File csv = new File("./theaterinfo.csv");
	BufferedWriter bw = null;
	BufferedReader br = null;
	int selectedIndex = -1;
	int selected_Radio_Index = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Theater window = new Theater();
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
	public Theater() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("관리자모드");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/** 헤더부분 START */
		JLabel lblNewLabel = new JLabel("극장 관리");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 10, 181, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAdminHome = new JButton("관리자홈");
		btnAdminHome.setBounds(560, 15, 100, 30);
		btnAdminHome.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnAdminHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdminHome.addActionListener(e -> {
			frame.dispose();
			AdminIndex.main(null);
		});
		frame.getContentPane().add(btnAdminHome);
		/** 헤더부분 END */
		
		/** 입력부분 START */
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(12, 67, 660, 166);
		frame.getContentPane().add(inputPanel);
		inputPanel.setLayout(null);
		
		String[] lbl_Name = {"지역","영화관","주소","공지사항"};
		for(int i=0, y=0; i<lbl_Name.length; i++, y+=41) {
			JLabel label = new JLabel(lbl_Name[i]);
			label.setFont(new Font("굴림", Font.BOLD, 15));
			label.setBounds(12, y, 114, 33);
			inputPanel.add(label);
		}
		
		
		JPanel panel_radio = new JPanel();
		panel_radio.setBounds(138, 0, 510, 33);
		panel_radio.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel.add(panel_radio);
		
		//지역 선택
		String[] radio_Name = {"서울","경기","인천","강원","대전/충청","대구","부울경","전라/제주"};
		radio = new JRadioButton[radio_Name.length];
		radioGroup = new ButtonGroup();
		for(int i=0; i<radio_Name.length; i++) {
			radio[i] = new JRadioButton(radio_Name[i]);
			radio[i].addMouseListener(new SelectedRadio(i));
			panel_radio.add(radio[i]);
			radioGroup.add(radio[i]);
		}
		//영화관 이름
		textTheaterName = new JTextField();
		textTheaterName.setBounds(138, 41, 500, 33);
		inputPanel.add(textTheaterName);
		textTheaterName.setColumns(10);
		
		//주소
		textAdress = new JTextField();
		textAdress.setColumns(10);
		textAdress.setBounds(138, 82, 500, 33);
		inputPanel.add(textAdress);
		
		//공지사항
		textNotice = new JTextField();
		textNotice.setColumns(10);
		textNotice.setBounds(138, 123, 500, 33);
		inputPanel.add(textNotice);
		/** 입력부분 END */

		
		/** 출력부분 START */

		OutputPanel = new JPanel();
		OutputPanel.setBounds(12, 289, 660, 260);
		OutputPanel.setLayout(null);
		frame.getContentPane().add(OutputPanel);
		
		ttData = new TheaterTableData();
		ReadTableData(ttData.getTheaterListInfo());	
		/** 출력부분 END */
		
        /** 추가버튼 START */		
		btnInput = new JButton("추가");
		btnInput.setFont(new Font("굴림", Font.BOLD, 18));
		btnInput.setBounds(219, 243, 103, 36);
		btnInput.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnInput);
		
		btnInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//라디오버튼 값 가져오기
				Enumeration<AbstractButton> enums = radioGroup.getElements();
				String radioSelected = "";
				while(enums.hasMoreElements()) {
					AbstractButton radio = enums.nextElement();
					JRadioButton jb = (JRadioButton)radio;
					if(jb.isSelected()) {
						radioSelected = jb.getText().trim();
					}
				}
				if(!textTheaterName.getText().isEmpty()) {
					if(!radioSelected.isEmpty()) {
						if(!textAdress.getText().isEmpty()) {
							try {
								
								if(textNotice.getText().isEmpty()) {
									textNotice.setText("없음");
								}
								
								//추가모드 : 선택된 행이 없을 때
								if(selectedIndex<0) {
									bw = new BufferedWriter(new FileWriter("theaterinfo.csv", true));
									bw.write(textTheaterName.getText().trim() + ",");
									bw.write(radioSelected + ",");
									bw.write(textAdress.getText().replaceAll(",", "").trim() + ",");
									bw.write(textNotice.getText().replaceAll(",", "").trim() + "\n");
									bw.close();
									JOptionPane.showMessageDialog(frame, "추가되었습니다.");
									
								}else { //수정모드 : 선택된 행이 있을 때
									String dummy = "";
									br = new BufferedReader(new FileReader(csv));
									String line;
									for(int i=0; i<=selectedIndex; i++) {
										line = br.readLine();
										dummy += (line + "\r\n");
									}
									//수정할 데이터
									br.readLine();
									String currData = textTheaterName.getText() + "," + 
													  radioSelected + "," +
													  textAdress.getText() + "," +
													  textNotice.getText()+ "\r\n";
									dummy += currData;
									
									while((line = br.readLine())!=null) {
										dummy += (line + "\r\n");
									}
									
									FileWriter fw = new FileWriter(csv);
									fw.write(dummy);

									JOptionPane.showMessageDialog(frame, "수정되었습니다.");
									fw.close();
									br.close();
								}
								if(selected_Radio_Index<0) {
									ttData.refresh();
									ReadTableData(ttData.getTheaterListInfo());
								}else {
									List_By_Area(selected_Radio_Index);
								}
								//입력란 전부 초기화
								textTheaterName.setText(null);
								textAdress.setText(null);
								textNotice.setText(null);
								//radioGroup.clearSelection();
								btnInput.setText("추가");
								textTheaterName.requestFocus();
							}catch(Exception err) {
								err.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(frame, "주소를 입력해 주세요.");
							textAdress.requestFocus();
						}
					}else {
						JOptionPane.showMessageDialog(frame, "지역을 선택해 주세요.");
					}
				}else {
					JOptionPane.showMessageDialog(frame, "영화관 이름을 입력해 주세요.");
					textTheaterName.requestFocus();
				}
			}
		});
		/** 추가버튼 END */
		
		/** 삭제버튼 START */
		JButton btnDelete = new JButton("삭제");
		btnDelete.setFont(new Font("굴림", Font.BOLD, 18));
		btnDelete.setBounds(345, 243, 103, 36);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dummy = "";
				
				if(selectedIndex >= 0) {
					try {
						br = new BufferedReader(new FileReader(csv));
						String line;
						for(int i=0; i<=selectedIndex; i++) {
							line = br.readLine();
							dummy += (line + "\r\n");
						}
						//삭제될 데이터 읽고 지나감
						br.readLine();
						
						while((line = br.readLine())!=null) {
							dummy += (line + "\r\n");
						}
						FileWriter fw = new FileWriter(csv);
						fw.write(dummy);
						
						fw.close();
						br.close();
						btnInput.setText("추가");
						JOptionPane.showMessageDialog(frame, "삭제되었습니다.");

						if(selected_Radio_Index<0) {
							ttData.refresh();
							ReadTableData(ttData.getTheaterListInfo());
						}else {
							List_By_Area(selected_Radio_Index);
						}
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}else {
					JOptionPane.showMessageDialog(frame, "삭제하실 행을 선택해 주세요.");
				}
			}
		});
		/** 삭제버튼 END */
		
		/** 리셋 버튼 STSRT*/
		JButton btnReset = new JButton("새로고침");
		btnReset.setFont(new Font("굴림", Font.BOLD, 12));
		btnReset.setBounds(580, 250, 90, 30);
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedIndex = -1;
				textTheaterName.setText(null);
				textAdress.setText(null);
				textNotice.setText(null);
				radioGroup.clearSelection();
				ttData.refresh();
				ReadTableData(ttData.getTheaterListInfo());
				btnInput.setText("추가");
			}
		});
		/** 리셋 버튼 END */
	}
	
	//CSV파일 읽어서 테이블에 뿌려주는 메소드
	private void ReadTableData(String[][] data) {
		
		
		model = new DefaultTableModel(data, ttData.getHeaders());
		tableTheater = new JTable(model);
		model.fireTableDataChanged();
		tableTheater.setAutoCreateRowSorter(false);
		tableTheater.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		//컬럼 사이즈 조절 클래스 호출
		new ResizeColumnWidth(tableTheater);
		OutputPanel.add(tableTheater);

		JScrollPane scrollPane = new JScrollPane(tableTheater);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(660, 260);
		scrollPane.setViewportView(tableTheater);
		OutputPanel.add(scrollPane);
		
		//csv파일 헤더
		JTableHeader header = tableTheater.getTableHeader();
		header.setForeground(Color.DARK_GRAY);
		header.setPreferredSize(new Dimension(tableTheater.getWidth(), 30));
		
		tableTheater.addMouseListener(new SelectedTableCell()); 

	}
	
	//테이블 셀 클릭시 이벤트 : 텍스트필드에 선택된 값 뿌려주기
	private class SelectedTableCell implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable)e.getSource();
			selectedIndex = table.getSelectedRow();
			
			String selectedRadio = (String)tableTheater.getValueAt(selectedIndex, 1);
			
			textTheaterName.setText((String)tableTheater.getValueAt(selectedIndex, 0));
			textAdress.setText((String)tableTheater.getValueAt(selectedIndex, 2));
			textNotice.setText((String)tableTheater.getValueAt(selectedIndex, 3));
			
			switch(selectedRadio) {
			case "서울" 		: radio[0].setSelected(true); break;
			case "경기" 		: radio[1].setSelected(true); break;
			case "인천" 		: radio[2].setSelected(true); break;
			case "강원" 		: radio[3].setSelected(true); break;
			case "대전/충청" 	: radio[4].setSelected(true); break;
			case "대구" 		: radio[5].setSelected(true); break;
			case "부울경" 	: radio[6].setSelected(true); break;
			case "전라/제주" 	: radio[7].setSelected(true); break;
			}
			btnInput.setText("수정");
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	
	// 지역 라디오버튼 선택 이벤트 : 선택된 리스트만 테이블에 보여주기
	private class SelectedRadio implements MouseListener{
		int i;
		public SelectedRadio(int i) {
			this.i = i;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			List_By_Area(i);
			
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	

	// 선택된 라디오버튼 값 가져오기
	private void List_By_Area(int index){
		selected_Radio_Index = index;
		
		String selected_Radio = radio[selected_Radio_Index].getText();
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Theaterinfo.csv"));
			String line;
			while((line = br.readLine())!=null) {
				String[] oneLine = line.split(",");
				
				if(oneLine[1].equals(selected_Radio)) {
					ArrayList<String> list_line = new ArrayList<>(Arrays.asList(oneLine));
					list.add(list_line);
				}
			}
			int listSize = list.size();
			String[][]res = new String[listSize][4];
			for(int i=0; i<list.size();i++) {
				for(int j=0; j<4;j++) {
					res[i][j] = list.get(i).get(j).toString();
				}
			}
			br.close();

			ReadTableData(res);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

