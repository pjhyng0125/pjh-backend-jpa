package main.java.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		/**
		 * 1. Persistence ���� ���� �б�
		 * 2. EntityManagerFactory Ŭ���� ����
		 * 3. EntityManager ����
		 * 4. parameter : persistence.xml > persistence-unit.name ��
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		/**
		 * EntityManager : Transaction �������� �������� �� ( = Connection)
		 * ��û �ø��� create & close
		 * ������ �� ���� X
		 */
		EntityManager em = emf.createEntityManager();
		
		// ��� ������ ������ Transcation �ȿ���
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// INSERT
//			Member m = new Member();
//			m.setId(2L);
//			m.setUsername("HelloB");
//			em.persist(m);			
			
			// SELECT
//			Member findMember = em.find(Member.class, 1L);
//			System.out.println("findMember.id = " + findMember.getId());
//			System.out.println("findMember.name = " + findMember.getName());
			
			// DELETE
//			em.remove(findMember);
			
			// UPDATE -> Persist �� �ص� ��.
			// JPA�� ���� Entity �������� ���� ���� �� ��ȭ ����
//			findMember.setName("HelloJPA");
			
			// JPQL : ��ü ��� ���� �ۼ�, DB ������ ���� ���� X
//			List<Member> rst = em.createQuery("select m from Member as m", Member.class)
//					.setFirstResult(5)
//					.setMaxResults(10)
//					.getResultList();
//			
//			for (Member m : rst) {
//				System.out.println("member.name = " + m.getName());
//			}
			
//			em.flush(); // Ʈ����� DB �ݿ�
//			em.detach(findMember); // �ؿ��� ����
//			em.clear(); // ���Ӽ� ���ؽ�Ʈ ��ü �ʱ�ȭ
//			em.close(); // ���Ӽ� ���ؽ�Ʈ ����
			
			// Entitiy Mapping
			Member m = new Member();
//			m.setId(3L);
			m.setUsername("A");
			
			Member m2 = new Member();
			m2.setUsername("B");
			
			Member m3 = new Member();
			m3.setUsername("C");
			
			em.persist(m); // 1, 51
			em.persist(m2);
			em.persist(m3);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();		
	}
}
