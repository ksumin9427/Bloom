package com.kim.bloom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.AttachMapper;
import com.kim.bloom.mapper.CartMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.CartDTO;

@Service("CartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private AttachMapper attachMapper;

	@Override
	public int addCart(CartDTO cart) {
		
		CartDTO checkCart = cartMapper.checkCart(cart);
		
		if (checkCart != null) {/* 0= db 등록 실패, 1= 성공, 2= 중복 */
			return 2;
		}
		
		try {
			return cartMapper.addCart(cart);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<CartDTO> getCartList(String memberId) {
		List<CartDTO> cart = cartMapper.getCart(memberId);
		
		for(CartDTO dto : cart) {
			/* 종합 정보 초기화 */
			dto.initSaleTotal();
			
			int bookId = dto.getBookId();
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			dto.setImageList(imageList);
	
		}
		return cart;
	}

	@Override
	public int modifyCount(CartDTO cart) {
		return cartMapper.modifyCount(cart);
	}

	@Override
	public int deleteCart(int cartId) {
		return cartMapper.deleteCart(cartId);
	}

}
