package com.kim.bloom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.AuthorMapper;
import com.kim.bloom.model.AuthorVO;

@Service("AuthorService")
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorMapper authorMapper;

	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorMapper.authorEnroll(author);
	}
	
	
}
