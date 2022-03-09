package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;
import domain.Team;

public class EntityDirectMain {
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
			
			String jpql = "";
			jpql = "select m from Member m where m = :member";
			List<Member> result = em.createQuery(jpql, Member.class)
					.setParameter("member", m)
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
