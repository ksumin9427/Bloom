package com.kim.bloom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.BookVO;

public class AdminServiceImpl implements AdminService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(service)bookEnroll..........");
		
		adminMapper.bookEnroll(book);
	}

}
