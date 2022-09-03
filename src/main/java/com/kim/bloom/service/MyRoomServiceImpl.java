package com.kim.bloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.MyRoomMapper;
import com.kim.bloom.model.CateVO;

@Service
public class MyRoomServiceImpl implements MyRoomService {
	
	@Autowired
	private MyRoomMapper myRoomMapper;

	@Override
	public List<CateVO> getCateCode1() {
		return myRoomMapper.getCateCode1();
	}

	@Override
	public List<CateVO> getCateCode2() {
		return myRoomMapper.getCateCode2();
	}
	
}
