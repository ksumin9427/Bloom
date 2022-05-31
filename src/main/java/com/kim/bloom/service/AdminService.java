package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;

public interface AdminService {
	
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
}
