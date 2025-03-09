/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.db;
//import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//import java.sql.DriverManager;
//import java.sql.SQLException;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/CinemaDB";// ?useSSL=false
	private static final String USER = "root";
	private static final String PASSWORD = "123456789";
//    
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
//
//	public static Connection connectDB() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			return DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11589964", "sql11589964",
//					"UnAPsV6nsM");
//		} catch (Exception e) {
//			return null;
//		}
//	}
}

