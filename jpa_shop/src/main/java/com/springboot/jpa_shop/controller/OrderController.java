package com.springboot.jpa_shop.controller;

import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.entity.item.Item;
import com.springboot.jpa_shop.service.OrderSearch;
import com.springboot.jpa_shop.service.ItemService;
import com.springboot.jpa_shop.service.MemberService;
import com.springboot.jpa_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
	    @Autowired OrderService orderService;
	    @Autowired MemberService memberService;
	    @Autowired ItemService itemService;
	
	    @GetMapping("/order")
	    public String createForm(Model model) {
	        List<Member> members = memberService.findMembers();
	        List<Item> items = itemService.findItems();
	
	        model.addAttribute("members", members);
	        model.addAttribute("items", items);
	
	        return "order/orderForm";
	    }
	
	    @PostMapping("/order")
	    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count, Date orderDate) {
	        orderService.order(memberId, itemId, count, orderDate);
	        
	        return "redirect:/orders";
	    }
	
	    @GetMapping("/orders")
	    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
	        List<Order> orders = orderService.findOrders(orderSearch);
	        model.addAttribute("orders", orders);
	
	        return "order/orderList";
	    }
	
	    @GetMapping("/orders/{orderId}/cancel")
	    public String processCancelBuy(@PathVariable Long orderId) {
	        orderService.cancelOrder(orderId);
	
	        return "redirect:/orders";
	    }
}
