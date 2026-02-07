package com.springboot.jpa_model.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@Setter
//@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDER_ITEMS")
public class OrderItem {
	
	    @Id
	    @Column(name = "ORDER_ITEM_ID")
	    @SequenceGenerator(sequenceName =  "order_item_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ITEM_ID")
	    private Item item;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ORDER_ID")
	    private Order order;

	    @Column(name = "ORDER_PRICE", nullable = false)
	    private int orderPrice; //주문 가격

	    @Column(name = "ORDER_CNT", nullable = false)
	    private int count;      //주문 수량

	    // Setter
	    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
	        OrderItem orderItem = new OrderItem();
	        
	        orderItem.item = item;
	    	orderItem.orderPrice = orderPrice;
	        orderItem.count = count;
	        
	        item.removeStock(count);
	        
	        return orderItem;
	    }
	    
	    public void setOrder(Order order) {
	        this.order = order;
	    }
	    
	    @Override
	    public String toString() {
	        return "OrderItem{" +
	                "id=" + id +
	                ", buyPrice=" + orderPrice +
	                ", count=" + count +
	                '}';
	    }
}
