package main;

import java.util.List;

import javax.persistence.*;

import domain.Address;
import domain.Member;
import domain.MemberType;
import domain.Team;

public class FunCaseMain {
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
			
			Member m2 = new Member();
			m2.setUsername("관리자");
			m2.setAge(99);
			em.persist(m2);
			
			em.flush();
			em.clear();
			
//			String q = "select coalesce(m.username, '이름 없는 회원') from Member m";
//			String q = "select nullif(m.username, '관리자') from Member m";
			String q = "select function('group_concat', m.username) from Member m";
			
			List<String> result = em.createQuery(q, String.class).getResultList();
			
			for (String s : result) {
				System.out.println("s = " + s);
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
