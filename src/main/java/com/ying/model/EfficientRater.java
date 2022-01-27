package com.ying.model;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    HashMap<String, Rating> myRatings;
    
	public EfficientRater(String myID) {
		this.myID = myID;
		myRatings = new HashMap<>();
	}
	
    
    public String getMyID() {
		return myID;
	}

	public void setMyID(String myID) {
		this.myID = myID;
	}

	public HashMap<String, Rating> getMyRatings() {
		return myRatings;
	}


	public void setMyRatings(HashMap<String, Rating> myRatings) {
		this.myRatings = myRatings;
	}

	@Override
	public void addRating(String item, String rating) {
        myRatings.put(item, new Rating(item,rating));
    }
    
	@Override
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }
    
	@Override
    public double getRating(String item) {
        Rating rating = myRatings.get(item);
        if (rating != null) {
        	return rating.getValue();
        }
        return -1;
    }
    
	@Override
    public int numRatings() {
        return myRatings.size();
    }
    
	@Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key: myRatings.keySet()) {
        	list.add(key);
        }
        return list;
    }
}
