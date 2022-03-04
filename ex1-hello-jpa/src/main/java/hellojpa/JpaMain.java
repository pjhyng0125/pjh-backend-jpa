package main.java.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

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
//			Member m = new Member();
//			m.setId(3L);
//			m.setUsername("A");
//			
//			Member m2 = new Member();
//			m2.setUsername("B");
//			
//			Member m3 = new Member();
//			m3.setUsername("C");
//			
//			em.persist(m); // 1, 51
//			em.persist(m2);
//			em.persist(m3);
			
			// ch06. ��� ���� - ��Ӱ������
//			Movie movie = new Movie();
//			movie.setDirector("������3");
//			movie.setActor("�׼ǹ��3");
//			movie.setName("����3");
//			movie.setPrice(200003);
//			em.persist(movie);
			
//			Movie findMovie = em.find(Movie.class, movie.getId());
//			Item findMovie = em.find(Item.class, movie.getId());
//			System.out.println("findMovie = " + findMovie);
			
			// ch06. ��� ���� - MappedSuperclass
			Member member = new Member();
			member.setUsername("park");
//			member.setCreatedBy("park");
//			member.setCreatedDate(LocalDateTime.now());
			em.persist(member);
			
			em.flush();
			em.clear();
			
			// ch08. ���Ͻ�
			// ���Ͻ� Ŭ����
			Member refMember = em.getReference(Member.class, member.getId());
			System.out.println("findMember id : " + refMember.getId());
			// DB���� �������� ������ �� �� ���� ������ ���� ���Ǵ� ������ ���� ����
//			System.out.println("findMember name : " + refMember.getUsername());
			
			// ���Ͻ� ���� �ʱ�ȭ (���̹�����Ʈ ����, jpa�� ����)
			Hibernate.initialize(refMember);
			
			// ���Ͻ� �ν��Ͻ� �ʱ�ȭ ���� Ȯ��
			System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(refMember));
			
			// ���Ͻ� Ŭ���� Ȯ��
			System.out.println("proxy class : " + refMember.getClass().getName());
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();		
	}
}
