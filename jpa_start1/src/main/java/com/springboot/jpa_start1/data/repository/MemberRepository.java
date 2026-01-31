package com.springboot.jpa_start1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa_start1.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}
