package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.MemberVO;
import com.kim.bloom.model.OrderDTO;

public interface AdminService {
	
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 상품 조회 페이지 */
	public BookVO goodsGetDetail(int bookId);
	
	/* 상품 수정 */
	public int goodsModify(BookVO vo);
	
	/* 상품 삭제 */
	public int goodsDelete(int bookId);
	
	/* 지정 상품 이미지 정보 */
	public List<AttachImageVO> getAttachInfo(int bookId);
	
	/* 주문 상품 리스트 */
	public List<OrderDTO> getOrderList(Criteria cri);
	
	/* 주문 총 갯수 */
	public int getOrderTotal(Criteria cri);
	
	/* 회원 목록 */
	public List<MemberVO> memberGetList(Criteria cri) throws Exception;
	
	/* 회원 수 */
	public int memberGetTotal(Criteria cri) throws Exception;;
}
