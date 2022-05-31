package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;

public interface AdminMapper {
	
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
}
