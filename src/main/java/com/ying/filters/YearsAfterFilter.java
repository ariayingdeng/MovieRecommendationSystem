package com.ying.filters;

import com.ying.databases.MovieDatabase;
import com.ying.interfaces.Filter;

public class YearsAfterFilter implements Filter {
	private int myYear;
	
	public YearsAfterFilter(int myYear) {
		this.myYear = myYear;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}
}
