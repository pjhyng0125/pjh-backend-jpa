package main;

import java.util.List;

import javax.persistence.*;

import domain.Address;
import domain.Member;
import domain.Team;

public class ProjectionMain {
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
			
			// Entity
			List<Member> ml = em.createQuery("select m from Member m", Member.class)
					.getResultList();
			
			Member fm = ml.get(0);
			fm.setAge(30);
			
			// Entity
//			List<Team> tl = em.createQuery("select t from Member m join m.team t", Team.class)
//					.getResultList(); 
			
			// Embedded
//			Address a = em.createQuery("select o.address from Order o", Address.class)
//					.getSingleResult();
			
			// Scalar #1
//			List l = em.createQuery("select distinct m.username, m.age from Member m")
//					.getResultList();
//			Object o = l.get(0);
//			Object[] result = (Object[]) o;
			
			// Scalar #2
			List<Object[]> l = em.createQuery("select distinct m.username, m.age from Member m")
					.getResultList();
			Object[] result = l.get(0);
			System.out.println("*****username : " + result[0]);
			System.out.println("*****age : " + result[1]);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
