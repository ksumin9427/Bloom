package com.kim.bloom.mapper;

import com.kim.bloom.model.MemberVO;

public interface MemberMapper {
	
	public void memberJoin(MemberVO member);
	
	public int idCheck(String memberId);
	
	public MemberVO memberLogin(MemberVO member);
	
	public MemberVO getMemberInfo(String memberId);
}
