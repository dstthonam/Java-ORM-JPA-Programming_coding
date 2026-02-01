package com.springboot.jpa_start2.service;

import java.util.List;

import com.springboot.jpa_start2.data.dto.MemberDto;
import com.springboot.jpa_start2.data.entity.Member;

public interface MemberService {

		List<Member> findAllMembers();
		
		Member findMemberById(Integer id);
		
		Member saveMember(MemberDto dto);
		
		Member updateMember(Integer id, MemberDto dto);
		
		void deleteMember(Integer id);
	
}
