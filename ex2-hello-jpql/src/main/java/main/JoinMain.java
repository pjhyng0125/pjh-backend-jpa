package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;
import domain.Team;

public class JoinMain {
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
			
			String jpql = "";
			// 내부 조인
//			jpql = "select m from Member m join m.team t";
			// 외부 조인
//			jpql = "select m from Member m left outer join m.team t";
			// 세타 조인
//			jpql = "select m from Member m, Team t where m.username = t.name";
			// ON 조인 (연관관계 O)
//			jpql = "select m from Member m left join m.team t on t.name='Arsnal'";
			// ON 조인 (연관관계 X)
			jpql = "select m from Member m left join Team t on t.name='Arsnal'";
			List<Member> result = em.createQuery(jpql, Member.class)
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
