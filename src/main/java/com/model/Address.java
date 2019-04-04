package com.model;

public class Address {
	private Long id;
	private String zipcode;
	private String city;
	private String street;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(Long id, String zipcode, String city, String street) {
		super();
		this.id = id;
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
	}

	public Address(String zipcode, String city, String street) {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [" + (id != null ? "id=" + id + ", " : "")
				+ (zipcode != null ? "zipcode=" + zipcode + ", " : "") + (city != null ? "city=" + city + ", " : "")
				+ (street != null ? "street=" + street : "") + "]";
	}

}
