package main.java.hellojpa;

import java.util.Set;

import javax.persistence.*;

public class CollectionTypeSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member m = new Member();
			m.setUsername("PARK");
			m.setHomAddress(new Address("seoul", "seongdong", "1234"));
			
			m.getFavoriteFoods().add("치킨");
			m.getFavoriteFoods().add("피자");
			m.getFavoriteFoods().add("햄버거");

			m.getAddressHistory().add(new AddressEntitiy("old1", "seongdong1", "5678"));
			m.getAddressHistory().add(new AddressEntitiy("old2", "seongdong2", "9101"));
			
			em.persist(m);
			
			em.flush();
			em.clear();
			
			System.out.println("=== find start ===");
			Member fm = em.find(Member.class, m.getId());
			System.out.println("fm : " + fm);
			
//			List<Address> addressHistory = fm.getAddressHistory();
//			for (Address a : addressHistory) {
//				System.out.println("address : " + a.getCity());
//			}
			
			Set<String> favoriteFoods = fm.getFavoriteFoods();
			for (String fa : favoriteFoods) {
				System.out.println("favoriteFood : " + fa);				
			}
			
			// 회원 주소 변경
//			fm.setHomAddress(new Address("new", "newStreet", "9999"));
			
			// 음식 변경
			fm.getFavoriteFoods().remove("치킨");
			fm.getFavoriteFoods().add("한식");
			// 컬렉션 변경 시, jpa 가 DB 변경
			
			// 주소 기록 변경
//			fm.getAddressHistory().remove(new Address("old1", "seongdong1", "5678"));
//			fm.getAddressHistory().add(new Address("new", "seongdong3", "9999"));
			fm.getAddressHistory().remove(new AddressEntitiy("old1", "seongdong1", "5678"));
			fm.getAddressHistory().add(new AddressEntitiy("new", "seongdong3", "9999"));
						
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
