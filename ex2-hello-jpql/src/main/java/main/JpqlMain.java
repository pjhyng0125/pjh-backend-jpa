package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;

public class JpqlMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Member m = new Member();
			m.setUsername("park");
					
			em.persist(m);
			
//			TypedQuery<Member> q1 = em.createQuery("select m from Member m", Member.class)
//			TypedQuery<String> q2 = em.createQuery("select m.username from Member m", String.class);
//			Query q3 = em.createQuery("select m.username, m.id from Member m", String.class);
			
//			List<Member> resultList = q1.getResultList();
			
			Member resultSingle = em.createQuery("select m from Member m where m.username = :username", Member.class)
					.setParameter("username", "park")
					.getSingleResult(); // 결과가 반드시 1개일 경우에만 사용, Spring Data JAP -> 결과가 없으면 null
			System.out.println("resultSingle : " + resultSingle.getUsername());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
