package com.springboot.jpa_start2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_start2.data.dto.MemberDto;
import com.springboot.jpa_start2.data.entity.Member;
import com.springboot.jpa_start2.data.repository.MemberRepository;
import com.springboot.jpa_start2.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

		private final MemberRepository memberRepository;
		
		@Override
		@Transactional(readOnly = true)
		public List<Member> findAllMembers() {
			List<Member> members = memberRepository.findAll();

			return members;
		}

		@Override
		@Transactional(readOnly = true)
		public Member findMemberById(Integer id) {
			
			return memberRepository.findById(id)
								.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다. id = " + id));
		}
		
		@Override
		public	Member saveMember(MemberDto dto) {
			Member member = new Member(dto.getUsername(), dto.getAge());
			
			return memberRepository.save(member);
		}
		
		@Override
		public Member  updateMember(Integer id, MemberDto dto) {
			Member member = findMemberById(id);
			
			// Dirty Checking
			member.changeAge(dto.getAge());
			
			return member;
		}

		@Override
		public void deleteMember(Integer id) {
		    Member member = findMemberById(id);

		    memberRepository.delete(member);
		}
		
}
