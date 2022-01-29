package com.ying.model;

import java.util.ArrayList;

import com.ying.databases.MovieDatabase;
import com.ying.filters.TrueFilter;
import com.ying.interfaces.Filter;

public class ThirdRatings {
	    private ArrayList<EfficientRater> myRaters;
	    
	    public ThirdRatings() {
	        this("ratings.csv");
	    }
	    
	    public ThirdRatings(String ratingsfile) {
	    	FirstRatings firstRatings = new FirstRatings();
	    	myRaters = firstRatings.loadRaters(ratingsfile);
	    }
	    
	    /**
	     * 
	     * @return the number of raters that were read in and stored in the ArrayList of type Rater
	     */
	    public int getRaterSize() {
	    	return myRaters.size();
	    }
	    
	    /**
	     * Calculate the average rating of a movie.
	     * 
	     * @param id a movie ID
	     * @param minimalRaters 
	     * @return  the average movie rating for this ID if there are at least minimalRaters ratings, 
	     * 			0.0 if there are not minimalRaters ratings.
	     */
	    public double getAverageByID(String id, int minimalRaters) {
	    	int raters = 0;
	    	double totalRating = 0;
	    	for (EfficientRater rater: myRaters) {
	    		if (rater.hasRating(id)) {
	    			raters++;
	    			totalRating += rater.getRating(id);
	    		}
	    	}
	    	if (raters >= minimalRaters) {
	    		return totalRating / raters;
	    	}
	    	return 0;
	    }
	    
	    /**
	     * Get the average rating for every movie that has been rated by at least minimalRaters raters.
	     *  
	     * @param minimalRaters
	     * @return an ArrayList of all the Rating objects for movies that have 
	     * 			at least the minimal number of raters supplying a rating
	     */
	    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
	    	ArrayList<Rating> avgRatings = getAverageRatingsByFilter(minimalRaters, new TrueFilter());
	    	return avgRatings;
	    }
	    
	    /**
	     * Create and return an ArrayList of type Rating of all the movies that 
	     * have at least minimalRaters ratings and satisfies the filter criteria. 
	     * 
	     * @param minimalRaters
	     * @param filterCriteria
	     * @return ArrayList of Rating of the movies
	     */
	    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
	    	ArrayList<Rating> avgRatings = new ArrayList<>();
	    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    	for (String movieId: movies) {
	    		double avgRating = getAverageByID(movieId, minimalRaters);
	    		if (avgRating > 0) {
	    			avgRatings.add(new Rating(movieId, avgRating));
	    		}
	    	}
	    	return avgRatings;
	    }

}
