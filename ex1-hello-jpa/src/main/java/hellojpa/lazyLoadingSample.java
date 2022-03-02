package main.java.hellojpa;

import javax.persistence.*;

public class lazyLoadingSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// ch08. 지연 로딩 lazy
			Team t = new Team();
			t.setName("new team");
			em.persist(t);
			
			Member m = new Member();
			m.setUsername("member1");
			m.setTeam(t);
			
			em.persist(m);

			em.flush();
			em.clear();

			// Member 가져올 때는 Member 만
			Member fm = em.find(Member.class, m.getId());
			// Team은 Proxy로 가져옴
			System.out.println("findMember team class : " + fm.getTeam().getClass());
			System.out.println("=====");
			// Team의 내부 속성 실제 사용 시, 쿼리 호출
			System.out.println("member's team name: " + fm.getTeam().getName());
			System.out.println("=====");
			
			// 즉시 로딩의 경우, proxy 필요 없음. em.find 시점부터 미리 조회

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();

	}
}
