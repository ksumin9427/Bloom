package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.ReplyDTO;

public interface ReplyMapper {
	
	/* 댓글 등록 */
	public int enrollReply(ReplyDTO dto);
	
	/* 동일 상품 댓글 존재 체크 */
	public Integer checkReply(ReplyDTO dto);
	
	/* 페이징된 댓글 리스트 정보 */
	public List<ReplyDTO> getReplyList(Criteria cri);
	
	/* 댓글 총 개수 */
	public int getReplyTotal(int bookId) ;
}
