package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.MemberVO;

public interface MyRoomService {
	
	public List<CateVO> getCateCode1() throws Exception;
	
	public List<CateVO> getCateCode2() throws Exception;
	
	public MemberVO getMyRoomInfo(String memberId) throws Exception;
	
	public int memberUpdate(MemberVO vo) throws Exception;
	
	public int memberDelete(MemberVO vo) throws Exception;
}
