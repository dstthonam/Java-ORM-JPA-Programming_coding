package com.springboot.jpa_prac;

import static org.mockito.Mockito.ignoreStubs;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa_prac.data.entity.Address;
import com.springboot.jpa_prac.data.entity.Member;
import com.springboot.jpa_prac.data.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.experimental.var;

@SpringBootTest
@Transactional
public class Jpql_01 {
	
		@PersistenceContext
		private EntityManager em;

		// TypedQuery 사용 : 결과가 정해져 있을 때
		/**
		@Test
		void TypedQuery_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			// when
			TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
			
			List<Member> resultList = query.getResultList();
			
			// then
			resultList.forEach(m -> System.out.println("Member : " + m.toString()));
			
		}
		 */

		// Query 사용 : 결과가 여러 타입일 때
		/**
		@Test
		void Query_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.persist(Member.createMember("thosin", "jinju", "dongginro 1 street", "92"));
			em.flush();
			em.clear();
			
			// when
			Query query = em.createQuery("SELECT m.username, m.address FROM Member m");
			
			List resultList = query.getResultList();
			
			// then
			//resultList.forEach(m -> System.out.println("Member : " + m.toString()));
			for (Object obj : resultList) {
				Object[] result = (Object[]) obj;
				
				System.out.println("username = " + result[0]);
				System.out.println("address = " + result[1]);
				
//				for(var i = 0; i < result.length; i++) {
//					System.out.println(i + " = " + result[i]);	
//				}
			}
			
		}
		 */

		// Param 사용 : 쿼리 파라미터 설정
		/**
		@Test
		void QueryParam_test() {
			
			String userParam = "thonam";
			
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			// when
//			TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);
//			
//			query.setParameter("username", userParam); // 이름 기준 파라미터
//			//query.setParameter(1, userParam); // 위치 기준 파라미터
//			
//			List<Member> resultList = query.getResultList();
			
			List<Member> resultList = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
																			.setParameter("username", userParam)
																			.getResultList();
			
			// then
			resultList.forEach(m -> System.out.println("Member : " + m.toString()));
			
		}
		*/

		// Entity Projection 사용 : 엔티티로 조회 쿼리 컬럼 설정
		/**
		@Test
		void Entity_Project_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			// when
//			TypedQuery<Order> query = em.createQuery("SELECT m.orders FROM Member m", Order.class);
//			
//			List<Order> resultList = query.getResultList();
			
			TypedQuery<Address> query = em.createQuery("SELECT m.address FROM Member m", Address.class);
			
			List<Address> resultList = query.getResultList();
			
			// then
			resultList.forEach(m -> System.out.println("Adress : " + m.toString()));
			
		}
		 */
		
		// Entity Projection 사용 : 엔티티로 조회 쿼리 컬럼 설정
		@Test
		void iterator_project_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			// when
			Query query = em.createQuery("SELECT m.username, m.address FROM Member m");
			
			List resultList = query.getResultList();
			
			Iterator iterator = resultList.iterator();

			// then
			while (iterator.hasNext()) {
				Object[] row = (Object[]) iterator.next();
				String username = (String) row[0];
				Address address = (Address) row[1];
				
				System.out.println("Username : " + username);
				System.out.println("address : " + address);
			}
			
		}
}