package com.kim.bloom.mapper;

import java.util.List;

import com.kim.bloom.model.CartDTO;

public interface CartMapper {
	
	/* 장바구니 추가, int 추가 성공 시 1, 실패시 0을 반환 */
	public int addCart(CartDTO cart) throws Exception;
	
	/* 장바구니 삭제 */
	public int deleteCart(int cardId);
	
	/* 장바구니 수량 조절 */
	public int modifyCount(CartDTO cart);
	
	/* 장바구니 목록 */
	public List<CartDTO> getCart(String memberId);
	
	/* 장바구니 확인 */
	public CartDTO checkCart(CartDTO cart);
	
	/* 주문 후 장바구니 삭제 */
	public int deleteOrderCart(CartDTO dto);
	
}
