package com.kim.bloom.service;

import com.kim.bloom.model.CartDTO;

public interface CartService {
	
	/* 장바구니 추가 */
	public int addCart(CartDTO cart);
	
}
