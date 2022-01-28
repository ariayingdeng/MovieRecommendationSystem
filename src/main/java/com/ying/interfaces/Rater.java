package com.ying.interfaces;

import java.util.ArrayList;

public interface Rater {
	public void addRating(String item, String rating);
	public boolean hasRating(String item);
	public double getRating(String item);
	public int numRatings();
	public ArrayList<String> getItemsRated();
}
