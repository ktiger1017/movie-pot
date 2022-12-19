package moviePackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {
	
	public Menu(JFrame frame) {
		/**상위 메뉴버튼 START*/
		JPanel menuPanel = new JPanel();
		menuPanel.setSize(682, 70);
		menuPanel.setLayout(new GridLayout(1,4));
		frame.getContentPane().add(menuPanel);
		
		JButton[] btn_Menu = new JButton[4];
		String[] btn_Name = {"HOME", "Box Office", "영화예매", "MY PAGE"};
		
		for(int i=0; i<btn_Menu.length; i++) {
			final int selection = i;
			btn_Menu[i] = new JButton(btn_Name[i]);
			btn_Menu[i].setBackground(new Color(230, 230, 250));
			btn_Menu[i].setForeground(new Color(85, 107, 47));
			btn_Menu[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			btn_Menu[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btn_Menu[i].setBorder(null);
			btn_Menu[i].addMouseListener(new BtnMouseEvent(btn_Menu[i]));
			menuPanel.add(btn_Menu[i]);
			btn_Menu[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					frame.dispose();
					
					switch(selection) {
					case 0 : Index.main(null); break;
					case 1 : BoxOffice.main(null); break;
					case 2 : Reservation.main(null); break;
					case 3 : MyPage.main(null); break;
						
					}
				}
			});
		}
	}
	private class BtnMouseEvent  implements MouseListener{
		JButton btn;
		
		public BtnMouseEvent(JButton btn) {
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
			btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			btn.setBackground(new Color(230, 230, 250));
			btn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		}
	}


}
