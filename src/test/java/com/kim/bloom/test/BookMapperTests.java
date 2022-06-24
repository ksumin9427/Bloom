package com.kim.bloom.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.BookMapper;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.Criteria;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTests {
	
	@Autowired
	private BookMapper mapper;
	
	/* 상품 정보 */
	@Test
	public void getGoodsInfo() {
		int bookId = 16391;
		BookVO goodsInfo = mapper.getGoodsInfo(bookId);
		System.out.println("===========================");
		System.out.println(goodsInfo);
		System.out.println("===========================");
		
	}
	
	
	
	
}
