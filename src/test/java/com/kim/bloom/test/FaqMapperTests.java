package com.kim.bloom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.FaqMapper;
import com.kim.bloom.model.FaqVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqMapperTests {
	
	@Autowired
	public FaqMapper mapper;
	
	@Test
	public void faqEnroll() {
		FaqVO vo = new FaqVO();
		
	}
}
