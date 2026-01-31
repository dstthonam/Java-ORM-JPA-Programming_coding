package com.springboot.jpa_start1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_start1.data.dto.MemberDto;
import com.springboot.jpa_start1.data.entity.Member;
import com.springboot.jpa_start1.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Test
	void saveMember() {
	    MemberDto dto = new MemberDto();
	    
	    dto.setUsername("insoung");
	    dto.setAge(26);

	    Member member = memberService.saveMember(dto);

	    assertThat(member.getId()).isNotNull();
	    
	    System.out.println("=====================");
	    
	    System.out.println("saveMember");
	    System.out.println("member getId: " + member.getId());
	    System.out.println("member getUsername: " + member.getUsername());
	    System.out.println("member getAge: " + member.getAge());
	    
	    System.out.println("=====================");
	}

	@Test
	void findMember() {
	    Member saved = memberService.saveMember(new MemberDto("Tho", 18));

	    Member find = memberService.findMemberById(saved.getId());

	    assertThat(find.getUsername()).isEqualTo("Tho");
	    
	    System.out.println("=====================");
	    
	    System.out.println("findMember");
	    System.out.println("member getId: " + find.getId());
	    System.out.println("member getUsername: " + find.getUsername());
	    System.out.println("member getAge: " + find.getAge());
	    
	    System.out.println("=====================");
	}
	
	@Test
	void updateMember() {
	    Member saved = memberService.saveMember(new MemberDto("nam", 25));

	    memberService.updateMember(saved.getId(), new MemberDto(null, 30));

	    Member find = memberService.findMemberById(saved.getId());
	    
	    assertThat(find.getAge()).isEqualTo(30);

	    System.out.println("=====================");
	    
	    System.out.println("updateMember");
	    System.out.println("member getId: " + find.getId());
	    System.out.println("member getUsername: " + find.getUsername());
	    System.out.println("member getAge: " + find.getAge());

	    System.out.println("=====================");
	}
	
	@Test
	void transactionRollback() {
	    assertThatThrownBy(() -> { // 예외가 발생하면 test 통과
	        memberService.saveMember(new MemberDto("fail", 10));
	        throw new RuntimeException();
	        
	    });
	}
	
}
