package com.ying.model;

import java.util.ArrayList;
import java.util.Collections;

import com.ying.databases.MovieDatabase;
import com.ying.databases.RaterDatabase;
import com.ying.filters.TrueFilter;
import com.ying.interfaces.Filter;
import com.ying.interfaces.Rater;

public class FourthRatings {
	
	
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
    	RaterDatabase.initialize("ratings.csv");
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters();
    	for (Rater rater: myRaters) {
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
    
    /**
     * This method translates a rating from the scale 0 to 10 to the scale -5 to 5 
     * and returns the dot product of the ratings of movies that they both rated.
     * 
     * @param me
     * @param r
     * @return the dot product of the ratings of movies
     */
    private double dotProduct(Rater me, Rater r) {
    	double result = 0;
    	ArrayList<String> myMovies = me.getItemsRated();
    	ArrayList<String> rMovies = r.getItemsRated();
    	for (String movie: myMovies) {
    		if (rMovies.contains(movie)) {
    			result += (me.getRating(movie) - 5) * (r.getRating(movie) - 5);
    		}
    	}
    	return result;
    }
    
    /**
     * This method computes a similarity rating for each rater in the RaterDatabase 
     * to see how similar they are to the Rater.
     * @param id
     * @return an ArrayList of type Rating, each Rating object the item field is 
     * a rater’s ID, and the value field is the dot product comparison,  
     * sorted by ratings from highest to lowest rating and only including those 
     * raters who have a positive similarity rating.
     */
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> raters = new ArrayList<>();
    	Rater me = RaterDatabase.getRater(id);
    	ArrayList<Rater> allRaters = RaterDatabase.getRaters();
    	for (Rater rater: allRaters) {
    		String raterId = rater.getMyID();
    		if (raterId != id) {
    			double dotProductC = dotProduct(me, rater);
    			if (dotProductC > 0) {
    				raters.add(new Rating(raterId, dotProductC));
    			}
    		}
    	}
    	// sort raters from highest dot product to lowest
    	Collections.sort(raters, Collections.reverseOrder());
    	return raters;
    }
    
    /**
     * This method computes to get an ArrayList of type Rating, of movies and 
     * their weighted average ratings using only the top numSimilarRaters with 
     * positive ratings and including only those movies that have at least 
     * minimalRaters ratings from those most similar raters.
     * 
     * @param id a rater ID
     * @param numSimilarRaters
     * @param minimalRaters
     * @return an ArrayList of Rating which objects should be returned in sorted 
     * order by weighted average rating from largest to smallest ratings.
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> ratings = new ArrayList<>();
    	ArrayList<Rating> topRaters = (ArrayList<Rating>) getSimilarities(id).subList(0, numSimilarRaters);
    	Rater me = RaterDatabase.getRater(id);
    	ArrayList<String> movies = me.getItemsRated();
    	for (String movieId: movies) {
    		double avg = getWeightedAvgByID(movieId, minimalRaters, topRaters);
    		if (avg > 0) {
    			ratings.add(new Rating(movieId, avg));
    		}
    	}
    	Collections.sort(ratings, Collections.reverseOrder());
    	return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> list = new ArrayList<>();
    	ArrayList<Rating> allRatings =getSimilarRatings(id, numSimilarRaters, minimalRaters);
        for(Rating rating : allRatings) {
            if (filterCriteria.satisfies(rating.getItem())) {
                list.add(rating);
            }
        }
    	return list;
    }
    
    private double getWeightedAvgByID(String id, int minimalRaters, ArrayList<Rating> topRaters) {
    	int raters = 0;
    	double totalRating = 0;
    	for (Rating rating: topRaters) {
    		String raterId = rating.getItem();
    		Rater rater = RaterDatabase.getRater(raterId);
    		if (rater.hasRating(id)) {
    			raters++;
    			totalRating += rater.getRating(id) * rating.getValue();
    		}
    	}
    	if (raters >= minimalRaters) {
    		return totalRating / raters;
    	}
    	return 0;
    }
    
    
}
