package main.java.hellojpa;

import javax.persistence.*;

public class lazyLoadingSample {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// ch08. ���� �ε� lazy
			Team t = new Team();
			t.setName("new team");
			em.persist(t);
			
			Member m = new Member();
			m.setUsername("member1");
			m.setTeam(t);
			
			em.persist(m);

			em.flush();
			em.clear();

			// Member ������ ���� Member ��
			Member fm = em.find(Member.class, m.getId());
			// Team�� Proxy�� ������
			System.out.println("findMember team class : " + fm.getTeam().getClass());
			System.out.println("=====");
			// Team�� ���� �Ӽ� ���� ��� ��, ���� ȣ��
			System.out.println("member's team name: " + fm.getTeam().getName());
			System.out.println("=====");
			
			// ��� �ε��� ���, proxy �ʿ� ����. em.find �������� �̸� ��ȸ

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();

	}
}
