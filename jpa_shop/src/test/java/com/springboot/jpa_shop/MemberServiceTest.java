package com.springboot.jpa_shop;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.repository.MemberRepository;
import com.springboot.jpa_shop.service.MemberService;

@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class MemberServiceTest {

	    @Autowired MemberService memberService;
	    @Autowired MemberRepository memberRepository;

	    @Test
	    public void register_test() throws Exception {
		    //Given
	        Member member = new Member();
	        member.changeMember("kim", null, null, null);
	
	        //When
	        Long saveMemberSevice = memberService.join(member);
	
	        //Then
	        assertEquals(member, memberRepository.findOne(saveMemberSevice));
	    }
	
	    @Test
	    public void except_duplicateRegister_test() throws Exception {
	        //Given
	        Member member1 = new Member();
	        member1.changeMember("kim", null, null, null);
	
	        Member member2 = new Member();
	        member2.changeMember("kim", null, null, null);
	
	        //When
	        memberService.join(member1);
	        
	        Assertions.assertThrows(IllegalStateException.class, () -> {
	            memberService.join(member2); //예외가 발생해야 한다.
	        });
	        
	        //Then
	        fail("예외가 발생해야 한다.");
	    }

}