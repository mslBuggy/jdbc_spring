package com.acn;

import java.util.List;

import com.model.Address;
import com.model.Customer;

public interface CustomerDao {
	List<Customer> getAllCustomer();
	List<Customer> getCustomerByName(String fName, String lName);
	Customer getCustomerById(Long id);
	
	//List<Address> getAddressForCustomer(Customer cust);
	
	void addCustomer(Customer cust);
	void changeCustomer(Customer cust);
	// optional vereinheitlichen als saveCustomer, inkl. Prüfung ob bereits existiert
	
	void deleteCustomer(Customer cust);
	
}
