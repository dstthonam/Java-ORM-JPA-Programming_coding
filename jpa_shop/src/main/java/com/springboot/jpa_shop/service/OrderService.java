package com.springboot.jpa_shop.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_shop.JpaShopApplication;
import com.springboot.jpa_shop.data.entity.Delivery;
import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.entity.OrderItem;
import com.springboot.jpa_shop.data.entity.item.Item;
import com.springboot.jpa_shop.data.repository.MemberRepository;
import com.springboot.jpa_shop.data.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	    private final JpaShopApplication jpaShopApplication;
		
	    @Autowired MemberRepository memberRepository;
	    @Autowired OrderRepository orderRepository;
	    @Autowired ItemService itemService;
	
	    OrderService(JpaShopApplication jpaShopApplication) {
	        this.jpaShopApplication = jpaShopApplication;
	    }
		
	    // 주문
	    public Long order(Long memberId, Long itemId, int count, Date orderDate) {
	    	
	    	if (orderDate == null) {
	            LocalDate today = LocalDate.now(); // 오늘 날짜 구하기
	            orderDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        }

	        Member member = memberRepository.findById(memberId)
														.orElseThrow(() -> new IllegalArgumentException("회원이 없습니다.")); // 엔티티 조회
	        Item item = itemService.findOne(itemId);
	    	
	        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count); // 주문상품 생성
	        Delivery delivery = new Delivery(); // 배송정보 생성
	    	
	        Order order = Order.createOrder(member, delivery, orderDate, orderItem);
	        
	        orderRepository.save(order);

	        return order.getId();
	    }
	
	    // 주문 취소
	    public void cancelOrder(Long orderId) {
	        Order order = orderRepository.findById(orderId)
	        									.orElseThrow(() -> new IllegalArgumentException("주문이 없습니다.")); //주문 엔티티 조회
	
	        order.cancelOrder(); //주문 취소
	    }
	
	    // 주문 검색
	    public List<Order> findOrders(OrderSearch orderSearch) {
	        return orderRepository.findAll(orderSearch);
	    }

}
