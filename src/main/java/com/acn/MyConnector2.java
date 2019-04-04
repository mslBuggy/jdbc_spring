 package com.acn;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;



public class MyConnector2 {
private DataSource ds;
Properties props = new Properties();
	public MyConnector2() {
		
		
	}
	
	public Connection getMyConnection() throws SQLException {
		
		
		
		
		
		return ds.getConnection();
	}
	
}
