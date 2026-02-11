package com.springboot.jpa_prac.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DELIVERY",
		uniqueConstraints = {@UniqueConstraint(
				name = "ITEM_NAME_PRICE_QPA_UNIQUE",
				columnNames = {"ITEM_NAME", "ITEM_PRICE", "ITEM_QPA"} )
		})
public class Delivery {

	    @Id
	    @Column(name = "DELIVERY_ID")
	    @SequenceGenerator(sequenceName =  "Delivery_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Delivery_seq")
		private Long id;
		
	    @OneToOne(mappedBy = "delivery")
		private Order order;

	    @Embedded
	    private Address address;
	    
		@Enumerated(EnumType.STRING)
		private DeliveryStatus status;
	
}
