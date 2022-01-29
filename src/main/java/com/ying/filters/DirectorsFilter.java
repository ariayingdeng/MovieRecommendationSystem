package com.ying.filters;

import com.ying.databases.MovieDatabase;
import com.ying.interfaces.Filter;

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
