package com.springboot.jpa_shop.data.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.entity.OrderStatus;
import com.springboot.jpa_shop.data.entity.QMember;
import com.springboot.jpa_shop.data.entity.QOrder;
import com.springboot.jpa_shop.service.OrderSearch;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
		
	    private final EntityManager em;
	    private final JPAQueryFactory queryFactory;
	    
	    public void save(Order order) {
	        em.persist(order);
	    }
	
	    public Order findOne(Long id) {
	        return em.find(Order.class, id);
	    }
	    
	    public List<Order> findAll(OrderSearch search) {
			
			QOrder order = QOrder.order; // 기본 인스턴스
	        QMember member = QMember.member;
	    	
	    	// JPQL로 수정 pis
	        return queryFactory
			                .selectFrom(order)
			                .join(order.member, member)
			                .where(
			                        statusEq(search.getOrderStatus()),
			                        memberNameLike(search.getMemberName())
			                )
			                .limit(1000)
			                .fetch();
	    }

	    private BooleanExpression statusEq(OrderStatus status) {
	        return status != null ? QOrder.order.orderStatus.eq(status) : null;
	    }

	    private BooleanExpression memberNameLike(String name) {
	        return StringUtils.hasText(name) ? QMember.member.username.like("%" + name + "%") : null;
	    }

}
