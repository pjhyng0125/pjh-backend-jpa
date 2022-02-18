package main.java.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		/**
		 * 1. Persistence 설정 정보 읽기
		 * 2. EntityManagerFactory 클래스 생성
		 * 3. EntityManager 생성
		 * 4. parameter : persistence.xml > persistence-unit.name 값
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		/**
		 * EntityManager : Transaction 단위마다 만들어줘야 함 ( = Connection)
		 * 요청 시마다 create & close
		 * 스레드 간 공유 X
		 */
		EntityManager em = emf.createEntityManager();
		
		// 모든 데이터 변경은 Transcation 안에서
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
			
			// UPDATE -> Persist 안 해도 됨.
			// JPA를 통해 Entity 가져오면 직접 관리 및 변화 감지
//			findMember.setName("HelloJPA");
			
			// JPQL : 객체 대상 쿼리 작성, DB 종류에 따라 종속 X
//			List<Member> rst = em.createQuery("select m from Member as m", Member.class)
//					.setFirstResult(5)
//					.setMaxResults(10)
//					.getResultList();
//			
//			for (Member m : rst) {
//				System.out.println("member.name = " + m.getName());
//			}
			
//			em.flush(); // 트랜잭션 DB 반영
//			em.detach(findMember); // 준영속 상태
//			em.clear(); // 영속성 컨텍스트 전체 초기화
//			em.close(); // 영속성 컨텍스트 종료
			
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
