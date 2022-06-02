package com.kim.bloom.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.Criteria;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {
	
	@Autowired
	private AdminMapper mapper;
	
	
	/*
	 * @Test public void goodsGetDetail() { int bookId = 8190; BookVO result =
	 * mapper.goodsGetDetail(bookId); System.out.println("상품 조회 데이터 : "+result);
	 * 
	 * }
	 */
	
	/*
	 * @Test public void goodsGetListTest() { Criteria cri = new Criteria();
	 * 
	 * cri.setKeyword("인간실격");
	 * 
	 * List list = mapper.goodsGetList(cri); for (int i = 0; i < list.size(); i++) {
	 * System.out.println("result........."+i+" : "+list.get(i)); }
	 * 
	 * int result = mapper.goodsGetTotal(cri);
	 * System.out.println("result........."+result);
	 * 
	 * }
	 */
	

	
	
}
