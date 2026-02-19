package com.springboot.jpa_shop.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ORDERS",
				uniqueConstraints = {@UniqueConstraint(
						name = "DATE_STATUS_UNIQUE",
						columnNames = {"MEMBER_ID", "ORDER_DATE", "ORDER_STATUS"} )
				})
public class Order extends BaseEntity {
	
	    @Id
	    @Column(name = "ORDER_ID")
	    @SequenceGenerator(sequenceName =  "order_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "MEMBER_ID")
	    private Member member;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	    
	    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinColumn(name = "DELIVERY_ID")
	    private Delivery delivery;

	    @Column(name = "ORDER_DATE", nullable = false)
	    private Date orderDate;     //주문시간
	
	    @Enumerated(EnumType.STRING)
	    @Column(name = "ORDER_STATUS", nullable = false)
	    private OrderStatus orderStatus; //주문상태
	    
	    // Setter
	    public static Order createOrder(Member member, Delivery delivery, Date orderDate, OrderItem... orderItems) {
	        Order order = new Order();
	        
	        order.changeMember(member);
	        order.assignDelivery(delivery);
	        order.orderStatus = OrderStatus.ORDER;
	        order.orderDate = orderDate;

	        for (OrderItem orderItem : orderItems) {
	            order.addOrderItem(orderItem);
	        }

	        return order;
	    }
	    
	    public void changeMember(Member member) {
	        if (this.member != null) { //기존 관계 제거
	            this.member.getOrders().remove(this);
	        }
	        
	        this.member = member;
	        member.getOrders().add(this); // 객체 상태 일치화
	    }

	    public void changeOrderStatus(OrderStatus orderStatus) {
	        this.orderStatus = orderStatus;
	    }
	    
	    public void addOrderItem(OrderItem orderItem) {
	        orderItems.add(orderItem); // 객체 상태 일치화
	        orderItem.setOrder(this);
	    }

	    public void assignDelivery(Delivery delivery) {
	        this.delivery = delivery;
	        delivery.setOrder(this); // 객체 상태 일치화
	    }

	    // 주문 취소
	    public void cancelOrder() {

	        if (delivery.getStatus() == DeliveryStatus.COMP) {
	            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
	        }

	        this.changeOrderStatus(OrderStatus.CANCEL);
	        for (OrderItem orderItem : orderItems) {
	            orderItem.cancelOrder();
	        }
	    }

	    // 전체 주문 가격 조회
	    public int getTotalPrice() {
	        int totalPrice = 0;
	        
	        for (OrderItem orderItem : orderItems) {
	            totalPrice += orderItem.getTotalPrice();
	        }
	        
	        return totalPrice;
	    }
	    
	    @Override
	    public String toString() {
	        return "Order{" +
	                "id=" + id +
	                ", orderDate=" + orderDate +
	                ", status=" + orderStatus +
	                '}';
	    }
}