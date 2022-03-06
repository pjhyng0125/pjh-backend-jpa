package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;

public class PagingMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			for (int i=0; i < 20; i++) {
				Member m = new Member();
				m.setUsername("park" + i);				
				em.persist(m);
			}
						
			em.flush();
			em.clear();
			
			List<Member> result = em.createQuery("select m from Member m order by m.age desc")
					.setFirstResult(0)
					.setMaxResults(10)
					.getResultList();
			System.out.println("list size : " + result.size());
			for (Member mem : result) {
				System.out.println("mem : " + mem);
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
