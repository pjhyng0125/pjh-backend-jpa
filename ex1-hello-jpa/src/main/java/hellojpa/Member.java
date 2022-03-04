package main.java.hellojpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "mem_sqe_gen", sequenceName = "MEM_SEQ", initialValue = 1, allocationSize = 50)
//@TableGenerator(
//		name = "MEMBER_SEQ_GENERATOR",
//		table = "MY_SEQUENCES",
//		pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
//public class Member extends BaseEntity {
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_sqe_gen")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String username;
	
	// LAZY : 지연 로딩
	@ManyToOne(fetch = FetchType.LAZY)
	// EAGER : 즉시 로딩 => SQL 문제, JPQL N+1 문제 발생
//	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Team team;
	
//	@OneToOne
//	@JoinColumn(name = "LOCKER_ID")
//	private Locker locker;
	
//	@ManyToMany
//	@JoinTable(name = "MEMBER_PRODUCT")
//	private List<Product> products = new ArrayList<>();
//	@OneToMany(mappedBy = "member")
//	private List<MemberProduct> memberProducts = new ArrayList<>();
	
	// 기간
	@Embedded
	private Period period;
	
	// 주소
	@Embedded
	private Address homAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city",
			column = @Column(name = "WORK_CITY")),
		@AttributeOverride(name = "street",
			column = @Column(name = "WORK_STREET")),
		@AttributeOverride(name = "zipCode",
			column = @Column(name = "WORK_ZIPCODE"))
	})
	private Address workAddress;
	
	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD", joinColumns = 
			@JoinColumn(name = "MEMBER_ID")
	)
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();
	
//	@ElementCollection
//	@CollectionTable(name = "ADDRESS", joinColumns = 
//			@JoinColumn(name = "MEMBER_ID")
//	)
//	private List<Address> addressHistory = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "MEMBER_ID")
	private List<AddressEntitiy> addressHistory = new ArrayList<>();
			
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

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

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Address getHomAddress() {
		return homAddress;
	}

	public void setHomAddress(Address homAddress) {
		this.homAddress = homAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}

	public List<AddressEntitiy> getAddressHistory() {
		return addressHistory;
	}

	public void setAddressHistory(List<AddressEntitiy> addressHistory) {
		this.addressHistory = addressHistory;
	}

//	public List<Address> getAddressHistory() {
//		return addressHistory;
//	}
//
//	public void setAddressHistory(List<Address> addressHistory) {
//		this.addressHistory = addressHistory;
//	}
	
	
	
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
