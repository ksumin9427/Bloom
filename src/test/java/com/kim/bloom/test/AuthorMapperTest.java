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
	public void authorDeleteTest() {
		int authorId = 3;
		int result = mapper.authorDelete(authorId);
		
		if (result == 1) {
			System.out.println("삭제 성공");
		}
	}
    
	/*
	 * @Test public void authorGetListTest() throws Exception{ Criteria cri = new
	 * Criteria(3, 10);
	 * 
	 * cri.setKeyword("김영하"); List<AuthorVO> list = mapper.authorGetList(cri);
	 * 
	 * for (int i = 0; i < list.size() ; i++) {
	 * System.out.println("list"+i+"...."+list.get(i));
	 * 
	 * } }
	 * 
	 * @Test public void authorModityTest() { AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(3555); author.setAuthorName("수정");
	 * author.setAuthorIntro("수정하였습니다.");
	 * 
	 * mapper.authorModify(author); }
	 */
}
