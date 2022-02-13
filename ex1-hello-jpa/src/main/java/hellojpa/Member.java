package main.java.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

// jpa 로드 알림
@Entity
public class Member {
	// key 알려줘야 함
	@Id
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
