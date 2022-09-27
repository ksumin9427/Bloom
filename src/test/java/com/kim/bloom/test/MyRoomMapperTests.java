package com.kim.bloom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.MyRoomMapper;
import com.kim.bloom.model.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MyRoomMapperTests {
	
	@Autowired
	private MyRoomMapper mapper;
	
	@Test
	public void test() {
		MemberVO vo = new MemberVO();
		
		vo.setMemberId("jun");
		
		
		vo.setMemberPw("1111");
		
		
		
		
		
	}
}
