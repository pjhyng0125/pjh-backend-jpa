package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;
import domain.Team;

public class NamedQueryMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {	
			Member m = new Member();
			m.setUsername("park");	
			m.setAge(29);
			em.persist(m);
						
			em.flush();
			em.clear();
			
			List<Member> result = em.createNamedQuery("Member.findByUsername", Member.class)
					.setParameter("username", "park")
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
