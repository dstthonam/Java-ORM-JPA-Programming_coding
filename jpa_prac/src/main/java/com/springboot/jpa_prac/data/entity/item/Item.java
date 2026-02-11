package com.springboot.jpa_prac.data.entity.item;

import java.util.ArrayList;
import java.util.List;

import com.springboot.jpa_prac.data.entity.Category;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
//@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "ITEMS",
				uniqueConstraints = {@UniqueConstraint(
						name = "ITEM_NAME_PRICE_QPA_UNIQUE",
						columnNames = {"ITEM_NAME", "ITEM_PRICE", "ITEM_QPA"} )
				})
public abstract class Item {
	
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
	
	    @ManyToMany(mappedBy = "items")
	    private List<Category> categories = new ArrayList<Category>();

	    // Setter
	    public Item(String name, int price, int stockQuantity) {
	        this.name = name;
	        this.price = price;
	        this.stockQuantity = stockQuantity;
	    }
	    
	    // 재고 감소
	    public void removeStock(int quantity) {
	        if (this.stockQuantity < quantity) {
	            throw new IllegalStateException("재고 부족");
	        }
	        
	        this.stockQuantity -= quantity;
	    }
	    
	    // 가격변경
	    public void changePrice(int price) {
	        if (price < 0) {
	            throw new IllegalArgumentException("가격은 음수 불가");
	        }
	        
	        this.price = price;
	    }
	    
	    @Override
	    public String toString() {
	        return "Item{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", price=" + price +
	                '}';
	    }
}
