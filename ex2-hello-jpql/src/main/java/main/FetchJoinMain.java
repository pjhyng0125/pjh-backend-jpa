package main;

import java.util.List;

import javax.persistence.*;

import domain.Member;
import domain.Team;

public class FetchJoinMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Team t = new Team();
			t.setName("team1");
			em.persist(t);
			
			Team t2 = new Team();
			t2.setName("team2");
			em.persist(t2);
			
			Team t3 = new Team();
			t3.setName("team3");
			em.persist(t3);
			
			Member m = new Member();
			m.setUsername("member1");
			m.setAge(29);
			m.setTeam(t);
			em.persist(m);
			
			Member m2 = new Member();
			m2.setUsername("member2");
			m2.setTeam(t);
			em.persist(m2);
			
			Member m3 = new Member();
			m3.setUsername("member3");
			m3.setTeam(t2);
			em.persist(m3);
						
			em.flush();
			em.clear();
			
			String jpql = "";
			// 패치 조인
//			jpql = "select m from Member m join fetch m.team";
			jpql = "select distinct t from Team t join fetch t.members";
			List<Team> result = em.createQuery(jpql, Team.class)
					.getResultList();
			jpql = "select m from Team t join fetch m.members";
			System.out.println("list size : " + result.size());
			for (Team team : result) {
				System.out.println("team : " + team.getName() + " | members : " + team.getMembers().size());
				for (Member mem : team.getMembers()) {
					System.out.println("-> member : " + mem);
				}
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
