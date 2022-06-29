package com.kim.bloom.mapper;

import com.kim.bloom.model.OrderPageItemDTO;

public interface OrderMapper {
	
	/* 주문 상품 정보 */
	public OrderPageItemDTO getGoodsInfo(int bookId);
}
