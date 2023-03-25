package com.kim.bloom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.bloom.model.MemberVO;
import com.kim.bloom.model.OrderDTO;
import com.kim.bloom.model.OrderPageDTO;
import com.kim.bloom.service.BookService;
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
	
	@Autowired
	private BookService bookService;
	
	/* 주문 페이지 이동 */
	@GetMapping("/order/{memberId}")
	public String orderPageGet(@PathVariable("memberId") String memberId, OrderPageDTO opd, Model model) {
		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
		model.addAttribute("orderList", orderService.getGoodsInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberSerivice.getMemberInfo(memberId));
		
		return "/order";
	}
	
	@PostMapping("/order")
	public String orderPagePost(OrderDTO od, HttpServletRequest request) {
		
		orderService.order(od);
		
		MemberVO member = new MemberVO();
		member.setMemberId(od.getMemberId());
		
		HttpSession session = request.getSession();
		
		/* memberLogin에 throw를 사용하였기 때문에 try catch 문을 사용 */
		try {
			MemberVO memberLogin = memberSerivice.memberLogin(member);
			memberLogin.setMemberPw("");
			session.setAttribute("member", memberLogin);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/main";
	}
	
	
	
	
}
