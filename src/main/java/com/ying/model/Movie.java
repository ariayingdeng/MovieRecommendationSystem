package com.ying.model;

public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;
    
    
	public Movie(String id, String title, String year) {
		this.id = id.trim();
		this.title = title.trim();
		this.year = Integer.parseInt(year.trim());
	}


	public Movie(String id, String title, String year, String genres, String director, String country, String poster,
			String minutes) {
		this.id = id.trim();
		this.title = title.trim();
		this.year = Integer.parseInt(year.trim());
		this.genres = genres;
		this.director = director;
		this.country = country;
		this.poster = poster;
		this.minutes = Integer.parseInt(minutes.trim());
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id.trim();
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title.trim();
	}


	public int getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = Integer.parseInt(year.trim());
	}


	public String getGenres() {
		return genres;
	}


	public void setGenres(String genres) {
		this.genres = genres;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public int getMinutes() {
		return minutes;
	}


	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genres=" + genres + "]";
	}  
    
}
