package main.java.hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "mem_sqe_gen", sequenceName = "MEM_SEQ", initialValue = 1, allocationSize = 50)
//@TableGenerator(
//		name = "MEMBER_SEQ_GENERATOR",
//		table = "MY_SEQUENCES",
//		pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_sqe_gen")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String username;
	
//	@OneToOne
//	@JoinColumn(name = "LOCKER_ID")
//	private Locker locker;
	
//	@ManyToMany
//	@JoinTable(name = "MEMBER_PRODUCT")
//	private List<Product> products = new ArrayList<>();
//	@OneToMany(mappedBy = "member")
//	private List<MemberProduct> memberProducts = new ArrayList<>();
	
	public Member() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public Locker getLocker() {
//		return locker;
//	}
//
//	public void setLocker(Locker locker) {
//		this.locker = locker;
//	}
//
//	public List<MemberProduct> getMemberProducts() {
//		return memberProducts;
//	}
//
//	public void setMemberProducts(List<MemberProduct> memberProducts) {
//		this.memberProducts = memberProducts;
//	}
	
}
