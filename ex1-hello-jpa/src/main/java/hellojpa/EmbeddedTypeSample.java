package main.java.hellojpa;

import java.time.LocalDateTime;

import javax.persistence.*;

public class EmbeddedTypeSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member m = new Member();
			m.setUsername("PARK");
			m.setHomAddress(new Address("seoul", "seongdong", "1234"));
			m.setWorkAddress(new Address("jongro", "cetral", "5678"));
			m.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now().plusYears(1)));
			em.persist(m);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
