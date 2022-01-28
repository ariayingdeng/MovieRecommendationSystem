package com.ying.filters;

import com.ying.interfaces.Filter;
import com.ying.moviedatabase.MovieDatabase;

public class DirectorsFilter implements Filter {

	private String directors;
	
	public DirectorsFilter(String directors) {
		this.directors = directors;
	}

	@Override
	public boolean satisfies(String id) {
		String directorsStr = MovieDatabase.getDirector(id);
		String[] directorsOfMovie = directorsStr.split(",");
		for (String director: directorsOfMovie) {
			if (directors.contains(director))
				return true;
		}
		return false;
	}

}
