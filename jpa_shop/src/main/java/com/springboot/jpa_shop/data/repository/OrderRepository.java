package com.springboot.jpa_shop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.repository.custom.OrderRepositoryCustom;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

}