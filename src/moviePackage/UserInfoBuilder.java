package moviePackage;

public class UserInfoBuilder {
	private UserInfo user;

	public UserInfoBuilder(UserInfo user) {
		this.user = user;
	}
	
	public UserInfo userInfo() {
		return user;
	}
	public UserInfoBuilder name(String name) {
		user.setName(name);
		return this;
	}
	public UserInfoBuilder id(String id) {
		user.setId(id);
		return this;
	}
	public UserInfoBuilder pw(String pw) {
		user.setPw(pw);
		return this;
	}
	public UserInfoBuilder number(String number) {
		user.setNumber(number);
		return this;
	}
	public UserInfoBuilder adress(String adress) {
		user.setAdress(adress);
		return this;
	}
	public UserInfoBuilder grade(String grade) {
		user.setGrade(grade);
		return this;
	}
}
