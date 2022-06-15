package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.AttachImageVO;

public interface AttachMapper {
	
	/* 이미지 데이터를 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
	
}
