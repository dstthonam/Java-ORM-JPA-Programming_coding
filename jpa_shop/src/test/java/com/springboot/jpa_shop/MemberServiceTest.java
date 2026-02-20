package com.springboot.jpa_shop;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.repository.MemberRepository;
import com.springboot.jpa_shop.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {

	    @Autowired MemberService memberService;
	    @Autowired MemberRepository memberRepository;

	    @Test
	    public void register_test() throws Exception {
	        try {
	    	    //Given
		        Member member = Member.changeMember("kim", "city", "street", "zipcode");
		
		        //When
		        Long saveMemberSevice = memberService.join(member);

		        //Then
		        assertEquals(member, memberRepository.findOne(saveMemberSevice));
			} catch (Exception e) {
				e.printStackTrace(); // error log check
			}
	
	    }
	
	    @Test
	    public void except_duplicateRegister_test() throws Exception {
	    	try {
				//Given
		        Member member1 =  Member.changeMember("kim", "city", "street", "zipcode");
		
		        Member member2 =  Member.changeMember("kim", "city", "street", "zipcode");
		
		        //When
		        memberService.join(member1);
	            //memberService.join(member2);
		        
		        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
		            memberService.join(member2); // occur exception // 26.02.20 람다식 내부의 파라미터 값은 final 상태, 외부에서 재선언 금지  
		        });
		        
		        //Then
		        System.out.println("except_duplicateRegister_test : " + thrown);
		        //fail("예외가 발생해야 한다.");
				
			} catch (Exception e) {
				e.printStackTrace(); // error log check
			}
	    }

}