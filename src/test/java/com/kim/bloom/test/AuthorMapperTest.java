package com.kim.bloom.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.AuthorMapper;
import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	
	@Autowired
    private AuthorMapper mapper;
    
    @Test
    public void authorGetListTest() throws Exception{
        Criteria cri = new Criteria(3, 10);
        
        cri.setKeyword("김영하");
        List<AuthorVO> list = mapper.authorGetList(cri);
        
        for (int i = 0; i < list.size() ; i++) {
			System.out.println("list"+i+"...."+list.get(i));
			
		}
    }
}
