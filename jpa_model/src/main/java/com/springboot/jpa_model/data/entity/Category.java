package com.springboot.jpa_model.data.entity;

import java.util.ArrayList;
import java.util.List;

import com.springboot.jpa_model.data.entity.item.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "CATEGORY",
				uniqueConstraints = {@UniqueConstraint(
						name = "CATEGORY_UNIQUE",
						columnNames = {"ITEM_NAME", "ITEM_PRICE", "ITEM_QPA"} )
				})
public class Category {

	    @Id
	    @Column(name = "CATEGORY_ID")
	    @SequenceGenerator(sequenceName =  "category_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
		private Long id;
		
		private String category_name;

	    @ManyToMany
	    @JoinTable(name = "CATEGORY_ITEM",
	    			joinColumns = @JoinColumn(name = "CATEGORY_ID"),
	    			inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
		private List<Item> items = new ArrayList<Item>();

	    @ManyToOne
	    @JoinColumn(name = "PARENT_ID")
	    private Category parent;

	    @OneToMany(mappedBy = "parent")
	    private List<Category> child = new ArrayList<Category>();

	    //==연관관계 메서드==//
	    public void addChildCategory(Category child) {
	        this.child.add(child);
	        child.setParent(this);
	    }

	    public void addItem(Item item) {
	        items.add(item);
	    }

	    @Override
	    public String toString() {
	        return "Category{" +
	                "id=" + id +
	                ", name='" + category_name + '\'' +
	                '}';
	    }
}
