package com.acn;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyConnector2Test {
	
	MyConnector2 myConn;

	@Before
	public void setUp() throws Exception {
		myConn = new MyConnector2();
	}

	@After
	public void tearDown() throws Exception {
		myConn = null;
	}

	@Test
	public void testGetMyConnection() throws SQLException, FileNotFoundException, IOException {
		assertNotNull(myConn);
		Connection conn = myConn.getMyConnection();
		assertNotNull(conn);
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		System.out.println(conn.getMetaData().getDriverName());
		conn.close();
		//fail("Not yet implemented");
		
	}
	@Test
	public void testGetCustomers() throws SQLException, FileNotFoundException, IOException {
		Statement stmt= null;
		Connection conn = myConn.getMyConnection();
		String sql = "select * from customer";
		ResultSet rs = null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()){
			System.out.println(rs.getLong("id")+"\t"+rs.getString("fname")+"\t"+rs.getString("lname"));
	}
		assertNotNull(conn);
		conn.close();
		//fail("Not yet implemented");
		
	}

}
