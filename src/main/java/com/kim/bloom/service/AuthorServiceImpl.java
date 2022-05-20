package com.kim.bloom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.controller.AdminController;
import com.kim.bloom.mapper.AuthorMapper;
import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;

@Service("AuthorService")
public class AuthorServiceImpl implements AuthorService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	AuthorMapper authorMapper;

	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		authorMapper.authorEnroll(author);
	}

	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		
		log.info("(service)authorGetList()......... "+cri);
		return authorMapper.authorGetList(cri);
		
	}

	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		log.info("(service)authorGetTotal()......... "+cri);
		
		return authorMapper.authorGetTotal(cri);
	}

	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		
		return null;
	}
	
	
}
