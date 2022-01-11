package com.ying.model;

public class Rating implements Comparable<Rating> {
	private String item;
	private double value;

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

	public void setValue(double value) {
		this.value = value;
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
