package com.springboot.jpa_model.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
//@ToString
@Table(name = "ITEMS",
				uniqueConstraints = {@UniqueConstraint(
						name = "ITEM_NAME_PRICE_QPA_UNIQUE",
						columnNames = {"ITEM_NAME", "ITEM_PRICE", "ITEM_QPA"} )
				})
public class Item {
	
	    @Id
	    @Column(name = "ITEM_ID")
	    @SequenceGenerator(sequenceName =  "item_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	    private Long id;

	    @Column(name = "ITEM_NAME", nullable = false)
	    private String name;        //이름

	    @Column(name = "ITEM_PRICE", nullable = false)
	    private int price;          //가격
	    
	    @Column(name = "ITEM_QPA", nullable = false)
	    private int stockQuantity;  //재고수량
	
	    @Override
	    public String toString() {
	        return "Item{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", price=" + price +
	                '}';
	    }
	    
}
