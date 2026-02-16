package com.springboot.jpa_prac;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa_prac.data.entity.Member;
import com.springboot.jpa_prac.data.entity.OrderStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class Jpql_02 {
	
		@BeforeEach
		void before() {
		    System.out.println("---- 테스트 시작 ----");
		}
		
		@PersistenceContext
		private EntityManager em;

		// Left Join Query Enum parameter 사용
		@Test
		void JPQL_Join_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			OrderStatus status = OrderStatus.ORDER;
			
			// when
			String query = "SELECT DISTINCT m FROM Member m LEFT OUTER JOIN m.orders o "
											+	"ON o.orderStatus = :status";
			
			List<Member> resultList = em.createQuery(query, Member.class)
										.setParameter("status", status)
										.getResultList();
			
			// then
			resultList.forEach(m -> System.out.println("JPQL_Join_test : " + m.toString()));
		}
		
		// Named Query parameter 사용
		@Test
		void Name_Query_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();

			List<Member> resultList =  em.createNamedQuery("Member.findByUsername", Member.class)
										.setParameter("username", "thonam")
										.getResultList();
			
			System.out.println("Name_Query_test : " + resultList.toString());
		}
		
		// Named Count parameter 사용
		@Test
		void Name_Count_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();

			Long count =  em.createNamedQuery("Member.count", Long.class)
							.getSingleResult();
			
			System.out.println("Name_Count_test : " + count);
		}
		
}
