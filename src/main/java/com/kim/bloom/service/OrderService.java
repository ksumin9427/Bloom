package com.kim.bloom.service;

import java.util.List;

import com.kim.bloom.model.OrderCancleDTO;
import com.kim.bloom.model.OrderDTO;
import com.kim.bloom.model.OrderPageItemDTO;

public interface OrderService {
	
	/* 주문 정보 */
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders);
	
	/* 주문 처리*/
	public void order(OrderDTO orw);
	
	/* 주문 취소*/
	public void orderCancle(OrderCancleDTO dto);
	
	
}
