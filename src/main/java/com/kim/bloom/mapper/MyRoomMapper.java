package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.MemberVO;

public interface MyRoomMapper {
	
	public List<CateVO> getCateCode1();
	
	public List<CateVO> getCateCode2();
	
	public MemberVO getMyRoomInfo(String memberId);
	
	public int myRoomInfoModify(MemberVO vo);
	
	public int memberUpdate(MemberVO vo) throws Exception;
	
	public int memberDelete(MemberVO vo) throws Exception;
}
