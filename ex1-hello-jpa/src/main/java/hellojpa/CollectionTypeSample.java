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
			
			m.getFavoriteFoods().add("ġŲ");
			m.getFavoriteFoods().add("����");
			m.getFavoriteFoods().add("�ܹ���");

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
			
			// ȸ�� �ּ� ����
//			fm.setHomAddress(new Address("new", "newStreet", "9999"));
			
			// ���� ����
			fm.getFavoriteFoods().remove("ġŲ");
			fm.getFavoriteFoods().add("�ѽ�");
			// �÷��� ���� ��, jpa �� DB ����
			
			// �ּ� ��� ����
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
