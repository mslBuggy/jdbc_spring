package com.acn.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.acn.CustomerDao;
import com.acn.CustomerDaoJdbcImpl;
import com.acn.MyConnector2;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;

@Configuration
//@EnableAspectJAutoProxy
@ComponentScan("com.acn")

public class Config {
	
	Properties props = new Properties();

	@Bean(name = "connectorbean")
	public DataSource connectorBean() {
		DataSource ds = null;
		try {
			props.load(new FileReader(new File("db.props")));
			String url = props.getProperty("url");
			String user = props.getProperty("dbuser");
			String passwd = props.getProperty("passwd");
			String driverclass = props.getProperty("driverclass");
			String poolsize = props.getProperty("poolsize");
			BasicDataSource bds = new BasicDataSource();
			bds.setUrl(url);
			bds.setPassword(passwd);
			bds.setDriverClassName(driverclass);
			bds.setUsername(user);
			bds.setInitialSize(Integer.parseInt(poolsize));

			ds = bds;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

//	@Bean (name = "connectionbean")
//	public Connection getMyConnectionBean() throws SQLException {
//
//		return ds.getConnection();
//	}
	
	@Bean
	public CustomerService custServBean() {
		return new CustomerServiceImpl(custDaoBean());
	}
	@Bean
	public CustomerDao custDaoBean() {
		return new CustomerDaoJdbcImpl(connectorBean());
	}


	public Config() {
		
	}

}
