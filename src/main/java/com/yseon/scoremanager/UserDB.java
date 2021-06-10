package com.yseon.scoremanager;

import java.sql.Connection;
import java.sql.DriverManager;

import org.sqlite.SQLiteConfig;

public class UserDB {
	
	public void createTable() {
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/scorelist.db", config.toProperties());
			//use
			
			//close
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
