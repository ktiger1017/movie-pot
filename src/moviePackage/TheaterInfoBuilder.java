package moviePackage;

public class TheaterInfoBuilder {
	private TheaterInfo theaterInfo;

	public TheaterInfoBuilder(TheaterInfo theaterInfo) {
		super();
		this.theaterInfo = theaterInfo;
	}
	
	public TheaterInfoBuilder theater(String theaterName) {
		theaterInfo.setTheaterName(theaterName);
		return this;
	}
	public TheaterInfoBuilder area(String area) {
		theaterInfo.setArea(area);
		return this;
	}
	public TheaterInfoBuilder adress(String adress) {
		theaterInfo.setAdress(adress);
		return this;
	}
	public TheaterInfoBuilder notice(String notice) {
		theaterInfo.setNotice(notice);
		return this;
	}
	public TheaterInfo theaterinfo() {
		return theaterInfo;
	}
}
