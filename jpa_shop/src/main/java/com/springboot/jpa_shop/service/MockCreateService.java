//package com.springboot.jpa_shop.service;
//
//import com.springboot.jpa_shop.data.entity.Address;
//import com.springboot.jpa_shop.data.entity.Member;
//import com.springboot.jpa_shop.data.entity.item.Book;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class MockCreateService {
//
//    @Autowired MemberService memberService;
//    @Autowired ItemService itemService;
//    @Autowired OrderService orderService;
//
//    @PostConstruct
//    public void initCreateMock() {
//
//        Member member = new Member();
//        member.UserName("회원1");
//        member.setAddress(new Address("서울", "강가", "123-123"));
//
//        memberService.join(member);
//
//        Book book = createBook("시골개발자의 JPA 책", 20000, 10);
//        itemService.saveItem(book);
//        itemService.saveItem(createBook("토비의 봄", 40000, 20));
//
//        orderService.order(member.getId(), book.getId(), 5);
//
//    }
//
//    private Book createBook(String name, int price, int stockQuantity) {
//        Book book = new Book();
//        book.setName(name);
//        book.setPrice(price);
//        book.setStockQuantity(stockQuantity);
//        return book;
//    }
//
//}
