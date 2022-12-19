package moviePackage;


public class MovieInfoBuilder {
	private MovieInfo movie;

	public MovieInfoBuilder(MovieInfo movie) {
		super();
		this.movie = movie;
	}
	
	public MovieInfoBuilder No(String no) {
		movie.setNo(no);
		return this;
	}
	
	public MovieInfoBuilder title(String title) {
		movie.setTitle(title);
		return this;
	}
	public MovieInfoBuilder director(String director) {
		movie.setDirector(director);
		return this;
	}
	public MovieInfoBuilder genre(String genre) {
		movie.setGenre(genre);
		return this;
	}
	public MovieInfoBuilder releaseDate(String releaseDate) {
		movie.setReleaseDate(releaseDate);
		return this;
	}
	public MovieInfoBuilder rating(String rating) {
		movie.setRating(rating);
		return this;
	}
	public MovieInfoBuilder audienceNum(float audienceNum) {
		movie.setAudienceNum(audienceNum);
		return this;
	}
	public MovieInfo movieinfo() {
		return movie;
	}
}
