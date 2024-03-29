package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateFilterDTO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.SelectDTO;

public interface BookService {
	
	/* 상품 검색 */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 국외 카테고리 리스트 */
	public List<CateVO> getCateCode2();
	
	/* 검색결과 카테고리 필터 정보 */
	public List<CateFilterDTO> getCateInfoList(Criteria cri);
	
	/* 상품 정보 */
	public BookVO getGoodsInfo(int bookId);
	
	/* 상품 id를 통한 이름 반환 */
	public BookVO getBookIdName(int bookId);
	
	/* 평점 순 상품 정보 */
	public List<SelectDTO> likeSelect();
}
