package moviePackage;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.*;

class DatePick {
	
	int DATE_MONTH = Calendar.getInstance().get(Calendar.MONTH);
	int DATE_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	
	JLabel J_Label = new JLabel("", JLabel.CENTER);
	String DATE_DAY = "";
	JDialog J_Dialog;
	JButton[] J_Buttons = new JButton[49];
	
	public DatePick(JFrame J_Frame_Parent) {
		J_Dialog = new JDialog();
		J_Dialog.setModal(true);
		String[] Header = new String[]{"SUN", "MON", "TUE", "WED", "THU","FRI", "SAT"};
		JPanel J_Panel1 = new JPanel(new GridLayout(7,7));
		J_Panel1.setPreferredSize(new Dimension(430, 200));
		
		for(int i=0; i< J_Buttons.length; i++) {
			final int selection = i;
			J_Buttons[i] = new JButton();
			J_Buttons[i].setFocusPainted(false);
			J_Buttons[i].setBackground(Color.WHITE);
			J_Buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			
			if(i<7) {	//달력 헤더부분
				J_Buttons[i].setText(Header[i]);
				J_Buttons[i].setForeground(Color.RED);
				J_Buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			}
			
			if(i > 6) {		//날짜 부분
				J_Buttons[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						DATE_DAY = J_Buttons[selection].getActionCommand();
						if(!DATE_DAY.isEmpty()) {
							LocalDate today = LocalDate.now();
							if(Integer.parseInt(DATE_DAY) >= today.getDayOfMonth()) {
								J_Dialog.dispose();
								
							}else {
								JOptionPane.showMessageDialog(J_Dialog, "<html><body>과거날짜입니다. <br />다시 선택해 주세요.</body></html>");
							}
						}
					}
				});
			}
			J_Panel1.add(J_Buttons[i]);
		}
		JPanel J_Panel2 = new JPanel(new GridLayout(1, 3));
		JButton Previous_Button = new JButton("<< Previous");
		Previous_Button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		Previous_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DATE_MONTH--;
				Display_date();
			}
		});
		J_Panel2.add(Previous_Button);
		J_Panel2.add(J_Label);
		JButton Next_Button = new JButton("Next >>");
		Next_Button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		Next_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DATE_MONTH++;
				Display_date();
			}
		});
		J_Panel2.add(Next_Button);
		J_Dialog.add(J_Panel1, BorderLayout.CENTER);
		J_Dialog.add(J_Panel2, BorderLayout.NORTH);
		J_Dialog.pack();
		J_Dialog.setLocationRelativeTo(J_Frame_Parent);
		Display_date();
		J_Dialog.setVisible(true);
	}
	
	public void Display_date() {
		
		LocalDate now = LocalDate.now();
		
		for(int i=7; i<J_Buttons.length; i++) {
			J_Buttons[i].setText("");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY년 MMMM");
		Calendar cal = Calendar.getInstance();
		cal.set(DATE_YEAR, DATE_MONTH, 1);
		
		int Day_Of_Week = cal.get(Calendar.DAY_OF_WEEK);
		int Days_In_Month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for(int j = 6 + Day_Of_Week, Day = 1; Day <= Days_In_Month; j++, Day++) {
			J_Buttons[j].setText("" + Day);
			
			//오늘 날짜 배경색 바꾸기
			if(Day == now.getDayOfMonth())
				J_Buttons[j].setBackground(Color.CYAN);
		}
		
		J_Label.setText(dateFormat.format(cal.getTime()));
		J_Label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		J_Dialog.setTitle("예매일 지정");
	}
	
	public String Set_Picked_Date() {
		if(DATE_DAY.equals(""))
			return DATE_DAY;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(DATE_YEAR, DATE_MONTH, Integer.parseInt(DATE_DAY));
		return dateFormat.format(cal.getTime());
	}
}

public class Date_Picker{

	
}


