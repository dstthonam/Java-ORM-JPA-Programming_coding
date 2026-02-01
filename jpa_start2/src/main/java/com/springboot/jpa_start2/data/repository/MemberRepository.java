package com.springboot.jpa_start2.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa_start2.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}
