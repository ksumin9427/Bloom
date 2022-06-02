package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;

public interface AdminMapper {
	
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 상품 조회 페이지 */
	public BookVO goodsGetDetail(int bookId);
}
