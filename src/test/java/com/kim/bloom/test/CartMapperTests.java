package com.kim.bloom.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.CartMapper;
import com.kim.bloom.model.CartDTO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartMapperTests {
	
	@Autowired
	private CartMapper mapper;
	
	/* 장바구니 제거(주문 처리) */
	@Test
	public void deleteOrderCart() {
		String memberId = "admin";
		int bookId = 17407;
		
		CartDTO dto = new CartDTO();
		dto.setMemberId(memberId);
		dto.setBookId(bookId);
		
		mapper.deleteOrderCart(dto);
		
	}
		
	

	

	
	
}
