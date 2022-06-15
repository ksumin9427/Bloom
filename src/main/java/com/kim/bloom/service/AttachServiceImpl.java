package com.kim.bloom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.AttachMapper;
import com.kim.bloom.model.AttachImageVO;

@Service("AttachService")
public class AttachServiceImpl implements AttachService{
	
	private static final Logger log = LoggerFactory.getLogger(AttachServiceImpl.class);
	
	@Autowired
	private AttachMapper attachMapper;
	
	/* 이미지를 데이터로 변환 */
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		log.info("getAttachList............");
		
		return attachMapper.getAttachList(bookId);
	}

}
