package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.ReplyDTO;
import com.kim.bloom.model.UpdateReplyDTO;

public interface ReplyMapper {
	
	/* 댓글 등록 */
	public int enrollReply(ReplyDTO dto);
	
	/* 동일 상품 댓글 존재 체크 */
	public Integer checkReply(ReplyDTO dto);
	
	/* 페이징된 댓글 리스트 정보 */
	public List<ReplyDTO> getReplyList(Criteria cri);
	
	/* 댓글 총 개수 */
	public int getReplyTotal(int bookId) ;
	
	/* 댓글 수정 */
	public int updateReply(ReplyDTO dto);
	
	/* 댓글 한 개 정보(수정할 때) */
	public ReplyDTO getUpdateReply(int replyId);
	
	/* 댓글 삭제 */
	public int deleteReply(int replyId);
	
	/* 평점 평균 구하기 */
	public Double getRatingAverage(int bookId);
	
	/* 평점 평균 반영하기 */
	public int updateRating(UpdateReplyDTO dto);
}
