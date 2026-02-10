package com.springboot.jpa_model.data.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
//@Setter
@Table(name = "MEMBERS",
				uniqueConstraints = {@UniqueConstraint(
						name = "NAME_UNIQUE",
						columnNames = {"MEMBER_NAME"} )
				})
public class Member extends BaseEntity {
	
	    @Id
	    @Column(name = "MEMBER_ID")
	    @SequenceGenerator(sequenceName =  "member_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
	    private Long id;

	    @Column(name = "MEMBER_NAME", nullable = false)
	    private String username;

	    @Embedded
	    private Address address;
	    
	    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	    private List<Order> orders = new ArrayList<Order>();
	    
	    public static Member createMember(String username, String city, String street,  String zipcode) {
	        Member member = new Member();
	        
	        member.username = username;
	        member.address = new Address(city, street, zipcode);
	        
	        return member;
	    }
}
