package com.models;

public class LocalPredictor {
	
	String l_index;
	int l_oldValue;
	int l_newValue;
	String l_localPrediction;
	private final int MIN = 0, MAX = 3;
	
	public LocalPredictor(){
		this.l_index = null;
		this.l_oldValue = 0;
		this.l_newValue = 0;
		this.l_localPrediction = null;	
	}

	public LocalPredictor(String l_index, int l_oldValue, int l_newValue,
			String l_localPrediction) {
		super();
		this.l_index = l_index;
		this.l_oldValue = l_oldValue;
		this.l_newValue = l_newValue;
		this.l_localPrediction = l_localPrediction;
	}

	public String getL_index() {
		return l_index;
	}

	public void setL_index(String l_index) {
		this.l_index = l_index;
	}

	public int getL_oldValue() {
		return l_oldValue;
	}

	public void setL_oldValue(int l_oldValue) {
		this.l_oldValue = l_oldValue;
	}

	public int getL_newValue() {
		return l_newValue;
	}

	public void setL_newValue(int l_newValue) {
		this.l_newValue = l_newValue;
	}

	public String getL_localPrediction() {
		return l_localPrediction;
	}

	public void setL_localPrediction(String l_localPrediction) {
		this.l_localPrediction = l_localPrediction;
	}

	public int getMIN() {
		return MIN;
	}

	public int getMAX() {
		return MAX;
	}
	
	
}
