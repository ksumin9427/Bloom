package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.Criteria;

public interface BookService {
	
	/* 상품 검색 */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
}
