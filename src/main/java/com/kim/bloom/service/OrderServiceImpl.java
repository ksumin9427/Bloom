package com.kim.bloom.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim.bloom.mapper.AttachMapper;
import com.kim.bloom.mapper.BookMapper;
import com.kim.bloom.mapper.CartMapper;
import com.kim.bloom.mapper.MemberMapper;
import com.kim.bloom.mapper.OrderMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.CartDTO;
import com.kim.bloom.model.MemberVO;
import com.kim.bloom.model.OrderCancleDTO;
import com.kim.bloom.model.OrderDTO;
import com.kim.bloom.model.OrderItemDTO;
import com.kim.bloom.model.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private BookMapper bookMapper;

	@Override
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders) {
		
		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();
		
		for(OrderPageItemDTO ord : orders) {
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getBookId());
			goodsInfo.setBookCount(ord.getBookCount());
			
			goodsInfo.initSaleTotal();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getBookId());
			goodsInfo.setImageList(imageList);
			
			result.add(goodsInfo);
		}
		
		return result;
	}

	@Override
	@Transactional
	public void order(OrderDTO ord) {
		/* 회원 정보 */
		MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
		
		/* 주문 정보 */
		List<OrderItemDTO> ords = new ArrayList<OrderItemDTO>();
		for(OrderItemDTO oit : ord.getOrders()) {
			OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getBookId());
			orderItem.setBookCount(oit.getBookCount());
			orderItem.initSaleTotal();
			ords.add(orderItem);
		}
		/* OrderDTO 세팅 */
		ord.setOrders(ords);
		ord.getOrderPriceInfo();
		
		
		/* OrderDTO 객체의 orderId에 저장 */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberId()+format.format(date);
		ord.setOrderId(orderId);
		
		/* DB에 넣기 */
		orderMapper.enrollOrder(ord);
		for(OrderItemDTO oit : ord.getOrders()) {
			oit.setOrderId(orderId);
			orderMapper.enrollOrderItem(oit);
		}
		
		/* 비용 차감, 변동 money Member 객체 적용 */
		int calMoney = member.getMoney();
		calMoney -= ord.getOrderFinalSalePrice();
		member.setMoney(calMoney);
		
		/* 포인트 차감, 증가 , 변동 point Member 객체 적용 */
		int calPoint= member.getPoint();
		calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();
		member.setPoint(calPoint);
 		
		orderMapper.deductMoney(member);
		
		/* 재고 변동 적용 */
		for(OrderItemDTO oit : ord.getOrders()) {
			
			BookVO book = bookMapper.getGoodsInfo(oit.getBookId());
			book.setBookStock(book.getBookStock() - oit.getBookCount());
			
			orderMapper.deductStock(book);
		}
		
		/* 장바구니 제거 */
		for(OrderItemDTO oit : ord.getOrders()) {
			
			CartDTO dto = new CartDTO();
			dto.setMemberId(ord.getMemberId());
			dto.setBookId(oit.getBookId());
			
			cartMapper.deleteOrderCart(dto);
		}
	
	}

	@Override
	@Transactional
	public void orderCancle(OrderCancleDTO dto) {
		
		/* 주문, 주문상품 객체 */
		/*회원*/
			MemberVO member = memberMapper.getMemberInfo(dto.getMemberId());
			System.out.println("@@@@ 환불 멤버 아이디 "+dto.getMemberId());
			System.out.println("@@@@ 환불 오더 아이디 "+dto.getOrderId());
		/*주문상품*/
			List<OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* 주문 */
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* 주문상품 취소 DB */
			orderMapper.orderCancle(dto.getOrderId());
			
	/* 돈, 포인트, 재고 변환 */
			/* 돈 */
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 */
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
				/* DB적용 */
				orderMapper.deductMoney(member);
				
			/* 재고 */
			for(OrderItemDTO ord : orw.getOrders()) {
				BookVO book = bookMapper.getGoodsInfo(ord.getBookId());
				book.setBookStock(book.getBookStock() + ord.getBookCount());
				orderMapper.deductStock(book);
			}	
		
		
	}

		
	
	
}
