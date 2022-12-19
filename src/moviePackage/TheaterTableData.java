package moviePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

public class TheaterTableData extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<TheaterInfo> theaterList;
	private String[] headers = {"극장이름","지역","주소","공지사항"};

	public TheaterTableData() {
		updateTheaterList();
	}

	public void updateTheaterList() {
		theaterList = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("./theaterinfo.csv"));
			for(int i=0; scanner.hasNextLine(); i++) {
				String[] data = scanner.nextLine().split(",");
				if(i!=0) {
					TheaterInfo theater = new TheaterInfo();
					TheaterInfoBuilder tb = new TheaterInfoBuilder(theater);
					theater = tb.theater(data[0])
							    .area(data[1])
							    .adress(data[2])
							    .notice(data[3])
								.theaterinfo();
					theaterList.add(theater);
				}
			}
			scanner.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int getRowCount() {
		return theaterList.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0:
			return theaterList.get(row).getTheaterName();
		case 1:
			return theaterList.get(row).getArea();
		case 2:
			return theaterList.get(row).getAdress();
		case 3:
			return theaterList.get(row).getNotice();
		}
		
		return null;
	}
	
	public String getColumName(int col) {
		return headers[col];
	}
	public void refresh() {
		updateTheaterList();
		super.fireTableDataChanged();
	}
	public String[] getHeaders() {
		return headers;
	}
	public List<TheaterInfo> getTheaterList(){
		return theaterList;
	}
	public String[][] getTheaterListInfo(){
		int cnt=0;
		String[][] info = new String[theaterList.size()][headers.length];
		for(TheaterInfo theater : theaterList) {
			info[cnt][0] = theater.getTheaterName();
			info[cnt][1] = theater.getArea();
			info[cnt][2] = theater.getAdress();
			info[cnt][3] = theater.getNotice();
			cnt++;
		}
		return info;
	}

}
