package com.kim.bloom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(service)bookEnroll..........");
		
		adminMapper.bookEnroll(book);
	}

	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList..........");
		
		return adminMapper.cateList();
	}

	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("(service)goodsGetList..........");
		return adminMapper.goodsGetList(cri);
	}

	@Override
	public int goodsGetTotal(Criteria cri) {
		log.info("(service)goodsGetTotal..........");
		return adminMapper.goodsGetTotal(cri);
	}

	@Override
	public BookVO goodsGetDetail(int bookId) {
		log.info("(service)goodsGetDetail..........");
		return adminMapper.goodsGetDetail(bookId);
	}

}
