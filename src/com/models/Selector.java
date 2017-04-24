package com.models;

public class Selector {
	
	String s_index;
	int s_oldValue;
	int s_newValue;
	String s_localPrediction;

	
	public String getS_index() {
		return s_index;
	}


	public void setS_index(String s_index) {
		this.s_index = s_index;
	}


	public int getS_oldValue() {
		return s_oldValue;
	}


	public void setS_oldValue(int s_oldValue) {
		this.s_oldValue = s_oldValue;
	}


	public int getS_newValue() {
		return s_newValue;
	}


	public void setS_newValue(int s_newValue) {
		this.s_newValue = s_newValue;
	}


	public String getS_localPrediction() {
		return s_localPrediction;
	}


	public void setS_localPrediction(String s_localPrediction) {
		this.s_localPrediction = s_localPrediction;
	}


	public Selector(String s_index, int s_oldValue, int s_newValue,
			String s_localPrediction) {
		super();
		this.s_index = s_index;
		this.s_oldValue = s_oldValue;
		this.s_newValue = s_newValue;
		this.s_localPrediction = s_localPrediction;
	}
}
