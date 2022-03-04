package main.java.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

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
			
			// ch06. 고급 매핑 - 상속관계매핑
//			Movie movie = new Movie();
//			movie.setDirector("박찬욱3");
//			movie.setActor("액션배우3");
//			movie.setName("괴물3");
//			movie.setPrice(200003);
//			em.persist(movie);
			
//			Movie findMovie = em.find(Movie.class, movie.getId());
//			Item findMovie = em.find(Item.class, movie.getId());
//			System.out.println("findMovie = " + findMovie);
			
			// ch06. 고급 매핑 - MappedSuperclass
			Member member = new Member();
			member.setUsername("park");
//			member.setCreatedBy("park");
//			member.setCreatedDate(LocalDateTime.now());
			em.persist(member);
			
			em.flush();
			em.clear();
			
			// ch08. 프록시
			// 프록시 클래스
			Member refMember = em.getReference(Member.class, member.getId());
			System.out.println("findMember id : " + refMember.getId());
			// DB에서 가져오지 않으면 알 수 없는 데이터 값이 사용되는 시점에 쿼리 실행
//			System.out.println("findMember name : " + refMember.getUsername());
			
			// 프록시 강제 초기화 (하이버네이트 제공, jpa는 없음)
			Hibernate.initialize(refMember);
			
			// 프록시 인스턴스 초기화 여부 확인
			System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(refMember));
			
			// 프록시 클래스 확인
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
