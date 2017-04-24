package com.models;

public class GlobalPredictor {
	
	String g_index;
	int g_oldValue;
	int g_newValue;
	String g_localPrediction;
	
	public String getG_index() {
		return g_index;
	}
	public void setG_index(String g_index) {
		this.g_index = g_index;
	}
	public int getG_oldValue() {
		return g_oldValue;
	}
	public void setG_oldValue(int g_oldValue) {
		this.g_oldValue = g_oldValue;
	}
	public int getG_newValue() {
		return g_newValue;
	}
	public void setG_newValue(int g_newValue) {
		this.g_newValue = g_newValue;
	}
	public String getG_localPrediction() {
		return g_localPrediction;
	}
	public GlobalPredictor(String g_index, int g_oldValue, int g_newValue,
			String g_localPrediction) {
		super();
		this.g_index = g_index;
		this.g_oldValue = g_oldValue;
		this.g_newValue = g_newValue;
		this.g_localPrediction = g_localPrediction;
	}
	public void setG_localPrediction(String g_localPrediction) {
		this.g_localPrediction = g_localPrediction;
	}

}

