package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.CateFilterDTO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.FaqVO;

public interface FaqMapper {

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
	public List<CateVO> cateList();

	/* faq 검색 */
	public List<FaqVO> getFaqSearch(Criteria cri) throws Exception;

	/* faq 게시물 총 개수 */
	public int getFaqSearchTotal(Criteria cri) throws Exception;

	/* 검색 대상 카테고리 리스트 */
	public String[] getFaqCateList(Criteria cri) throws Exception;

	/* 카테고리 정보(+검색대상 갯수) */
	public CateFilterDTO getFaqCateInfo(Criteria cri) throws Exception;

	/* 검색 대상 카테코드 리스트 */
	public String[] getFaqCatecodeList(String keyword) throws Exception;

	/* 검색 대상 카테코드 리스트 */
	public String[] getFaqFnoList(String keyword) throws Exception;

}
