package moviePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Reservation {

	private JFrame frame;
	private JTextField textDate;
	private JList<String> listArea;
	private JList<String> listTheater;
	private JList<String> listMovie;
	private JList<String> listTime;
	private String selectedArea;
	private String selectedTheater;
	private String selectedMovie;
	private String selectedTime;
	private JLabel lbl_Notice;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation window = new Reservation();
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
	public Reservation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(Login.loginName + "님 환영합니다.");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//상위 메뉴버튼 호출
		new Menu(frame);
		
		//타이틀
		JLabel lblTitle = new JLabel("영화예매");
		lblTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		lblTitle.setBounds(20, 75, 150, 35);
		frame.getContentPane().add(lblTitle);
		
		//날짜 선택
		JLabel lblDate = new JLabel("날짜 선택 :");
		lblDate.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblDate.setBounds(15, 140, 80, 35);
		frame.getContentPane().add(lblDate);
		textDate = new JTextField(10);
		textDate.setEditable(false);
		textDate.setBounds(100, 140, 100, 35);
		textDate.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		BevelBorder border = new BevelBorder(BevelBorder.LOWERED);
		textDate.setBorder(border);
		lblDate.setLabelFor(textDate);
		frame.getContentPane().add(textDate);
		
		JButton btnCal = new JButton("달력");
		btnCal.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnCal.setBounds(210, 140, 60, 30);
		btnCal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textDate.setText(new DatePick(frame).Set_Picked_Date());
			}
		});
		frame.getContentPane().add(btnCal);
		lbl_Notice = new JLabel();
		lbl_Notice.setBounds(300, 140, 380, 30);
		lbl_Notice.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl_Notice.setForeground(Color.RED);
		lbl_Notice.setVisible(false);
		frame.getContentPane().add(lbl_Notice);
		
			
		//예매 패널
		JPanel panel = new JPanel();
		panel.setBounds(12, 180, 660, 255);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/** 지역부문 START */
		JLabel lblArea = new JLabel("지역");
		lblArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblArea.setBounds(30, 10, 57, 20);
		panel.add(lblArea);
		
		String[] strArea = {"서울","경기","인천","강원","대전/충청","대구","부울경","전라/제주"};
		listArea = new JList<String>(strArea);
		listArea.setBounds(12, 40, 80, 200);
		listArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listArea.addMouseListener(new MouseClickEvent("clickedArea"));
		panel.add(listArea);
		/** 지역부문 END */

		JLabel lblNext1 = new JLabel(">>");
		lblNext1.setBounds(100, 120, 15, 15);
		panel.add(lblNext1);
		
		/** 극장부문 START */
		JLabel lblTheater = new JLabel("극장");
		lblTheater.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblTheater.setBounds(180, 10, 57, 21);
		panel.add(lblTheater);
		
		listTheater = new JList<String>();
		listTheater.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listTheater.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listTheater.addMouseListener(new MouseClickEvent("clickedTheater"));
		JScrollPane theaterScroll = new JScrollPane();
		theaterScroll.setBounds(120, 40, 150, 200);
		theaterScroll.setBorder(null);
		theaterScroll.setViewportView(listTheater);
		panel.add(theaterScroll);
		/** 극장부문 END */
		
		JLabel lblNext2 = new JLabel(">>");
		lblNext2.setBounds(275, 120, 15, 15);
		panel.add(lblNext2);
		
		/** 영화부문 START */
		JLabel lblMovie = new JLabel("영화");
		lblMovie.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblMovie.setBounds(380, 10, 57, 21);
		panel.add(lblMovie);	
		
		listMovie = new JList<String>();
		listMovie.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listMovie.addMouseListener(new MouseClickEvent("clickedMovie"));
		JScrollPane movieScroll = new JScrollPane();
		movieScroll.setBounds(295, 40, 230, 200);
		movieScroll.setBorder(null);
		movieScroll.setViewportView(listMovie);
		panel.add(movieScroll);
		/** 영화부문 END */

		JLabel lblNext3 = new JLabel(">>");
		lblNext3.setBounds(530, 120, 15, 15);
		panel.add(lblNext3);
		
		/** 시간부문 START */
		JLabel lblTime = new JLabel("시간");
		lblTime.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblTime.setBounds(580, 10, 57, 21);
		panel.add(lblTime);
		
		listTime = new JList<String>();
		listTime.setBounds(550, 40, 100, 200);
		listTime.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		listTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listTime.addMouseListener(new MouseClickEvent("clickedTime"));
		panel.add(listTime);
		/** 시간부문 END */
		
		/** 하단 버튼 START */
		JButton btnSave = new JButton("예매");
		btnSave.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnSave.setBounds(219, 450, 100, 30);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.addActionListener(new Save());
		frame.getContentPane().add(btnSave);
		
		JButton btnCheck = new JButton("예매내역");
		btnCheck.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnCheck.setBounds(345, 450, 100, 30);
		btnCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MyPage.main(null);
			}
		});
		frame.getContentPane().add(btnCheck);
		/** 하단 버튼 END */
	}
	
	private class MouseClickEvent implements MouseListener{

		String str;
		public MouseClickEvent(String str) {
			this.str = str;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			switch(str) {
			case "clickedArea" : 	//지역 클릭시 그 지역의 극장리스트 띄움
				
				listMovie.setListData(new String[] {});
				listTime.setListData(new String[] {});
				TheaterTableData tableData = new TheaterTableData();
				selectedArea = listArea.getSelectedValue();
				//극장데이터 행
				int row = tableData.getRowCount();
				DefaultListModel<String> theaterModel = new DefaultListModel<>();
								
				for(int i=0; i<row; i++) {
					if(selectedArea.equals(tableData.getTheaterList().get(i).getArea())) {
						theaterModel.addElement(tableData.getTheaterList().get(i).getTheaterName());
						listTheater.setModel(theaterModel);
					}
				}
				lbl_Notice.setVisible(false);
				break;
				
			case "clickedTheater" :	//극장 클릭시 영화리스트 띄움
				listMovie.setListData(new String[] {});
				listTime.setListData(new String[] {});
				listMovie.isSelectionEmpty();
				lbl_Notice.setVisible(false);
				selectedTheater = listTheater.getSelectedValue();
				MTableData movieData = new MTableData();
				String[] arrMoveiTitle = new String[movieData.getRowCount()];
				for(int i=0; i<movieData.getRowCount(); i++) {
					arrMoveiTitle[i] = movieData.getMovieList().get(i).getTitle();
					listMovie.setListData(arrMoveiTitle);
				}
				
				//공지사항 들고오기
				String notice = "";
				TheaterTableData ttData = new TheaterTableData();
				for(int i=0; i<ttData.getRowCount(); i++) {
					if(selectedTheater.equals(ttData.getValueAt(i, 0))) {
						notice = ttData.getValueAt(i, 3).toString();
					}
				}
				lbl_Notice.setText(notice);
				if(!(notice.equals("없음"))) {
					lbl_Notice.setVisible(true);
				}
				break;
			case "clickedMovie" :
				selectedMovie = listMovie.getSelectedValue();
				String[] arrTime = new String[] {"9:00","11:00","14:00","17:00","20:00","22:00"};
				listTime.setListData(arrTime);

				
				break;
			case "clickedTime" :
				selectedTime = listTime.getSelectedValue();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	//저장버튼
	private class Save implements ActionListener{
		public Save() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!textDate.getText().isEmpty()) {
				if(!selectedTheater.isEmpty()) {
					if(!selectedMovie.isEmpty()) {
						if(!selectedTime.isEmpty()&&!selectedTime.isBlank()) {
							String dummy = "예매일 : " + textDate.getText() + "\r\n" + "극장명 : " + selectedTheater + "\r\n" + 
									       "영화 : " + selectedMovie + "\r\n" + "시간 : " + selectedTime + "\r\n";
						
							int confirm = JOptionPane.showConfirmDialog(frame, dummy, "예매하시겠습니까?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							if(confirm == 0) {
								try {
									FileWriter writer = new FileWriter("ReservationList.csv",true);
									writer.write(Login.loginID + ",");
									writer.write(textDate.getText() + ",");	//날짜
									writer.write(selectedTheater + ",");	//극장
									writer.write(selectedMovie + ",");		//영화
									writer.write(selectedTime + "\r\n");	//상영시간
									writer.close();
								} catch (IOException e1) {
										e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "예매 되었습니다.");
							}
						}else {
							JOptionPane.showMessageDialog(frame, "시간을 선택해 주세요.");
						}
					}else {
						JOptionPane.showMessageDialog(frame, "영화를 선택해 주세요.");
					}
				}else {
					JOptionPane.showMessageDialog(frame, "극장을 선택해 주세요.");
				}
			}else {
				JOptionPane.showMessageDialog(frame, "날짜를 입력해 주세요");
			}
		}
		
	}
}
