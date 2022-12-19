package moviePackage;

public class MovieInfo {
	private String no;
	private String title;		//영화제목
	private String director;	//감독
	private String genre;		//장르
	private String releaseDate;	//개봉일
	private String rating;		//등급
	private float audienceNum;	//관객수
	
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public float getAudienceNum() {
		return audienceNum;
	}
	public void setAudienceNum(float audienceNum) {
		this.audienceNum = audienceNum;
	}
	
}
