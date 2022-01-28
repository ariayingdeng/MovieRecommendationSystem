package com.ying.model;

import java.util.ArrayList;

import com.ying.interfaces.Rater;

/**
 * The class Rater keeps track of one rater and all their ratings.
 * 
 * @author Ying Deng 
 * @version 2022-01-15
 */
public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;
    
	public PlainRater(String myID) {
		this.myID = myID;
		myRatings = new ArrayList<>();
	}
	
    
    public String getMyID() {
		return myID;
	}

	public void setMyID(String myID) {
		this.myID = myID;
	}
	

	public ArrayList<Rating> getMyRatings() {
		return myRatings;
	}


	public void setMyRatings(ArrayList<Rating> myRatings) {
		this.myRatings = myRatings;
	}

	@Override
	public void addRating(String item, String rating) {
        myRatings.add(new Rating(item,rating));
    }
    
	@Override
    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        return false;
    }
    
	@Override
    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
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
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }

}
