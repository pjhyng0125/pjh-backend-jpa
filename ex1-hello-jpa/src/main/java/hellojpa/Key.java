package main.java.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Key {
	@Id
	private Long key;
	private String value;
	public Key() {}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
