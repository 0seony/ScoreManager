package com.yseon.scoremanager;

public class ScoreList {
	int idx;
	String name;
	double midScore;
	double finScore;
	String created;
	
	ScoreList(){
		
	}
	
	ScoreList(String name, double mid, double fin, String created){
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.created = created;
	}
	
	ScoreList(int idx, String name, double mid, double fin, String created) {
		this.idx = idx;
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.created = created;
	}

}
