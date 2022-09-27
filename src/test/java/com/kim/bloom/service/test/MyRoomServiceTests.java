package com.kim.bloom.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.model.MemberVO;
import com.kim.bloom.service.MyRoomService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MyRoomServiceTests {
	
	@Autowired
	private MyRoomService service;
	
	@Test
	public void myRoomInfo() throws Exception {
		String id = "dan";
		MemberVO vo = service.getMyRoomInfo(id);
		System.out.println(vo);
	}
}
