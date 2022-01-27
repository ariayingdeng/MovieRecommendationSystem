/**
 * SecondRating focuses on computing averages on movie ratings.
 * 
 * @author Ying Deng 
 * @version 2022-01-22
 */
package com.ying.model;

import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
    	FirstRatings firstRatings = new FirstRatings();
    	myMovies = firstRatings.loadMovies(moviefile);
    	myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    /**
     * 
     * @return the number of movies that were read in and stored in the ArrayList of type Movie
     */
    public int getMovieSize() {
    	return myMovies.size();
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
    	for (Rater rater: myRaters) {
    		for (Rating rating: rater.getMyRatings()) {
    			if (rating.getItem().equals(id)) {
    				raters++;
    				totalRating += rating.getValue();
    			}
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
    	ArrayList<Rating> avgRatings = new ArrayList<>();
    	for (Movie movie: myMovies) {
    		String id = movie.getId();
    		double avgRating = getAverageByID(id, minimalRaters);
    		if (avgRating > 0) {
    			avgRatings.add(new Rating(id, avgRating));
    		}
    	}
    	return avgRatings;
    }
    
    /**
     * Get the title of a movie with a specific id
     * 
     * @param id a movie ID
     * @return the title of the movie, or a String indicating the ID was not found if the movie ID does not exist
     */
    public String getTitle(String id) {
    	for (Movie movie: myMovies) {
    		if (movie.getId().equals(id)) {
    			return movie.getTitle();
    		}
    	}
    	return "ID NOT FOUND";
    }
    
    /**
     * Get the movie id based on title
     * 
     * @param title the title of a movie
     * @return the movie ID of this movie or "NO SUCH TITLE" if the title is not found
     */
    public String getID(String title) {
    	for (Movie movie: myMovies) {
    		if (movie.getTitle().equalsIgnoreCase(title)) {
    			return movie.getId();
    		}
    	}
    	return "NO SUCH TITLE";
    }
}
