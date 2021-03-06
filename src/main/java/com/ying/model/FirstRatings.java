package com.ying.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.csv.*;

/**
 * Process the movie and ratings data from XML files.
 * 
 * @author Ying Deng 
 * @version 2022-01-15
 */
public class FirstRatings {
	
	public enum MovieHeaders {
	    ID, title, year, country, genre, director, minutes, poster
	}
	
	public enum RatingHeaders {
		rater_id, movie_id, rating, time
	}
	
	public static void main(String[] args) {
		testLoadRaters();
	}
	
	/**
	 * This method process every record from the CSV file whose name is filename, 
	 * a file of movie information.
	 * 
	 * @param filename the movies CSV file name
	 * @return an ArrayList of type Movie with all of the movie data from the file
	 */
	public static ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<>();
		try {
			FileReader reader = new FileReader(filename);

			CSVFormat csvFormat = CSVFormat.Builder.create()
			        .setHeader(MovieHeaders.class)
			        .setSkipHeaderRecord(true)
			        .build();
			
			CSVParser records = CSVParser.parse(reader, csvFormat);
			
			for (CSVRecord record: records) {
				String ID = record.get(MovieHeaders.ID);
				String movieTitle = record.get(MovieHeaders.title);
				String movieYear = record.get(MovieHeaders.year);
				String movieCountry = record.get(MovieHeaders.country);
				String movieGenre = record.get(MovieHeaders.genre);
				String movieDirector = record.get(MovieHeaders.director);
				String movieMins = record.get(MovieHeaders.minutes);
				String moviePoster = record.get(MovieHeaders.poster);
				Movie movie = new Movie(ID, movieTitle, movieYear, movieGenre, movieDirector, movieCountry, moviePoster, movieMins);
				movies.add(movie);
			}
			reader.close();
		}
		catch (Exception e) {
			System.out.println("Error in reading movie CSV file.");
			e.printStackTrace();
		}

		return movies;
	}
	
	public static void testLoadMovies() {
		String fileName = "data\\ratedmoviesfull.csv";
		ArrayList<Movie> movieList = loadMovies(fileName);
		System.out.println("The number of movies " + movieList.size());
		//System.out.println(movieList);
		
		//determine how many movies include the Comedy genre
		int comedyNum = 0;
		for (Movie movie: movieList) {
			if (movie.getGenres().contains("Comedy")) {
				comedyNum++;
			}
		}
		System.out.println("Movies include the Comedy genre: " + comedyNum);
		
		//determine how many movies are greater than 150 minutes in length
		int num = 0;
		for (Movie movie: movieList) {
			if (movie.getMinutes() > 150) {
				num++;
			}
		}
		System.out.println("Movies are greater than 150 minutes: " + num);
		
		//determine the maximum number of movies by any director, and who the directors are that directed that many movies.
		HashMap<String, Integer> directors = new HashMap<>();
		for (Movie movie: movieList) {
			String director = movie.getDirector();
			String[] directorList = director.split(",");
			for (String directorName: directorList) {
				String name = directorName.trim();
				if (directors.containsKey(name)) {
					int moviesNum = directors.get(name) + 1;
					directors.replace(name, moviesNum);
				}
				else {
					directors.put(name, 1);
				}
			}
		}
		int maxNum = 0;
		for (int i: directors.values()) {
			if (i > maxNum) {
				maxNum = i;
			}
		}
		System.out.println("The maximum number of movies by any director: " + maxNum);
		for (String k: directors.keySet()) {
			if (directors.get(k) == maxNum)
				System.out.println(k);
		}
		System.out.println();
		
	}
	
	/**
	 * This method should process every record from the CSV file whose name is filename, 
	 * a file of raters and their ratings.
	 * 
	 * @param filename the raters CSV file name
	 * @return an ArrayList of type Rater with all the rater data from the file
	 */
	public static ArrayList<EfficientRater> loadRaters(String filename) {
		ArrayList<EfficientRater> raters = new ArrayList<>();
		try {
			FileReader reader = new FileReader(filename);
			CSVFormat csvFormat = CSVFormat.Builder.create()
					.setHeader(RatingHeaders.class)
					.setSkipHeaderRecord(true)
					.build();
			CSVParser records = CSVParser.parse(reader, csvFormat);
			for (CSVRecord record: records) {
				String raterID = record.get(RatingHeaders.rater_id);
				String movieID = record.get(RatingHeaders.movie_id);
				String ratingValue = record.get(RatingHeaders.rating);
				
				//check whether the rater exist in raters
				//if hasRater add rating in the rater
				//else add the new rater into the raters
				boolean hasRater = false;
				for (EfficientRater rater: raters) {
					if ((rater.getMyID()).equals(raterID)) {
						hasRater = true;
						rater.addRating(movieID, ratingValue);
					}
				}
				if (!hasRater) {
					EfficientRater newRater = new EfficientRater(raterID);
					newRater.addRating(movieID, ratingValue);
					raters.add(newRater);
				}
			}
			reader.close();
		}
		catch (Exception e) {
			System.out.println("Error in reading rating CSV file.");
			e.printStackTrace();
		}
		return raters;
	}
	
	public static void testLoadRaters() {
		String fileName = "data\\ratings.csv";
		ArrayList<EfficientRater> raters = loadRaters(fileName);
		System.out.println("The number of raters " + raters.size());
//		for (Rater rater: raters) {
//			System.out.println(rater.getMyID() + "\t" + rater.numRatings());
//			ArrayList<Rating> ratings = rater.getMyRatings();
//			for (Rating rating: ratings) {
//				System.out.println(rating.getItem() + "\t" + rating.getValue());
//			}
//		}
		
		//find the number of ratings for a particular rater
		for (EfficientRater rater: raters) {
			if (rater.getMyID().equals("193")) {
				System.out.println("The number of ratings: " + rater.numRatings());
			}
		}
		
		//find the maximum number of ratings by any rater
		int maxNum = 0;
		ArrayList<String> ratersWithMaxRatings = new ArrayList<>();
		for (EfficientRater rater: raters) {
			int num = rater.numRatings();
			if (num > maxNum) {
				maxNum = num;
			}
		}
		for (EfficientRater rater: raters) {
			if (rater.numRatings() == maxNum)
				ratersWithMaxRatings.add(rater.getMyID());
		}
		System.out.println("The max num of ratings: " + maxNum);
		System.out.println("Raters with max ratings: " + ratersWithMaxRatings 
				+ "\tNumber: " + ratersWithMaxRatings.size());
		
		//find the number of ratings a particular movie has
		int ratingsNum = 0;
		for (EfficientRater rater: raters) {
			if (rater.hasRating("1798709")) {
				ratingsNum++;
			}
		}
		System.out.println("The number of ratings for a movie: " + ratingsNum);
		
		//determine how many different movies have been rated by all these raters
		ArrayList<String> movies = new ArrayList<>();
		for (EfficientRater rater: raters) {
			ArrayList<String> moviesRated = rater.getItemsRated();
			for (String movie: moviesRated) {
				if (!movies.contains(movie))
				{
					movies.add(movie);
				}
			}
		}
		System.out.println("Rated movies Num: " + movies.size());
	}


}
