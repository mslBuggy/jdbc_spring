package com.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private Long id;
	private String firstName;
	private String lastName;
	private String hobby;
	private String telephone;
	private char gender;
	private List<Address> addressList;

	public void addAddress(Address address) {
		// TODO Auto-generated method stub
		if(addressList == null) {
			addressList = new ArrayList<>();
		}
		addressList.add(address);
	}
	public List<Address> getAddressList() {
		return addressList;
	}


	public Customer() {
		// TODO Auto-generated constructor stub
		
	}


	public Customer(String firstName, String lastName, String hobby, String telephone, char gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.hobby = hobby;
		this.telephone = telephone;
		this.gender = gender;
	}

	public Customer(Long id, String firstName, String lastName, String hobby, String telephone, char gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hobby = hobby;
		this.telephone = telephone;
		this.gender = gender;
	}

	public Customer(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [" + (id != null ? "id=" + id + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (hobby != null ? "hobby=" + hobby + ", " : "")
				+ (telephone != null ? "telephone=" + telephone + ", " : "") + "gender=" + gender + ", "
				+ (addressList != null ? "addressList=" + addressList : "") + "]";
	}


	public Long getId() {
		return id;
	}
	
	/**
	 * ID darf sich eigentlich keinesfalls ändern,
	 * aber leider wird der Setter von unserer Software benötigt
	 * @param id
	 */
	public void setId(Long id) { 
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getHobby() {
		return hobby;
	}
	
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
}
