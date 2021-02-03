package com.tei.database;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {

//	private static String databaseURL = "jdbc:mysql://localhost:3306/";
//	private static String databaseName = "sudoku";
//	private static String username = "root";
//	private static String password = "root";

	private static String databaseURL;
	private static String username;
	private static String password;
	private static Connection connection;

	private static MySQLConnection instance = null;

	private MySQLConnection() {};

	public static MySQLConnection getInstance() {
		if (instance == null) {
			instance = new MySQLConnection();
		}
		return instance;
	}

	public Connection getConnection() {

//		String url = databaseURL + databaseName + "?useSSL=false&characterEncoding=UTF-8";
		String url = getInstance().readDbProperties() + "?useSSL=false&characterEncoding=UTF-8";
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public static void closeConnection() throws SQLException {
		connection.close();
	}

	private String readDbProperties() {
		try {
			Properties properties = new Properties();
			InputStream inputStream = MySQLConnection.class.getResourceAsStream("/database.properties");
			properties.load(inputStream);
			inputStream.close();

			databaseURL = properties.getProperty("jdbc.url");
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			return databaseURL;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Error loading properties file");
	}

//	public static void main(String[] args) {
//		getConnection();
//	}
}
