package com.acn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnector {

	public MyConnector() {
		// TODO Auto-generated constructor stub
	}
	public Connection getMyConnection() { // NB: Erzeugt Objekt vom Typ Connection (Interface)
		String url = "jdbc:mysql://localhost:3306/jumpsecond1903",
				user = "trainee",
				pw = "trainee";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
