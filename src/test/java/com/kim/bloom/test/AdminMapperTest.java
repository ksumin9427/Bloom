package com.kim.bloom.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {
	
	@Autowired
	private AdminMapper mapper;
	
	@Test
	public void getAttachInfoTest() {
		int bookId = 16391;
		List<AttachImageVO> list = mapper.getAttachInfo(bookId);
		System.out.println("list : "+list);
	}
	
	/*
	 * @Test public void checkImageListTest() { mapper.checkFileList(); }
	 */
	
	
	/*
	 * @Test public void imageEnrollTest() { AttachImageVO vo = new AttachImageVO();
	 * 
	 * vo.setBookId(16372); vo.setFileName("test"); vo.setUploadPath("test");
	 * vo.setUuid("test2");
	 * 
	 * mapper.imageEnroll(vo); }
	 */
	
	/*
	 * @Test public void bookEnrollTest() throws Exception{
	 * 
	 * BookVO book = new BookVO();
	 * 
	 * book.setBookName("테스트"); book.setAuthorId(759);
	 * book.setPublicYear("2022-06-14"); book.setPublisher("출판사");
	 * book.setCateCode("101001"); book.setBookPrice(20000); book.setBookStock(300);
	 * book.setBookDiscount(0.23); book.setBookIntro("책 소개 ");
	 * book.setBookContents("책 목차 "); System.out.println("Before BookVO: "+book);
	 * mapper.bookEnroll(book); System.out.println("After BookVO: "+book); }
	 */
	
	
	/*
	 * @Test public void goodsModifyTest() {
	 * 
	 * BookVO book = new BookVO();
	 * 
	 * book.setBookId(15346); book.setBookName("mapper 테스트"); book.setAuthorId(4);
	 * book.setPublicYear("2021-03-18"); book.setPublisher("출판사");
	 * book.setCateCode("103002"); book.setBookPrice(20000); book.setBookStock(300);
	 * book.setBookDiscount(0.23); book.setBookIntro("책 소개 ");
	 * book.setBookContents("책 목차 ");
	 * 
	 * mapper.goodsModify(book);
	 * 
	 * }
	 */
	

	
	
}
