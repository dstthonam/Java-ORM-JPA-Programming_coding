package com.springboot.jpa_start1.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 260129 pis
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name="MEMBER")
public class Member {
	
	    @Id
	    @Column(name = "member_id")
		//@GeneratedValue(strategy = GenerationType.IDENTITY) // persist 시점에 바로 insert 발생
		@GeneratedValue(strategy = GenerationType.SEQUENCE) // 쓰기 지연 학습에 사용
	    private Integer id;
	
	    @Column(name = "member_name")
	    private String username;
	
	    @Column(name = "member_age")
	    private Integer age;
	    
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