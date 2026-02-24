package com.springboot.jpa_shop.data.repository.custom;

import java.util.List;

import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.service.OrderSearch;

public interface OrderRepositoryCustom {
    	List<Order> findAll(OrderSearch search);

}
