package com.kim.bloom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.DesignerMapper;
import com.kim.bloom.model.DesignerVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DesignerMapperTest {
	
	@Autowired
	private DesignerMapper mapper;
	
	@Test
	public void DesignerEnroll() throws Exception{
		DesignerVO designer = new DesignerVO();
		
		designer.setDesignerName("정윤지");
		
		mapper.DesignerEnroll(designer);
	}
}
