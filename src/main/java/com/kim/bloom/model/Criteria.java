package com.kim.bloom.model;

import java.util.Arrays;

public class Criteria { 
	/* 지정한 개수와 검색조건에 따라서 작가 데이터를 출력하는 쿼리를 실행하는데 필요로 한 데이터들의 모임 */
	/* 페이징 쿼리를 동적 제어하기 위해 필요한 데이터 'pageNum'과 'amount'을 같이 파라미터로 전달하기 위한 용도 */
	
	private int pageNum; /* 현재 페이지 번호 */
	
	private int amount; /* 한 페이지 당 보여줄 게시물 수  */
	
	private int skip; /* 스킵할 게시물 수  (pageNum-1) * amount ) */
	
	private String type; /* 검색 카테고리 */
	
	private String keyword; /* 검색 키워드 */
	
	private String[] authorArr; /* 작가 리스트 */
	
	private String[] fnoCateCodeArr; /* 글번호 카테코드 리스트 */
	
	private String cateCode; /* 카테고리 코드 */
	
	private String cateName; 
	
	private int bookId; 
	
	public String[] getTypeArr() { /* 타입을 하나의 문자열로 저장하고, 사용할 때에는 각각을 나눠 배열로 가져온다. */
		return type == null? new String[] {}:type.split("");
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public Criteria() {
		this(1,10);
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getCateName() {
		return cateName;
	}
	
	public void setCateName(String cateName) {
		this.cateName = cateName;
		
	}
	
	public String[] getAuthorArr() {
		return authorArr;
	}

	public void setAuthorArr(String[] authorArr) {
		this.authorArr = authorArr;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) { /* amout와 pageNum 변수의 값이 변경될때 skip변수의 값도 변경되도록 setAmout(), setPageNum() 메서드를 일부 수정*/
		this.pageNum = pageNum;
		this.skip = (this.pageNum - 1) * amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		this.skip = (this.pageNum - 1) * amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String[] getFnoCateCodeArr() {
		return fnoCateCodeArr;
	}

	public void setFnoCateCodeArr(String[] fnoCateCodeArr) {
		this.fnoCateCodeArr = fnoCateCodeArr;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + ", type=" + type
				+ ", keyword=" + keyword + ", authorArr=" + Arrays.toString(authorArr) + ", fnoCateCodeArr="
				+ Arrays.toString(fnoCateCodeArr) + ", cateCode=" + cateCode + ", cateName=" + cateName + ", bookId="
				+ bookId + "]";
	}
	
	
	
}
