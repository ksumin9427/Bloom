package com.kim.bloom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.ReplyDTO;
import com.kim.bloom.model.ReplyPageDTO;
import com.kim.bloom.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	/* 댓글 등록 요청 */
	@PostMapping("/enroll")
	public void enrollReplyPost(ReplyDTO dto) {
		replyService.enrollReply(dto);
	}
	
	@PostMapping("/check")
	public String replyCheckPost(ReplyDTO dto) {
		return replyService.checkReply(dto);
	}
	
	/* 댓글 페이징 */
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReplyPageDTO replyListPost(Criteria cri) {
		return replyService.replyList(cri);
	}
	

}
