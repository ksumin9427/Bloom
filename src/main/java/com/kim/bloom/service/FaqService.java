package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.FaqVO;

public interface FaqService {

	/* faq 게시글 등록 */
	public void faqEnroll(FaqVO faq) throws Exception;

	/*
	 * faq 게시글 목록 public List<FaqVO> getFaqList() throws Exception;
	 */

	/* faq 게시글 목록 */
	public List<FaqVO> getFaqList(Criteria cri) throws Exception;

	/* faq 게시글 조회 */
	public FaqVO getFaqPage(int fno) throws Exception;

	/* faq 게시글 수정 */
	public int faqModify(FaqVO faq) throws Exception;

	/* faq 게시글 삭제 */
	public int faqDelete(int fno) throws Exception;

	/* faq 게시판 목록(페이징 적용) */
	public List<FaqVO> getListPaging(Criteria cri) throws Exception;

	/*
	 * faq 게시물 총 개수 public int getTotal() throws Exception;
	 */

	/* faq 게시물 총 개수 */
	public int getTotal(Criteria cri) throws Exception;

	/* faq 카테고리 리스트 */
	public List<CateVO> cateList() throws Exception;

	/* faq 검색 */
	public List<FaqVO> getFaqSearch(Criteria cri) throws Exception;

	/* faq 검색 게시물 총 개수 */
	public int getFaqSearchTotal(Criteria cri) throws Exception;

}
