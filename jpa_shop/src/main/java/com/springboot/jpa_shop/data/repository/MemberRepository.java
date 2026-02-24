package com.springboot.jpa_shop.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa_shop.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);
}

