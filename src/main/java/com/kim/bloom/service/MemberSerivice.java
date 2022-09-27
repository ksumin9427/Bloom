package com.kim.bloom.service;

import com.kim.bloom.model.MemberVO;

public interface MemberSerivice {

	public void memberJoin(MemberVO member) throws Exception;
	
	public int idCheck(String memberId) throws Exception;
	
	public MemberVO memberLogin(MemberVO member) throws Exception ;
	
	/* 주문자 정보 */
	public MemberVO getMemberInfo(String memberId);
	
	
}
