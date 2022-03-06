package main;

import java.util.List;

import javax.persistence.*;

import domain.Address;
import domain.Member;
import domain.MemberType;
import domain.Team;

public class JpqlTypeMain {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Member m = new Member();
			m.setUsername("park");
			m.setAge(29);
			m.setType(MemberType.ADMIN);
			em.persist(m);
			
			em.flush();
			em.clear();
			
			String q = "select m.username, 'HELLO', true from Member m where m.type = :userType";
			
			List<Object[]> l = em.createQuery(q)
					.setParameter("userType", MemberType.ADMIN)
					.getResultList();
			Object[] result = l.get(0);
			System.out.println("*****username : " + result[0]);
			System.out.println("*****age : " + result[1]);
			System.out.println("*****true : " + result[2]);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
