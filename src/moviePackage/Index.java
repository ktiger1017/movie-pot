package moviePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.List;

public class Index{

	private JFrame frame;
	List<List<String>> movieList = new ReadCSV("movieinfo.csv").getList();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Index() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame(Login.loginName + "님 환영합니다!");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(frame) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		//상위 메뉴버튼 호출
		new Menu(frame);
		
		/**제목 패널 *****************************************/
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0,0,684,50);
		titlePanel.setLayout(null);
		
		JLabel filmLabel = new JLabel("");
		ImageIcon icon = new ImageIcon("./image/film.png");
		Image titleImg = icon.getImage().getScaledInstance(47, 46, Image.SCALE_SMOOTH);
		filmLabel.setIcon(new ImageIcon(titleImg));
		filmLabel.setBounds(5, 0, 50, 50);
		titlePanel.add(filmLabel);
		
		JLabel titleLabel = new JLabel("Movie List");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		titleLabel.setBounds(67, 0, 214, 50);
		titlePanel.add(titleLabel);
		JButton btnGradeSale = new JButton("할인혜택");
		btnGradeSale.setBounds(587, 10, 90, 40);
		btnGradeSale.setForeground(new Color(255, 0, 0));
		btnGradeSale.setBackground(SystemColor.menu);
		btnGradeSale.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnGradeSale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGradeSale.setBorder(null);
		btnGradeSale.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGradeSale.setForeground(new Color(255, 0, 0));
				btnGradeSale.setBackground(SystemColor.menu);
				btnGradeSale.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGradeSale.setForeground(Color.RED);
				btnGradeSale.setBackground(Color.PINK);
				btnGradeSale.setFont(new Font("맑은 고딕",	Font.BOLD, 15));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		btnGradeSale.addActionListener(e -> {
			//frame.dispose();
			GradeSale.main(null);
		});
		titlePanel.add(btnGradeSale);
		/**************************************************/
		

		JPanel homePanel = new JPanel();
		JPanel contentsPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(contentsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//메인 콘텐츠 패널
		contentsPanel.setBounds(4, 50, 678, 390);
		contentsPanel.setLayout(new GridLayout(0, 5));
		scroll.setBounds(4, 50, 678, 399);
		scroll.setViewportView(contentsPanel);
		
		// Home 패널 : 영화 리스트 
		homePanel.setBounds(0, 60, 684, 449);
		homePanel.setLayout(null);
		homePanel.add(titlePanel);
		homePanel.add(scroll);

		frame.getContentPane().add(homePanel);
		homePanel.setVisible(true);

		
		for(int i=0; i<movieList.size();i++) {
			if(i>0) {
				String movieNo = movieList.get(i).get(0);
				ImageIcon icon1 = new ImageIcon("./image/movie/" + movieList.get(i).get(0) + ".jpg");
				Image tempImg2 = icon1.getImage().getScaledInstance(130, 175, Image.SCALE_SMOOTH);
				JButton btnMovieImg = new JButton(new ImageIcon(tempImg2));
				
				//포스터 클릭시 이벤트
				btnMovieImg.addActionListener(e -> {
					homePanel.setVisible(false);
					new DrawPanel(movieNo);
				});
				btnMovieImg.setBorder(null);
				btnMovieImg.setBackground(Color.WHITE);
				btnMovieImg.setCursor(new Cursor(Cursor.HAND_CURSOR));
				contentsPanel.add(btnMovieImg);
			}
		}
	}
	

	//상세내용 셋팅
	class DrawPanel extends JPanel{
		private static final long serialVersionUID = 7747382442345088098L;
		
		MovieInfo movieInfo = new MovieInfo();
		
		public DrawPanel(String movieNo) {
			
			//패널설정
			setBounds(0, 50, 684, 461);
			setLayout(null);
			
			//포스터이미지 라벨
			JLabel posterLabel = new JLabel();
			posterLabel.setBounds(50, 10, 280, 450);
			
			String imgPath = "./image/movie/" ;
			Image tempImg = new ImageIcon(imgPath + movieNo + ".jpg").getImage().getScaledInstance(268, 358, Image.SCALE_SMOOTH);
			posterLabel.setIcon(new ImageIcon(tempImg));
			
			add(posterLabel);
			
			JButton btnReservation = new JButton("영화 예매");
			btnReservation.setForeground(new Color(85, 107, 47));
			btnReservation.setBackground(new Color(230, 230, 250));
			btnReservation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnReservation.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
			btnReservation.setBounds(370, 370, 130, 40);
			btnReservation.addActionListener(e -> {
				frame.dispose();
				Reservation.main(null);
			});
			add(btnReservation);
			
			JButton btnPreview = new JButton("예고편");
			btnPreview.setForeground(new Color(85, 107, 47));
			btnPreview.setBackground(new Color(230, 230, 250));
			btnPreview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPreview.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
			btnPreview.setBounds(510, 370, 130, 40);
			btnPreview.addActionListener(e -> {
				if(Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						
						for(int i=1; i<movieList.size(); i++) {
							if(String.valueOf(i).equals(movieNo)) {
								URI uri = new URI(movieList.get(i).get(7).trim().toString());
								desktop.browse(uri);
							}
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			add(btnPreview);

			frame.getContentPane().add(this);
			setVisible(true);
			
			for(int i=0; i<movieList.size();i++) {
				if(movieList.get(i).get(0).equals(movieNo)) {
					movieInfo.setTitle(movieList.get(i).get(1));
					movieInfo.setDirector(movieList.get(i).get(2));
					movieInfo.setGenre(movieList.get(i).get(3));
					movieInfo.setReleaseDate(movieList.get(i).get(4));
					movieInfo.setRating(movieList.get(i).get(5));
					movieInfo.setAudienceNum(Float.parseFloat(movieList.get(i).get(6)));
					break;
				}
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(SystemColor.windowBorder);
			g.setFont(new Font("맑은 고딕", Font.ITALIC, 18));
			g.drawString("제목 : " + movieInfo.getTitle(), 370, 80);
			g.drawString("감독 : " + movieInfo.getDirector(), 370, 130);
			g.drawString("장르 : " + movieInfo.getGenre(), 370, 180);
			g.drawString("등급 : " + movieInfo.getRating() + "관람가", 370, 230);
			g.drawString("개봉일 : " + movieInfo.getReleaseDate(),370, 280);
			g.drawString("관객수 : " + new DecimalFormat("0.0").format(movieInfo.getAudienceNum()/10000) + "만명", 370, 330);
		}
	}
}
