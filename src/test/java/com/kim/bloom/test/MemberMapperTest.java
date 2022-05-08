package com.kim.bloom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.MemberMapper;
import com.kim.bloom.model.MemberVO;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;

	@Test
	public void memberLogin() throws Exception {
		MemberVO member = new MemberVO();
		member.setMemberId("aaaa");
		member.setMemberPw("1111");
		
		memberMapper.memberLogin(member);
		System.out.println("결과 값: "+memberMapper.memberLogin(member));
		
		 
	}
	

}