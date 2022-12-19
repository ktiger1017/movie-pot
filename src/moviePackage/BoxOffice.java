package moviePackage;

import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BoxOffice {

	private JFrame frame;
	private JPanel p_Main;
	private JPanel p_ComingSoon;
	private JTabbedPane tabbedPane;
	MTableData ttData = new MTableData();
	TitledBorder tb = new TitledBorder(new LineBorder(Color.LIGHT_GRAY));

	List<List<String>> movieList = new ReadCSV("movieinfo.csv").getList();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoxOffice window = new BoxOffice();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoxOffice() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame(Login.loginName + "님 환영합니다!");
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//상위 메뉴버튼 호출
		new Menu(frame);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 70, 680, 430);
		tabbedPane.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		frame.getContentPane().add(tabbedPane);
		
		//메인패널
		p_Main = new JPanel(new GridLayout(0, 2, 10, 10));
		ScrollPane scroll = new ScrollPane();
		Call_BoxOffice();
		scroll.add(p_Main);
		tabbedPane.add("영화순위", scroll);
		
		//개봉예정영화
		p_ComingSoon = new JPanel(new GridLayout(0,2,10,10));
		ScrollPane scroll2 = new ScrollPane();
		Call_ComingSoon();
		scroll2.add(p_ComingSoon);
		tabbedPane.add("개봉예정영화", scroll2);
		
	}

	private void Call_BoxOffice() {
		
		JLabel[] lbl_Movie = new JLabel[movieList.size()];
		ArrayList<Movie> list = new ArrayList<>();
		
		for(int i=1; i<movieList.size(); i++) {
			int no = Integer.parseInt(movieList.get(i).get(0));
			String title = String.valueOf(movieList.get(i).get(1));
			float audienceNum = Float.parseFloat(movieList.get(i).get(6));
			list.add(new Movie(no, title, audienceNum));
			
		}
		Collections.sort(list, Collections.reverseOrder());
				
		int i=1;
		for(Movie result : list) {
			if(result.audienceNum >0 ) {
				String str = "<html><body>" + i + "위 <br /><br />" +
							 result.title + "<br />" +
							 new DecimalFormat("0.0").format(result.audienceNum/10000) + "만명</body></html>";
				ImageIcon img = new ImageIcon(new ImageIcon("./image/movie/"+result.no + ".jpg").getImage().getScaledInstance(100, 130, Image.SCALE_SMOOTH));
				
				
				lbl_Movie[i] = new JLabel(str, img, JLabel.LEFT);
				lbl_Movie[i].setSize(new Dimension(250, 130));
				lbl_Movie[i].setBorder(tb);
				lbl_Movie[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				lbl_Movie[i].setOpaque(true);
				lbl_Movie[i].setBackground(Color.white);
				lbl_Movie[i].setIcon(img);
				lbl_Movie[i].setIconTextGap(15);
				lbl_Movie[i].setVerticalTextPosition(JLabel.CENTER);
				lbl_Movie[i].setHorizontalTextPosition(JLabel.RIGHT);
				
				p_Main.add(lbl_Movie[i]);
				i++;
			}
		}
	}

	private void Call_ComingSoon(){

		JLabel[] lbl_Movie = new JLabel[movieList.size()];
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = dateFormat.format(new Date(System.currentTimeMillis()));
		
		for(int i=1; i<movieList.size(); i++) {
			try {
				Date today = new Date(dateFormat.parse(strToday).getTime());
				
				Date often = dateFormat.parse(movieList.get(i).get(4).trim().toString());
				
				if(often.after(today)){
					ImageIcon img = new ImageIcon(new ImageIcon("./image/movie/"+ movieList.get(i).get(0) + ".jpg").getImage().getScaledInstance(100, 130, Image.SCALE_SMOOTH));

					String str = "<html><body>" + movieList.get(i).get(1) + "<br /><br />" +
								 "개봉일 : " + movieList.get(i).get(4) + "<br />" +
								 "</body></html>";
					
					lbl_Movie[i] = new JLabel(str, img, JLabel.LEFT);
					lbl_Movie[i].setSize(new Dimension(250, 130));
					lbl_Movie[i].setBorder(tb);
					lbl_Movie[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
					lbl_Movie[i].setOpaque(true);
					lbl_Movie[i].setBackground(Color.white);
					lbl_Movie[i].setIcon(img);
					lbl_Movie[i].setIconTextGap(15);
					lbl_Movie[i].setVerticalTextPosition(JLabel.CENTER);
					lbl_Movie[i].setHorizontalTextPosition(JLabel.RIGHT);
					
					p_ComingSoon.add(lbl_Movie[i]);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
	}

}


class Movie implements Comparable<Movie>{
	
	int no;
	String title;
	float audienceNum;
	
	public Movie(int no, String title, float audienceNum) {
		super();
		this.no = no;
		this.title = title;
		this.audienceNum = audienceNum;
	}

	@Override
	public int compareTo(Movie movie) {
		if(movie.audienceNum < audienceNum) {
			return 1;
		}else if(movie.audienceNum > audienceNum){
			return -1;
		}
		return 0;
	}
}
