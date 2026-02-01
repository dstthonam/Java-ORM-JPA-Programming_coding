package com.springboot.jpa_model.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ORDERS",
				uniqueConstraints = {@UniqueConstraint(
						name = "DATE_STATUS_UNIQUE",
						columnNames = {"MEMBER_ID", "ORDER_DATE", "ORDER_STATUS"} )
				})
public class Order {
	
	    @Id
	    @Column(name = "ORDER_ID")
	    @SequenceGenerator(sequenceName =  "order_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	    private Long id;
	
	    @Column(name = "MEMBER_ID", nullable = false)
	    private Long memberId;
	
	    @Column(name = "ORDER_DATE", nullable = false)
	    private Date orderDate;     //주문시간
	
	    @Enumerated(EnumType.STRING)
	    @Column(name = "ORDER_STATUS", nullable = false)
	    private OrderStatus status; //주문상태
	
}