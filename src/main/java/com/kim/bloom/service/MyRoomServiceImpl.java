package com.kim.bloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.MyRoomMapper;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.MemberVO;

@Service("MyRoomService")
public class MyRoomServiceImpl implements MyRoomService {
	
	/*
	 * @Inject MemberDAO dao;
	 */
	
	@Autowired
	private MyRoomMapper myRoomMapper;

	@Override
	public List<CateVO> getCateCode1() throws Exception{
		return myRoomMapper.getCateCode1();
	}

	@Override
	public List<CateVO> getCateCode2() throws Exception{
		return myRoomMapper.getCateCode2();
	}

	@Override
	public MemberVO getMyRoomInfo(String memberId) throws Exception {
		return myRoomMapper.getMyRoomInfo(memberId);
	}

	@Override
	public int memberUpdate(MemberVO vo) throws Exception {
		return myRoomMapper.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) throws Exception {
		return myRoomMapper.memberDelete(vo);
	}

	


	

	

	

	
	

	

	 

	
}
