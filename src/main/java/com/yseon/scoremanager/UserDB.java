package com.yseon.scoremanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class UserDB {
	
	public void createTable() {
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "CREATE TABLE scoretable (idx INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, midScore REAL, finScore REAL, created TEXT)";
			//sqlite는 정수 INTEGER, 실수는  REAL, 문자열 TEXT
			Statement statement = connection.createStatement();
			//Statement는 Connection으로 연결한 객체에게 Query 작업을 실행하기 위한 객체
			int result = statement.executeUpdate(query);
			statement.close();
			
			//close
			connection.close();
			
		} catch (Exception e) {
		}
	}
	
	public void insertData(String name, double midScore, double finScore, String created) {
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "INSERT INTO scoretable (name,midScore,finScore,created) VALUES('" + name +"',"+ midScore +","+ finScore +",'"+ created + "')";
			//sqlite는 정수 INTEGER, 실수는  REAL, 문자열 TEXT
			Statement statement = connection.createStatement();
			//Statement는 Connection으로 연결한 객체에게 Query 작업을 실행하기 위한 객체
			int result = statement.executeUpdate(query);
			statement.close();
			
			//close
			connection.close();
			
		} catch (Exception e) {
		}
	}
	
	public String selectData() {
		String resultString = "";
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "SELECT * FROM scoretable";
			//sqlite는 정수 INTEGER, 실수는  REAL, 문자열 TEXT
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String name = resultSet.getString("name");
				double midScore = resultSet.getDouble("midScore");
				double finScore = resultSet.getDouble("finScore");
				String created = resultSet.getString("created");
				resultString += "<tr>";
				resultString += "<td>" + idx + "</td><td>" + name +"</td><td>" 
				+ midScore + "</td><td>" + finScore + "</td><td>" + created + "</td>";
				resultString += "<td><a href='update?idx=" + idx +"'>수정</a></td>";
				resultString += "<td><a href='delete?idx=" + idx +"'>삭제</a></td>";
				resultString += "</tr>";
			}
			preparedStatement.close();
			
			//close
			connection.close();
			
		} catch (Exception e) {
		}
		return resultString;
	}
	
	public void updateData(int idx, String name, double midScore, double finScore, String updated) {
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "UPDATE scoretable SET name=?, midScore=?, finScore=?, created=? WHERE idx=" + idx;
			//sqlite는 정수 INTEGER, 실수는  REAL, 문자열 TEXT
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDouble(2, midScore);
			preparedStatement.setDouble(3, finScore);
			preparedStatement.setString(4, updated);
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();

			//close
			connection.close();
			
		} catch (Exception e) {
		}
	}
	
	public ScoreList detailsData(int idx) {
		ScoreList resultData = new ScoreList();
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "SELECT * FROM scoretable WHERE idx=" + idx;
			//sqlite는 정수 INTEGER, 실수는  REAL, 문자열 TEXT
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				resultData.idx = resultSet.getInt("idx");
				resultData.name = resultSet.getString("name");
				resultData.midScore = resultSet.getDouble("midScore");
				resultData.finScore = resultSet.getDouble("finScore");
				resultData.created = resultSet.getString("created");
			}
			preparedStatement.close();
			
			//close
			connection.close();
			
		} catch (Exception e) {
		}
		return resultData;
	}
	
	public void deleteData(int idx) {
		try {
			//open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "d:/tomcat/score.db", config.toProperties());
			//Connection은 DB에 접속할 때 필요한 클래스
			
			//use
			String query = "DELETE FROM scoretable WHERE idx=" + idx;
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();

			//close
			connection.close();
			
		} catch (Exception e) {
		}
	}

}
