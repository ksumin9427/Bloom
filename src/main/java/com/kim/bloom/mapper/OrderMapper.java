package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.MemberVO;
import com.kim.bloom.model.OrderDTO;
import com.kim.bloom.model.OrderItemDTO;
import com.kim.bloom.model.OrderPageItemDTO;

public interface OrderMapper {
	
	/* 주문 상품 정보(주문페이지) */
	public OrderPageItemDTO getGoodsInfo(int bookId);
	
	/* 상품의 아이디, 가격, 할인율을 가져온다,  주문 상품 정보(주문 처리) */
	public OrderItemDTO getOrderInfo(int bookId);
	
	/* bloom_order 주문 테이블에 데이터를 등록 */
	public int enrollOrder(OrderDTO ord);
	
	/* bloom_orderItem 주문 아이템 테이블에 데이터를 등록 */
	public int enrollOrderItem(OrderItemDTO orid);
	
	/* bloom_member 테이블에서 회원의 돈 포인트를 변경 */
	public int updateMoney(MemberVO member);
	
	/* 주문한 상품 개수 만큼 재고를 차감 */
	public int updateStock(BookVO book);
	
	/* 주문 취소 */
	public int orderCancle(String orderId);
	
	/* 주문 상품 정보의 주문 취소 */
	public List<OrderItemDTO> getOrderItemInfo(String orderId);
	
	/* 주문 정보의 주문 취소 */
	public OrderDTO getOrder(String orderId);
	
	
	
}
