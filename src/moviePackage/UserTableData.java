package moviePackage;

import java.io.File;
import java.util.*;
import javax.swing.table.AbstractTableModel;


public class UserTableData extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	Scanner sc;
	private String[]headers = {"이름","아이디","패스워드","전화번호","주소","등급"};
	private List<UserInfo> UserList;
	
	public UserTableData() {
		updateList();
	}

	private void updateList() {
		UserList=new ArrayList<>();
		
		try {
			sc= new Scanner(new File("User.csv"));
			for(int i=0;sc.hasNextLine();i++) {
				String[] data= sc.nextLine().split(",");
				if(i!=0) {
					UserInfo user = new UserInfo();
					UserInfoBuilder bb = new UserInfoBuilder(user);
					user = bb.name(data[0].trim())
							.id(data[1].trim())
							.pw(data[2].trim())
							.number(data[3].trim())
							.adress(data[4].trim())
							.grade(data[5].trim())
							.userInfo();
					UserList.add(user);
				}
			}sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		return UserList.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return UserList.get(rowIndex).getName();
		case 1:
			return UserList.get(rowIndex).getId();
		case 2:
			return UserList.get(rowIndex).getPw();
		case 3:
			return UserList.get(rowIndex).getNumber();
		case 4:
			return UserList.get(rowIndex).getAdress();
		case 5:
			return UserList.get(rowIndex).getGrade();
		}
		return null;
	}
	
	public String getColumName(int columnIndex) {
		return headers[columnIndex];
	}
	public void refresh() {
		updateList();
		super.fireTableDataChanged();
	}
	public String[]getHeaders(){
		return headers;
	}
	public List<UserInfo> getUserList(){
		return UserList;
	}
	public String[][]getUserListinfo(){
		int cnt=0;
		String[][] info=new String[UserList.size()][headers.length];
		
		
		for(UserInfo user: UserList) {
			info[cnt][0]= user.getName();
			info[cnt][1]= user.getId();
			info[cnt][2]= user.getPw();
			info[cnt][3]= user.getNumber();
			info[cnt][4]= user.getAdress();
			info[cnt][5]= user.getGrade();
			cnt++;
		}
		return info;
	}
}
