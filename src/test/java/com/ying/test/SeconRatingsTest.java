package com.ying.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.ying.model.Rating;
import com.ying.model.SecondRatings;

class SeconRatingsTest {
	
	SecondRatings ratings = new SecondRatings("data\\ratedmovies_short.csv", "data\\ratings_short.csv");

	/**
	 * Tests getSizes().
	 */
	@Test
	void testMovieSize() {
		assertEquals(5, ratings.getMovieSize());
		assertEquals(5, ratings.getRaterSize());
	}
	
	/**
	 * Tests getAverageRatings(int minimalRaters).
	 */
	@Test
	void testAverageRatings() {
		ArrayList<Rating> avgRatings = ratings.getAverageRatings(3);
		//Sort avgRatings based on rating
		Collections.sort(avgRatings);
		String outcome1 = avgRatings.get(0).getValue() + " " + ratings.getTitle(avgRatings.get(0).getItem());
		assertEquals("8.25 Her", outcome1);
		String outcome2 = avgRatings.get(1).getValue() + " " + ratings.getTitle(avgRatings.get(1).getItem());
		assertEquals("9.0 The Godfather", outcome2);
	}
	
	/**
	 * Tests getAverageByID(String id, int minimalRaters).
	 */
	@Test
	void testGetAverageByID() {
		String id = ratings.getID("The Godfather");
		double avg = ratings.getAverageByID(id, 1);
		assertEquals(9.0, avg);
	}

}
