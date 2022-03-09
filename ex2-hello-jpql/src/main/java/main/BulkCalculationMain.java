package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;
import domain.Team;

public class BulkCalculationMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {			
			Member m = new Member();
			m.setUsername("member1");
			m.setAge(29);
			em.persist(m);
			
			Member m2 = new Member();
			m2.setUsername("member2");
			m2.setAge(29);
			em.persist(m2);
			
			Member m3 = new Member();
			m3.setUsername("member3");
			m3.setAge(29);
			em.persist(m3);
			
			String jpql = "";
			jpql = "update Member m set m.age = 30";
			// flush 자동 호출
			int resultCnt = em.createQuery(jpql)
					.executeUpdate();
			System.out.println("resultCnt : " + resultCnt);
			
			em.clear();			
			Member fm = em.find(Member.class, m.getId());
			System.out.println("fm : " + fm);
			
			System.out.println("m age : " + m.getAge());
			System.out.println("m2 age : " + m2.getAge());
			System.out.println("m3 age : " + m3.getAge());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
