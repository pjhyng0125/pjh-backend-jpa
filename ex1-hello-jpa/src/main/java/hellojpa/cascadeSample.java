package main.java.hellojpa;

import javax.persistence.*;

public class cascadeSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Child c1 = new Child();
			Child c2 = new Child();
			
			Parent p = new Parent();
			p.addChild(c1);
			p.addChild(c2);

			em.persist(p);
//			em.persist(c1);
//			em.persist(c2);
			
			em.flush();
			em.clear();
			
			Parent fp = em.find(Parent.class, p.getId());
			fp.getChildList().remove(0);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
