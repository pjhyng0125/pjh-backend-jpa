package main;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import domain.Address;
import domain.Member;
import domain.MemberType;
import domain.Team;

public class PathExpressionMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Team t = new Team();
			t.setName("Arsnal");
			em.persist(t);
			
			Member m = new Member();
			m.setUsername("park");	
			m.setAge(29);
			m.setTeam(t);
			em.persist(m);
			
			em.flush();
			em.clear();
			
			String q = "";
			
			// 단일 값 연관 필드: @*ToOne
//			q = "select m.team.name from Member m";
//			List<String> result = em.createQuery(q, String.class).getResultList();
//			for (String s : result) {
//				System.out.println("s = " + s);
//			}
			
			// 컬렉션 값 연관 필드: @*ToMany
//			q = "select t.members from Team t";
//			Collection result = em.createQuery(q, Collection.class).getResultList();
//			for (Object s : result) {
//				System.out.println("s = " + s);
//			}
			
			// 컬렉션 개수 select
			q = "select t.members.size from Team t";
			Integer result = em.createQuery(q, Integer.class).getSingleResult();
			System.out.println("result : " + result);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
