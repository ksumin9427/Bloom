package com.kim.bloom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kim.bloom.model.OrderPageDTO;
import com.kim.bloom.service.MemberSerivice;
import com.kim.bloom.service.OrderService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberSerivice memberSerivice;
	
	/* 주문 페이지 이동 */
	@GetMapping("/order/{memberId}")
	public String orderPageGet(@PathVariable("memberId") String memberId, OrderPageDTO opd, Model model) {
	
		model.addAttribute("orderList", orderService.getGoodsInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberSerivice.getMemberInfo(memberId));
		
		return "/order";
	}
	
	
}
