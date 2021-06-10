package com.yseon.scoremanager;

public class ScoreList {
	int idx;
	String name;
	int midScore;
	int finScore;
	String created;
	
	ScoreList(){
		
	}
	
	ScoreList(String name, int mid, int fin, String created){
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.created = created;
	}
	
	ScoreList(int idx, String name, int mid, int fin, String created) {
		this.idx = idx;
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.created = created;
	}

}
