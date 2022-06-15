package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.AttachImageVO;

public interface AttachService {
	
	/* 이미지 데이터를 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
}
