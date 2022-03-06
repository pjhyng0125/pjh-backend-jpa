package main.java.hellojpa;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

public class CriteriaSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Member> q = cb.createQuery(Member.class);
			
			Root<Member> m = q.from(Member.class);
			CriteriaQuery<Member> cq = q.select(m);
			
			String username = "khell";
			if (username != null) {
				cq.where(cb.equal(m.get("username"), username));				
			}
			
			List<Member> result = em.createQuery(cq).getResultList();
			
			for (Member mem : result) {
				System.out.println("[Criteria] member name : " + mem.getUsername());
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
