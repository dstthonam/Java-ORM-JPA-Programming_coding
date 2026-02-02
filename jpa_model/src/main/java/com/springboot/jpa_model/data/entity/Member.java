package com.springboot.jpa_model.data.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MEMBERS",
				uniqueConstraints = {@UniqueConstraint(
						name = "NAME_UNIQUE",
						columnNames = {"MEMBER_NAME"} )
				})
public class Member {
	
	    @Id
	    @Column(name = "MEMBER_ID")
	    @SequenceGenerator(sequenceName =  "member_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	    private Long id;

	    @Column(name = "MEMEBER_NAME", nullable = false)
	    private String username;

	    @Column(name = "MEMEBER_CITY")
	    private String city;
	    
	    @Column(name = "MEMEBER_STREET")
	    private String street;

	    @Column(name = "MEMEBER_ZIPCODE")
	    private String zipcode;

	    @OneToMany(mappedBy = "member")
	    private List<Order> orders = new ArrayList<Order>();
}
