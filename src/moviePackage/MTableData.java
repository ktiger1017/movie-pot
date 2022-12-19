package moviePackage;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;

public class MTableData extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private List<MovieInfo> list;
	private String[] headers = {"No","제목", "감독", "장르", "개봉일", "등급", "관객수"};
	
	public MTableData() {
		updateList();
	}
	
	public void updateList() {
		list = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("./movieinfo.csv"));
			for(int i=0; scanner.hasNextLine(); i++) {
				String[] data = scanner.nextLine().split(",");
				if(i!=0) {
					MovieInfo movie = new MovieInfo();
					MovieInfoBuilder mb = new MovieInfoBuilder(movie);
					movie = mb.No(data[0])
							.title(data[1])
							.director(data[2])
							.genre(data[3])
							.releaseDate(data[4])
							.rating(data[5])
							.audienceNum(Float.parseFloat(data[6]))
							.movieinfo();
					list.add(movie);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override 
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0:
			return list.get(row).getNo();
		case 1:
			return list.get(row).getTitle();
		case 2:
			return list.get(row).getDirector();
		case 3:
			return list.get(row).getGenre();
		case 4:
			return list.get(row).getReleaseDate();
		case 5:
			return list.get(row).getRating();
		case 6:
			return list.get(row).getAudienceNum();
		}
		return null;
	}

	@Override
	public String getColumnName(int col) {
		return headers[col];
	}
	
	
	public void refresh() {
		updateList();
		super.fireTableDataChanged();
	}
	
	public String[] getHeaders() {
		return headers;
	}
	
	public List<MovieInfo> getMovieList(){
		return list;
	}
	
	public String[][] getinfo() {
		int cnt = 0;
		String[][] info = new String[list.size()][headers.length];
		for(MovieInfo movie: list) {
			info[cnt][0] = movie.getNo();
			info[cnt][1] = movie.getTitle();
			info[cnt][2] = movie.getDirector();
			info[cnt][3] = movie.getGenre();
			info[cnt][4] = movie.getReleaseDate();
			info[cnt][5] = movie.getRating();
			info[cnt][6] = String.valueOf(new DecimalFormat("0").format(movie.getAudienceNum()));
			cnt++;
		} return info;
	}
}