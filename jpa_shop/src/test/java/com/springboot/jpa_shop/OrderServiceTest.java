package com.springboot.jpa_shop;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa_shop.data.entity.Member;
import com.springboot.jpa_shop.data.entity.Order;
import com.springboot.jpa_shop.data.entity.OrderStatus;
import com.springboot.jpa_shop.data.entity.item.Book;
import com.springboot.jpa_shop.data.entity.item.Item;
import com.springboot.jpa_shop.data.repository.ItemRepository;
import com.springboot.jpa_shop.data.repository.MemberRepository;
import com.springboot.jpa_shop.data.repository.OrderRepository;
//import com.springboot.jpa_shop.exception.NotEnoughStockException;
import com.springboot.jpa_shop.service.OrderService;

@SpringBootTest
@Transactional
public class OrderServiceTest {
	
	    @Autowired 
	    public OrderService orderService;
	    @Autowired 
	    public OrderRepository orderRepository;
	    @Autowired 
	    public MemberRepository memberRepository;
	    @Autowired 
	    public ItemRepository itemRepository;
	
	    //private EntityManager em;
	    
	    @Test
	    public void OrderProduct_test() throws Exception {
	
	        //Given
	        Member member = createdMember();
	        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
	        int orderCount = 2;

            LocalDate today = LocalDate.now(); // 오늘 날짜 구하기
            Date orderDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        		
	        //When
	        Long orderId = orderService.order(member.getId(), item.getId(), orderCount, orderDate);
	
	        //Then
	        Order getOrder = orderRepository.findById(orderId)
													.orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));;
	
	        assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus(), "상품 주문시 상태는 주문(ORDER)이다.");
	        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
	        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
	        assertEquals(8, item.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
	    }
	
	    @Test
	    public void OrderProduct_OrderThanStock_test() throws Exception {
	
	        //Given
	        Member member = createdMember();
	        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
	        int orderCount = 11; //재고 보다 많은 수량

            LocalDate today = LocalDate.now(); // 오늘 날짜 구하기
            Date orderDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        		
	        //When
	        orderService.order(member.getId(), item.getId(), orderCount, orderDate);
	
	        //Then
	        fail("재고 수량 부족 예외가 발생해야 한다.");
	    }
	
	    @Test
	    public void OrderCancel() {
	
	        //Given
	        Member member = createdMember();
	        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
	        int orderCount = 2;

            LocalDate today = LocalDate.now(); // 오늘 날짜 구하기
            Date orderDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        
	        //When
	        Long orderId = orderService.order(member.getId(), item.getId(), orderCount, orderDate);
	        orderService.cancelOrder(orderId);
	        
	        //Then
	        Order getOrder = orderRepository.findById(orderId)
														.orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));
	
	        assertEquals(OrderStatus.CANCEL, getOrder.getOrderStatus(), "주문 취소시 상태는 CANCEL 이다.");
	        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
	    }
	
	    private Member createdMember() {
	        Member member = new Member().changeMember("회원1", "서울", "강가", "123-123");
	        
	        memberRepository.save(member);
	        
	        return member;
	    }
	
	    private Book createBook(String name, int price, int stockQuantity) {
	        Book book = new Book(name, price, stockQuantity, name, name);
	        
	        itemRepository.save(book);
	        
	        return book;
	    }
}
