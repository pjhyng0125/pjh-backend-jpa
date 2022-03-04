package main.java.jpabook.jpashop.domain;

import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class Address {
	@Column(length = 10)
	private String city;
	@Column(length = 20)
	private String street;
	@Column(length = 5)
	private String zipCode;
	public String fullAddress() {
		return getCity() + " " + getStreet() + " " + getZipCode(); 
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
}
