package com.springboot.jpa_model.data.entity;

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
//@Setter
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
	    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems, Date orderDate) {
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
	
	    public void addOrderItem(OrderItem orderItem) {
	        orderItems.add(orderItem); // 객체 상태 일치화
	        orderItem.setOrder(this);
	    }

	    public void assignDelivery(Delivery delivery) {
	        this.delivery = delivery;
	        delivery.setOrder(this); // 객체 상태 일치화
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