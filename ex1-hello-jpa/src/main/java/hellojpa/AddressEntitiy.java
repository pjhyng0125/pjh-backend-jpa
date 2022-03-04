package main.java.hellojpa;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS_ENTITY")
public class AddressEntitiy {
	
	@Id @GeneratedValue
	private Long id;
	
	private Address address;
	
	public AddressEntitiy() {}
	
	public AddressEntitiy(Address address) {
		super();
		this.address = address;
	}
	
	public AddressEntitiy(String city, String street, String zipCode) {
		this.address = new Address(city, street, zipCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntitiy other = (AddressEntitiy) obj;
		return Objects.equals(address, other.address);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
