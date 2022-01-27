package com.ying.model;

/**
 * The class Rating stores the data about one rating of an item.
 * 
 * @author Ying Deng 
 * @version 2022-01-15
 */
public class Rating implements Comparable<Rating> {
	private String item;
	private double value;

	public Rating(String item, String value) {
		this.item = item;
		this.value = Double.parseDouble(value.trim());
	}
	
	public Rating(String item, double value) {
		this.item = item;
		this.value = value;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = Double.parseDouble(value.trim());
	}

	@Override
	public String toString() {
		return "Rating [item=" + item + ", value=" + value + "]";
	}

	public int compareTo(Rating other) {
		if (value < other.value)
			return -1;
		if (value > other.value)
			return 1;

		return 0;
	}

}
