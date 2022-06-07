package com.kim.bloom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.BookVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {
	
	@Autowired
	private AdminMapper mapper;
	
	
	
	/*
	 * @Test public void goodsDeleteTest() { int bookId = 15346;
	 * 
	 * int result =mapper.goodsDelete(bookId);
	 * 
	 * if(result == 1) { System.out.println("삭제 성공"); } }
	 */
	
	/*
	 * @Test public void goodsModifyTest() {
	 * 
	 * BookVO book = new BookVO();
	 * 
	 * book.setBookId(15346); book.setBookName("mapper 테스트"); book.setAuthorId(4);
	 * book.setPublicYear("2021-03-18"); book.setPublisher("출판사");
	 * book.setCateCode("103002"); book.setBookPrice(20000); book.setBookStock(300);
	 * book.setBookDiscount(0.23); book.setBookIntro("책 소개 ");
	 * book.setBookContents("책 목차 ");
	 * 
	 * mapper.goodsModify(book);
	 * 
	 * }
	 */
	

	
	
}
