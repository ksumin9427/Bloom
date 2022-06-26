package com.kim.bloom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kim.bloom.mapper.CartMapper;
import com.kim.bloom.model.CartDTO;

@Service("CartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

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

}
