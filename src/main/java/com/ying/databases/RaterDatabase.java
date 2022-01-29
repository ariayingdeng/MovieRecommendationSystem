/**
 * RaterDatabase is more efficient to get information about raters.
 * 
 * @author Ying Deng
 * @version 2022-01-29
 */

package com.ying.databases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.*;

import com.ying.interfaces.Rater;
import com.ying.model.EfficientRater;
import com.ying.model.FirstRatings.RatingHeaders;

public class RaterDatabase {

	private static HashMap<String, Rater> ourRaters;

	private static void initialize() {
		if (ourRaters == null) {
			ourRaters = new HashMap<String, Rater>();
		}
	}

	public static void initialize(String filename) {
		if (ourRaters == null) {
			ourRaters = new HashMap<String, Rater>();
			addRatings("data\\" + filename);
		}
	}

	public static void addRatings(String filename) {
		initialize();
		FileReader fileReader;
		try {
			fileReader = new FileReader(filename);
			CSVFormat csvFormat = CSVFormat.Builder.create().setHeader(RatingHeaders.class).setSkipHeaderRecord(true)
					.build();
			CSVParser records = CSVParser.parse(fileReader, csvFormat);
			for (CSVRecord rec : records) {
				String id = rec.get(RatingHeaders.rater_id);
				String item = rec.get(RatingHeaders.movie_id);
				String rating = rec.get(RatingHeaders.rating);
				addRaterRating(id, item, rating);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in reading movie CSV file.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addRaterRating(String raterID, String movieID, String rating) {
		initialize();
		Rater rater = null;
		if (ourRaters.containsKey(raterID)) {
			rater = ourRaters.get(raterID);
		} else {
			rater = new EfficientRater(raterID);
			ourRaters.put(raterID, rater);
		}
		rater.addRating(movieID, rating);
	}

	public static Rater getRater(String id) {
		initialize();
		return ourRaters.get(id);
	}

	public static ArrayList<Rater> getRaters() {
		initialize();
		ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
		return list;
	}

	public static int size() {
		return ourRaters.size();
	}

}
