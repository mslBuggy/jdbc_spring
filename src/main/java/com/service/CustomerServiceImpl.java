package com.service;

import java.util.List;

import com.acn.CustomerDao;
import com.model.Customer;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao custdao;

	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return custdao.getAllCustomer();
	}

	@Override
	public List<Customer> getCustomerByName(String fName, String lName) {
		// TODO Auto-generated method stub
		return custdao.getCustomerByName(fName, lName);
	}

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return custdao.getCustomerById(id);
	}

	@Override
	public void addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		custdao.addCustomer(cust);
	}

	@Override
	public void changeCustomer(Customer cust) {
		// TODO Auto-generated method stub
		custdao.changeCustomer(cust);
	}

	@Override
	public void deleteCustomer(Customer cust) {
		// TODO Auto-generated method stub
		custdao.deleteCustomer(cust);
	}

	public CustomerServiceImpl(CustomerDao custdao) {
		super();
		this.custdao = custdao;
	}

}
