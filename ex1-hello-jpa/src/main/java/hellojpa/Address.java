package main.java.hellojpa;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String street;
//	@Column(name = "ZIPCODE")
	private String zipCode;
	
	public Address() {}
	
	public Address(String city, String street, String zipCode) {
		super();
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(city, street, zipCode);
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
		return Objects.equals(city, other.city) && Objects.equals(street, other.street)
				&& Objects.equals(zipCode, other.zipCode);
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	private void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	private void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
