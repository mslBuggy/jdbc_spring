package com.service;

import java.util.List;

import com.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomer();
	List<Customer> getCustomerByName(String fName, String lName);
	Customer getCustomerById(Long id);
	
	void addCustomer(Customer cust);
	void changeCustomer(Customer cust);
	// optional vereinheitlichen als saveCustomer, inkl. Prüfung ob bereits existiert
	
	void deleteCustomer(Customer cust);
}
