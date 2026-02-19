package com.springboot.jpa_shop.service;

import com.springboot.jpa_shop.JpaShopApplication;
import com.springboot.jpa_shop.data.entity.Delivery;
import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.entity.OrderItem;
import com.springboot.jpa_shop.data.entity.item.Item;
import com.springboot.jpa_shop.service.OrderSearch;
import com.springboot.jpa_shop.data.repository.MemberRepository;
import com.springboot.jpa_shop.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
	
	        // 엔티티 조회
	        Member member = memberRepository.findOne(memberId);
	        Item item = itemService.findOne(itemId);
	
	        // 주문상품 생성
	        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count); 
	        // 배송정보 생성
	        //Delivery delivery = new Delivery(member.getAddress());
	        //Delivery delivery = new Delivery();
	        Delivery delivery = new Delivery();
	        
	        // 주문 생성
	        Order order = Order.createOrder(member, delivery, orderDate, orderItem);

	        // 주문 저장
	        orderRepository.save(order);
	        
	        return order.getId();
	    }
	
	    // 주문 취소
	    public void cancelOrder(Long orderId) {
	
	        //주문 엔티티 조회
	        Order order = orderRepository.findOne(orderId);
	
	        //주문 취소
	        order.cancelOrder();
	    }
	
	    // 주문 검색
	    public List<Order> findOrders(OrderSearch orderSearch) {
	        return orderRepository.findAll(orderSearch);
	    }

}
