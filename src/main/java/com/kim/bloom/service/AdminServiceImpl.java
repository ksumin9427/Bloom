package com.kim.bloom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim.bloom.mapper.AdminMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CateVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.MemberVO;
import com.kim.bloom.model.OrderDTO;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(service)bookEnroll..........");
		
		adminMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		book.getImageList().forEach(attach -> {
			
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
		});
	}

	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList..........");
		
		return adminMapper.cateList();
	}

	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("(service)goodsGetList..........");
		
		return adminMapper.goodsGetList(cri);
	}

	@Override
	public int goodsGetTotal(Criteria cri) {
		log.info("(service)goodsGetTotal..........");
		
		return adminMapper.goodsGetTotal(cri);
	}

	@Override
	public BookVO goodsGetDetail(int bookId) {
		log.info("(service)goodsGetDetail..........");
		return adminMapper.goodsGetDetail(bookId);
	}
	
	/* 두 개 이상의 쿼리를 요청하기 때문에 @Transactional 사용 */ 
	@Transactional
	@Override
	public int goodsModify(BookVO vo) {
		log.info("(service)goodsModify..........");
		
		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			adminMapper.deleteImageAll(vo.getBookId());
			
			vo.getImageList().forEach(attach -> {
				attach.setBookId(vo.getBookId());
				adminMapper.imageEnroll(attach);
			});
		}
		
		return result;
	}

	@Transactional
	@Override
	public int goodsDelete(int bookId) {
		log.info("(service)goodsDelete..........");
		
		adminMapper.deleteImageAll(bookId);
		
		return adminMapper.goodsDelete(bookId);
	}

	@Override
	public List<AttachImageVO> getAttachInfo(int bookId) {
		log.info("(service)getAttachInfo..........");
		
		return adminMapper.getAttachInfo(bookId);
	}

	@Override
	public List<OrderDTO> getOrderList(Criteria cri) {
		return adminMapper.getOrderList(cri);
	}

	@Override
	public int getOrderTotal(Criteria cri) {
		return adminMapper.getOrderTotal(cri);
	}

	@Override
	public List<MemberVO> memberGetList(Criteria cri) throws Exception {
		return adminMapper.memberGetList(cri);
	}

	@Override
	public int memberGetTotal(Criteria cri) throws Exception {
		return adminMapper.memberGetTotal(cri);
	}

}
