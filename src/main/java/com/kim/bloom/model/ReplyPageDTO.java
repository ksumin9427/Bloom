package com.kim.bloom.model;

import java.util.List;

public class ReplyPageDTO {
	
	/* 페이징된 댓글 리스트 정보 */
	List<ReplyDTO> list;
	
	/* 페이지 정보 */
	PageDTO pageInfo;

	public List<ReplyDTO> getList() {
		return list;
	}

	public void setList(List<ReplyDTO> list) {
		this.list = list;
	}

	public PageDTO getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageDTO pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	@Override
	public String toString() {
		return "ReplyPageDTO [list=" + list + ", pageInfo=" + pageInfo + "]";
	}
}
