package com.springboot.jpa_shop.service;

import com.springboot.jpa_shop.data.entity.OrderStatus;

import lombok.Getter;

@Getter
public class OrderSearch {

	    private String memberName;      //회원 이름
	    private OrderStatus orderStatus;//주문 상태
	
	    public void serachOrderSearch(String memberName, OrderStatus orderStatus) {
	        this.memberName = memberName;
	        this.orderStatus = orderStatus;
	    }
		    
}
