package main.java.hellojpa;

import java.util.List;

import javax.persistence.*;

public class JpqlSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
//			Member m1 = new Member();
//			m1.setUsername("phello");
//			em.persist(m1);
//			
//			Member m2 = new Member();
//			m2.setUsername("ahello");
//			em.persist(m2);
//			
//			Member m3 = new Member();
//			m3.setUsername("khell");
//			em.persist(m3);
//			
//			em.flush();
//			em.clear();
			
			// 조건절 컬럼명은 객체 기준 
			String jpql = "select m From Member m where m.username like '%hello%'";
			List<Member> result = em.createQuery(jpql, Member.class).getResultList();
			
			for (Member m : result) {
				System.out.println("[jpql] member name : " + m.getUsername());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
