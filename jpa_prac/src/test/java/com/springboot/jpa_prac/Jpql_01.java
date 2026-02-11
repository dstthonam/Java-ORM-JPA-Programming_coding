package com.springboot.jpa_prac;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.jpa_prac.data.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class Jpql_01 {
	
		@PersistenceContext
		private EntityManager em;

		@Test
		void Jpql_test() {
			em.persist(Member.createMember("thonam", "busan", "marine 1 street", "91"));
			em.flush();
			em.clear();
			
			// when
			TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
			
			List<Member> resultList = query.getResultList();
			
			// then
			resultList.forEach(m -> System.out.println("Member : " + m.toString()));
			
		}

}