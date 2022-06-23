package com.kim.bloom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.AttachMapper;
import com.kim.bloom.mapper.BookMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateFilterDTO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("BookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	/* 상품 검색 */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		log.info("(service)getGoodsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}
		
		List<BookVO> list = bookMapper.getGoodsList(cri);
		
		list.forEach(book -> {
			int bookId = book.getBookId();
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			book.setImageList(imageList);
		});
		
		return list;
	}

	@Override
	public int goodsGetTotal(Criteria cri) {
		log.info("(service)goodsGetTotal().......");
		
		return bookMapper.goodsGetTotal(cri);
	}

	@Override
	public List<CateVO> getCateCode1() {
		log.info("(service)getCateCode1().......");
		
		return bookMapper.getCateCode1();
	}

	@Override
	public List<CateVO> getCateCode2() {
		log.info("(service)getCateCode2().......");
		
		return bookMapper.getCateCode2();
	}

	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {
		log.info("(service)getCateInfoList().......");
		
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
		//String[] cateList = bookMapper.getCateList(cri);
		
		String[] typeArr = cri.getType().split("");
		String[] authorArr;
		
		for(String type : typeArr) {
			if(type.equals("A")) {
				authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
				cri.setAuthorArr(authorArr);
			}
		}
		String[] cateList = bookMapper.getCateList(cri);
		String tempCateCode = cri.getCateCode();
		
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = bookMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		cri.setCateCode(tempCateCode);
		
		return null;
	}

}
