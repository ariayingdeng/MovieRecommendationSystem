package com.ying.model;

import com.ying.interfaces.Filter;
import com.ying.moviedatabase.MovieDatabase;

public class GenreFilter implements Filter {
	
	private String genre;
	

	public GenreFilter(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(genre);
	}

}
