package com.springboot.jpa_start2.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="MEMBER")
@Getter
@NoArgsConstructor
@Table(name="MEMBER",
				uniqueConstraints = {@UniqueConstraint(
						name = "NAME_AGE_UNIQUE",
						columnNames = {"MEMBER_NAME", "MEMBER_AGE"} )
				})
public class Member {
	
	    @Id
	    @Column(name = "member_id")
	    @SequenceGenerator(sequenceName =  "member_seq", 	allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")  // 쓰기 지연 학습에 사용
		//@GeneratedValue(strategy = GenerationType.IDENTITY) // persist 시점에 바로 insert 발생
	    private Integer id;

	    @Column(name = "member_name", nullable = false, length = 10)
	    private String username;
	
	    @Column(name = "member_age")
	    private Integer age;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "role_type")
	    private RoleType roleType;
	
	    @Column(name = "created_date")
	    private Date createdDate;
	    
	    @Column(name = "last_modified_date")
	    private Date lastModifiedDate;
	
	    @Lob
	    @Column(name = "description")
	    private String description;
	
	    @Transient
	    private String temp;
	
	    // 생성 메서드
	    public Member(String username, Integer age) {
	        this.username = username;
	        this.age = age;
	    }
	
	    // 변경 메서드 (dirty checking 의도 표현)
	    public void changeAge(Integer age) {
	        this.age = age;
	    }

}