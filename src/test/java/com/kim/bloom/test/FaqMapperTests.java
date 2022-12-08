package com.kim.bloom.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.FaqMapper;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.FaqVO;

import lombok.extern.log4j.Log4j;

@Log4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqMapperTests {
	
	@Autowired
	public FaqMapper mapper;
	
	@Test 
	public void test() throws Exception {
		
		
            String keyword = "주문";
    		
    		String[] list = mapper.getFaqFnoList(keyword);
    		
    		System.out.println("결과 : " + list.toString());
    		
    		for(String id : list) {
    			System.out.println("개별 결과 : " + id);
    		}
	}
}
