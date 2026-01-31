package com.springboot.jpa_start1.data.dto;

import com.springboot.jpa_start1.data.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
	
		private Integer id;
		private String username;
		private Integer age;

	    public static MemberResponseDto from(Member member) {
	    	
	        return new MemberResponseDto(
	            member.getId(),
	            member.getUsername(),
	            member.getAge()
	        );
	    }
	    
}
