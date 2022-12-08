package com.kim.bloom.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.PageDTO;
import com.kim.bloom.service.FaqService;

import lombok.extern.log4j.Log4j;

@Log4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqServiceTests {
	
	@Autowired
	public FaqService service;
	
	 @Test
	    public void test() throws Exception {
		 Criteria cri = new Criteria();
		 service.getFaqSearch(cri);
	     log.info("@@@@@@@@@@@@@"+service.getFaqSearch(cri));
	     
	     service.getFaqSearchTotal(cri);
	     log.info("@@@@@@@@@@@@@"+service.getFaqSearchTotal(cri));
	     
	     
		 
		 
	        
	    }
}
