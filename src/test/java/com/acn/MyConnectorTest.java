package com.acn;

import static org.junit.Assert.*;
	
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.acn.config.Config;

import java.sql.ResultSet;

import java.sql.Statement;

public class MyConnectorTest {
	private static ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
	private static Connection myconn;

	@BeforeClass
	public static void setUp() throws Exception {
		
		myconn = (Connection) ac.getBean("connectionbean");
		getData();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		
		((ConfigurableApplicationContext)ac).close();
		myconn.close();
	}
	
	@Test
	public void testGetMyConnection() throws SQLException {
		assertNotNull(myconn);
		Connection conn = myconn;
		assertNotNull(conn);
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductVersion());
		System.out.println(conn.getMetaData().getDriverName());
		
	}
	
	
	public static void getData() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = myconn;
		String SQLString = "select * from customer";
		
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQLString);
		while(rs.next()) {
			System.out.println(rs.getLong("id") + " "+ rs.getString("fname") + " "+ rs.getString("lname"));
			
		}
		
		
	}

}
