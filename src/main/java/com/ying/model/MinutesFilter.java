package com.ying.model;

import com.ying.interfaces.Filter;
import com.ying.moviedatabase.MovieDatabase;

public class MinutesFilter implements Filter {
	private int min;
	private int max;

	public MinutesFilter(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean satisfies(String id) {
		return (MovieDatabase.getMinutes(id) >= min) && (MovieDatabase.getMinutes(id) <= max);
	}

}
