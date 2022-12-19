package moviePackage;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MovieAdmin {

	private JFrame frame;
	private JLabel label;
	private JButton btn_main;
	private JTable table;
	MTableData imd;
	public int selectedRow;
	public int selectedCol;
	BufferedReader br;
	private JTextField textTitle;
	private JTextField textDirector;
	private JTextField textAudienceNum;
	private JTextField textRating;
	private JTextField textGenre;
	private JTextField textreleaseDate;
	private JTextField textNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieAdmin window = new MovieAdmin();
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
	public MovieAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MTableData imd = new MTableData();
		
		frame = new JFrame("관리자모드");
		frame.getContentPane().setFont(new Font("굴림", Font.BOLD, 12));
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		label = new JLabel("영화 관리");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setBounds(12, 5, 150, 40);
		frame.getContentPane().add(label);
		
		btn_main = new JButton("관리자홈");
		btn_main.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn_main.setBounds(575, 10, 97, 34);
		btn_main.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminIndex.main(null);
			}
		});
		frame.getContentPane().add(btn_main);
		
		
		//영화 관리 테이블
		table = new JTable(imd.getinfo(), imd.getHeaders());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 614, 170);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setFont(new Font("맑은 고딕",Font.BOLD,12));
		new ResizeColumnWidth(table);
		
		//영화 관리 테이블 헤더
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.DARK_GRAY);
		header.setPreferredSize(new Dimension(table.getWidth(),30));

		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();
				
				textNo.setText((String)table.getValueAt(selectedRow, 0));
				textTitle.setText((String)table.getValueAt(selectedRow, 1));
				textDirector.setText((String)table.getValueAt(selectedRow, 2));
				textGenre.setText((String)table.getValueAt(selectedRow, 3));
				textreleaseDate.setText((String)table.getValueAt(selectedRow, 4));
				textRating.setText((String)table.getValueAt(selectedRow, 5));
				textAudienceNum.setText((String)table.getValueAt(selectedRow, 6));
				
			}
		});
		
		
		//==================================영화 추가==================================
		JButton btn_add = new JButton("추가");
		btn_add.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_add.setBounds(98, 467, 97, 23);
		btn_add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MovieAdmin_Add.main(null);
			}
		});
		frame.getContentPane().add(btn_add);
		
		
		//==================================영화 수정==================================
		JButton btn_mod = new JButton("수정");
		btn_mod.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_mod.setBounds(290, 467, 97, 23);
		btn_mod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedRow>=0) {
					try {
						ModifyData();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					imd.fireTableDataChanged();
					JOptionPane.showMessageDialog(btn_mod, "수정되었습니다.");
				}
			}
		});
		frame.getContentPane().add(btn_mod);
		
		
		//==================================영화 삭제==================================
		JButton btn_del = new JButton("삭제");
		btn_del.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_del.setBounds(481, 467, 97, 23);
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dummy = "";
				
				if(selectedRow>=0) {
					try {
						br = new BufferedReader(new FileReader("movieinfo.csv"));
						String line;
						for(int i=0; i<=selectedRow; i++) {
							line = br.readLine();
							dummy += (line + "\r\n");
						}
						//삭제될 데이터 출력
						String deldata = br.readLine();
						System.out.println(deldata);
						
						while((line = br.readLine())!=null) {
							dummy += (line + "\r\n");
						}
						FileWriter fw = new FileWriter("movieinfo.csv");
						fw.write(dummy);
						
						fw.close();
						br.close();
						JOptionPane.showMessageDialog(frame, "삭제되었습니다.");
						imd.fireTableChanged(null);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}else {
					JOptionPane.showMessageDialog(frame, "삭제하실 행을 선택해 주세요.");
				}
				imd.fireTableDataChanged();
			}
		});
		frame.getContentPane().add(btn_del);
		
		textNo = new JTextField();
		textNo.setColumns(10);
		textNo.setBounds(95, 241, 532, 21);
		frame.getContentPane().add(textNo);
		
		textTitle = new JTextField();
		textTitle.setBounds(95, 272, 532, 21);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);
		
		textreleaseDate = new JTextField();
		textreleaseDate.setColumns(10);
		textreleaseDate.setBounds(95, 364, 532, 21);
		frame.getContentPane().add(textreleaseDate);
		
		textGenre = new JTextField();
		textGenre.setColumns(10);
		textGenre.setBounds(95, 334, 532, 21);
		frame.getContentPane().add(textGenre);
		
		textRating = new JTextField();
		textRating.setColumns(10);
		textRating.setBounds(95, 394, 532, 21);
		frame.getContentPane().add(textRating);
		
		textAudienceNum = new JTextField();
		textAudienceNum.setColumns(10);
		textAudienceNum.setBounds(95, 424, 532, 21);
		frame.getContentPane().add(textAudienceNum);
		
		textDirector = new JTextField();
		textDirector.setColumns(10);
		textDirector.setBounds(95, 303, 532, 21);
		frame.getContentPane().add(textDirector);
		
		JLabel lblNo = new JLabel("No.");
		lblNo.setBounds(20, 245, 57, 15);
		frame.getContentPane().add(lblNo);
		
		JLabel lblTitle = new JLabel("제목");
		lblTitle.setBounds(20, 276, 57, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblDirector = new JLabel("감독");
		lblDirector.setBounds(20, 307, 57, 15);
		frame.getContentPane().add(lblDirector);
		
		JLabel lblGenre = new JLabel("장르");
		lblGenre.setBounds(20, 336, 57, 15);
		frame.getContentPane().add(lblGenre);
		
		JLabel lblreleaseDate = new JLabel("개봉일");
		lblreleaseDate.setBounds(20, 369, 57, 15);
		frame.getContentPane().add(lblreleaseDate);
		
		JLabel lblRating = new JLabel("등급");
		lblRating.setBounds(20, 400, 57, 15);
		frame.getContentPane().add(lblRating);
		
		JLabel lblAudienceNum = new JLabel("관객수");
		lblAudienceNum.setBounds(20, 431, 57, 15);
		frame.getContentPane().add(lblAudienceNum);
	
	}
	
	//==================================테이블 수정 메소드================================== 
	public void	ModifyData() throws IOException {
		
		table.setValueAt(textNo.getText(), selectedRow, 0);
		table.setValueAt(textTitle.getText(), selectedRow, 1);
		table.setValueAt(textDirector.getText(), selectedRow, 2);
		table.setValueAt(textGenre.getText(), selectedRow, 3);
		table.setValueAt(textreleaseDate.getText(), selectedRow, 4);
		table.setValueAt(textRating.getText(), selectedRow, 5);
		table.setValueAt(textAudienceNum.getText(), selectedRow, 6);

		
		
		br = new BufferedReader(new FileReader("movieinfo.csv"));
		
		String dummy = "";
		String line;
		for(int i=0; i<=selectedRow; i++) {
			line = br.readLine();
			dummy += (line + "\r\n");
		}
		//수정할 데이터
		br.readLine();
		String currData =
				textNo.getText() + "," +
				textTitle.getText() + "," +
				textDirector.getText() + "," + 
				textGenre.getText() + "," + 
				textreleaseDate.getText() + "," + 
				textRating.getText() + "," + 
				textAudienceNum.getText()+"\r\n";
		dummy += currData;
		
		while((line = br.readLine())!=null) {
			dummy += (line + "\r\n");
		}
		FileWriter fw = new FileWriter("movieinfo.csv");
		fw.write(dummy);
		
		fw.close();
		br.close();

	}
}