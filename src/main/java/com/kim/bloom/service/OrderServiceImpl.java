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
import com.kim.bloom.model.MemberVO;
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
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddMM");
		String orderId = member.getMemberId()+format.format(date);
		ord.setOrderId(orderId);
		
		/* DB에 넣기 */
		orderMapper.enrollOrder(ord);
		for(OrderItemDTO oit : ord.getOrders()) {
			oit.setOrderId(orderId);
			orderMapper.enrollOrderItem(oit);
		}
		
		int calMoney = member.getMoney();
		calMoney -= ord.getOrderFinalSalePrice();
		member.setMoney(calMoney);
 		
 		MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
 		
 		List<OrderItemDTO> ords = new ArrayList<OrderItemDTO>();
	}
	
	
}
