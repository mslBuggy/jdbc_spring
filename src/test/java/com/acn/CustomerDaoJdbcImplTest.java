package com.acn;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.acn.config.Config;
import com.model.Address;
import com.model.Customer;

public class CustomerDaoJdbcImplTest {
	private static CustomerDao custdao;
	private static ApplicationContext ac;
	private static Connection myConn;
	private final String FNAME_OK = "Aretha";
	private final String LNAME_OK = "Franklin";
	private final String FNAME_FAIL = "0?&&4";
	private final String LNAME_FAIL = "34%%2";
	private final Long ID_OK = 1000L;
	private final Long ID_FAIL = -20L;
	private final Long ID_HAS_ADDRESS = 1003L;
	private final Long ID_TO_CHANGE = 1005L;
	
	private final String ZIPCODE = "12345";
	private final String CITY = "Testenhausen";
	private final String STREET = "Testergasse 123";
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		ac = new AnnotationConfigApplicationContext(Config.class);
		//myConn = (Connection) ac.getBean("connectionBean");
		custdao = (CustomerDao) ac.getBean("custDaoBean");
	}

	@AfterClass
	public static void tearDown() throws Exception {
	//	myConn.close();
	}

	@Test
	public void testGetAllCustomer() {
		System.out.println("test is running");
		List<Customer> custlist = null;
		assertNotNull(custdao);
		custlist = custdao.getAllCustomer();
		assertNotNull(custlist);
		custlist.forEach(System.out::println);
	}

	@Test
	public void testGetCustomerByName() {
		List<Customer> custlist = null;
		assertNotNull(custdao);
		custlist = custdao.getCustomerByName(FNAME_OK, LNAME_OK);
		assertFalse(custlist.isEmpty());
		custlist.forEach(System.out::println);
		custlist = custdao.getCustomerByName(FNAME_FAIL, LNAME_FAIL);
		assertTrue(custlist.isEmpty());
	}

	@Test
	public void testGetCustomerById() {
		Customer cust = null;
		assertNotNull(custdao);
		cust = custdao.getCustomerById(ID_OK);
		assertNotNull(cust);
		System.out.println(cust);
		cust = custdao.getCustomerById(ID_FAIL);
		assertNull(cust);
	}

	@Test
	public void testAddCustomer() {
		assertNotNull(custdao);
		List<Customer> allCusts = custdao.getAllCustomer();
		assertFalse(allCusts.isEmpty());
		int listSizeBefore = allCusts.size();
		String fname = FNAME_OK;
		String lname = LNAME_OK+'n';
		Customer newCust = new Customer(fname, lname, "dancing", null, 'f');
		custdao.addCustomer(newCust);
		allCusts = custdao.getAllCustomer();
		assertNotEquals(listSizeBefore, allCusts.size());
		assertNotNull(newCust.getId());
		
	}
	
	@Test
	public void testAddCustomerWithNewAddress() {
		assertNotNull(custdao);
		List<Customer> allCusts = custdao.getAllCustomer();
		assertFalse(allCusts.isEmpty());
		int listSizeBefore = allCusts.size();
		String fname = FNAME_OK;
		String lname = LNAME_OK+'n';
		Customer newCust = new Customer(fname, lname, "dancing", null, 'f');
		newCust.addAddress(new Address(ZIPCODE, CITY, STREET));
		custdao.addCustomer(newCust);
		allCusts = custdao.getAllCustomer();
		assertNotEquals(listSizeBefore, allCusts.size());
		assertNotNull(newCust.getId());
		System.out.println(newCust);
	}

	@Test
	public void testChangeCustomer() {
		assertNotNull(custdao);
		String newLastName = LNAME_OK + '-' + LNAME_OK;
		Customer custChange = custdao.getCustomerById(ID_TO_CHANGE);
		custChange.setLastName(newLastName);
		custdao.changeCustomer(custChange);
	}

	@Test
	public void testDeleteCustomer() {
		assertNotNull(custdao);
		List<Customer> custsToDel = custdao.getCustomerByName(FNAME_OK, LNAME_OK+'n');
		assertNotNull(custsToDel);
		assertFalse(custsToDel.isEmpty());
		for (Customer custDel : custsToDel) {
			custdao.deleteCustomer(custDel);
		}
		custsToDel = custdao.getCustomerByName(FNAME_OK, LNAME_OK+'n');
		assertTrue(custsToDel.isEmpty());
		
	}
	
	@Test
	public void testGetAddressFromCustomer() {
		assertNotNull(custdao);
		Customer cust = custdao.getCustomerById(ID_HAS_ADDRESS);
		List<Address> customerAddressList = cust.getAddressList();
		assertNotNull(customerAddressList);
		assertFalse(customerAddressList.isEmpty());
		for (Address address : customerAddressList) {
			assertNotNull(address.getId());
			System.out.println(address);
		}
	}

}
