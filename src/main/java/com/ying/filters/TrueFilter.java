package com.ying.filters;

import com.ying.interfaces.Filter;

public class TrueFilter implements Filter {
	
	@Override
	public boolean satisfies(String id) {
		return true;
	}
}
