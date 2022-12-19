package moviePackage;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MovieAdmin_Add {

	private JFrame frame;
	private JTextField textTitle;
	private JTextField textDirector;
	private JTextField textGenre;
	private JTextField textreleaseDate;
	private JTextField textRating;
	private JTextField textAudienceNum;
	private JTextField textPreview;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieAdmin_Add window = new MovieAdmin_Add();
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
	public MovieAdmin_Add() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("영화 추가");
		frame.setBounds(100, 100, 312, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("영화 추가");
		label.setBounds(12, 10, 150, 40);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 60, 272, 441);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		//텍스트 필드
		ArrayList<String> addlist = new ArrayList<String>();
		
		textTitle = new JTextField();
		textTitle.setBounds(100, 40, 150, 21);
		panel.add(textTitle);
		
		textTitle.setColumns(10);
		
		textDirector = new JTextField();
		textDirector.setBounds(100, 80, 150, 21);
		panel.add(textDirector);

		textDirector.setColumns(10);
		
		textGenre = new JTextField();
		textGenre.setBounds(100, 120, 150, 21);
		panel.add(textGenre);
		
		textGenre.setColumns(10);
		
		textreleaseDate = new JTextField();
		textreleaseDate.setBounds(100, 160, 150, 21);
		panel.add(textreleaseDate);
		
		textreleaseDate.setColumns(10);
		
		textRating = new JTextField();
		textRating.setBounds(100, 200, 150, 21);
		textRating.setColumns(10);
		panel.add(textRating);
		
		
		textAudienceNum = new JTextField();
		textAudienceNum.setBounds(100, 240, 150, 21);
		textAudienceNum.setColumns(10);
		panel.add(textAudienceNum);

		textPreview = new JTextField();
		textPreview.setBounds(100, 280, 150, 21);
		panel.add(textPreview);
		
		//라벨
		String[] lblName = {"제목","감독","장르","개봉일","등급","관객수","예고편링크"};
		JLabel[] lblMovie = new JLabel[lblName.length];
		for(int i=0, y=40; i<lblName.length; i++, y+=40) {
			lblMovie[i] = new JLabel(lblName[i]);
			lblMovie[i].setBounds(15, y, 80, 15);
			lblMovie[i].setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			panel.add(lblMovie[i]);
		}
		
		JButton btnChecked = new JButton("추가하기");
		btnChecked.setBounds(80, 360, 116, 35);
		btnChecked.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnChecked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String addtitle = String.valueOf(textTitle.getText());
				String adddirector = String.valueOf(textDirector.getText());
				String addgenre = String.valueOf(textGenre.getText());
				String addreleaseDate = String.valueOf(textreleaseDate.getText());
				String addRating = String.valueOf(textRating.getText());
				String addAudienceNum = String.valueOf(textAudienceNum.getText());
				String addPreview = String.valueOf(textPreview.getText());
				
				try {
					FileWriter fileWriter = new FileWriter("movieinfo.csv", true);
					BufferedReader br = new BufferedReader(new FileReader("movieinfo.csv"));
					int listcount=0;
					
					while(br.readLine()!=null) {
						listcount++;
					}
					String lc = String.valueOf(listcount);
					
					addlist.add(lc);
					addlist.add(addtitle);
					addlist.add(adddirector);
					addlist.add(addgenre);
					addlist.add(addreleaseDate);
					addlist.add(addRating);
					addlist.add(addAudienceNum);
					addlist.add(addPreview);
					
					String data1 = addlist.toString().replace("[", "");
					String data2 = data1.replace("]", "");
					
					fileWriter.write("\n");
					fileWriter.write(data2);
					
					fileWriter.close();
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(btnChecked, "추가 완료");
			}
		});
		panel.add(btnChecked);
		
		JButton btnBackSpace = new JButton("<<");
		btnBackSpace.setFont(new Font("굴림", Font.PLAIN, 10));
		btnBackSpace.setBounds(244, 0, 52, 32);
		btnBackSpace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MovieAdmin.main(null);
			}
		});
		frame.getContentPane().add(btnBackSpace);
	}
}