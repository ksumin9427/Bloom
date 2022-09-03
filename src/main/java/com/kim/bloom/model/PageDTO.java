package com.kim.bloom.model;

public class PageDTO {
	/* 페이지 이동 인터페이스를 위해 */
	
	/* 시작 페이지 번호*/
	private int pageStart; 
	
	/* 끝 페이지 번호*/
	private int pageEnd; 
	
	/* 이전 페이지, 다음 페이지 존재유무 */
    private boolean prev, next;
    
    /*전체 게시물 수*/
    private int total;
    
    /* 현재 페이지의 정보(번호, 행의 수, 검색 키워드, 검색 종류) */
    private Criteria cri;
	
	public PageDTO(Criteria cri, int total) { /* 클래스를 호출 할 때 각 변수의 값을 초기화 한다 */
		
		this.cri = cri;
		this.total = total;
		
		/* 10은 클릭할 수 있는 페이지 개수이다(DisplayPagenum), [1] - [10] 설정
		   pageEnd = (int) (Math.ceil (cri.getPage()/ (double) displayPageNum) * displayPageNum);
		   pageStart = (endPage - displayPageNum)+1; 
		 */
		this.pageEnd = (int) (Math.ceil(cri.getPageNum()/10.0)) * 10;
		
		this.pageStart = this.pageEnd - 9;
		
		
		/* 전체 마지막 페이지, total * 1.0는 double로 만들기 위해서 */
		int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));
		
		if (realEnd < pageEnd) {
			this.pageEnd = realEnd;
		}
		
		this.prev = this.pageStart > 1;
		
		this.next = this.pageEnd < realEnd;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	@Override
	public String toString() {
		
		return "PageDTO [pageStart=" + pageStart + ", pageEnd=" + pageEnd + ", next=" + next + ", prev=" + prev
				+ ", total=" + total + ", cri=" + cri + "]";
	}
}
