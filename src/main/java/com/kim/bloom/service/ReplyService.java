package com.kim.bloom.service;

import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.ReplyDTO;
import com.kim.bloom.model.ReplyPageDTO;

public interface ReplyService {
	
	/* 댓글 등록 */
	public int enrollReply(ReplyDTO dto);
	
	/* 동일 상품 댓글 존재 체크 */
	public String checkReply(ReplyDTO dto);
	
	/* 댓글 페이징 */
	public ReplyPageDTO replyList(Criteria cti);
	
	/* 댓글 수정 */
	public int updateReply(ReplyDTO dto);
	
	/* 댓글 한 개 정보(수정할 때) */
	public ReplyDTO getUpdateReply(int replyId);
	
	/* 댓글 삭제 */
	public int deleteReply(ReplyDTO dto);
	
}
