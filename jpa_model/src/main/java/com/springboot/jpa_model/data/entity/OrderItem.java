package com.springboot.jpa_model.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "ORDER_ITEMS",
				uniqueConstraints = {@UniqueConstraint(
						name = "DATE_STATUS_UNIQUE",
						columnNames = {"MEMBER_ID", "ORDER_DATE", "ORDER_STATUS"} )
				})
public class OrderItem {
	
	    @Id
	    @Column(name = "ORDER_ITEM_ID")
	    @SequenceGenerator(sequenceName =  "order_item_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
	    private Long id;
	
	    @Column(name = "ITEM_ID")
	    private Long itemId;
	    
	    @Column(name = "ORDER_ID")
	    private Long orderId;

	    @Column(name = "ORDER_PRICE", nullable = false)
	    private int orderPrice; //주문 가격

	    @Column(name = "ORDER_CNT", nullable = false)
	    private int count;      //주문 수량
	
	    @Override
	    public String toString() {
	        return "OrderItem{" +
	                "id=" + id +
	                ", buyPrice=" + orderPrice +
	                ", count=" + count +
	                '}';
	    }
}
