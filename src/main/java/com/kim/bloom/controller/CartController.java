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

import com.kim.bloom.model.CartDTO;
import com.kim.bloom.model.MemberVO;
import com.kim.bloom.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart/add")
	@ResponseBody
	public String addCartPost(CartDTO cart, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		if(mvo == null) {
			return "5";
		}
		
		int result = cartService.addCart(cart);
		return result+"";
	}
	
	@GetMapping("/cart/{memberId}")
	public String cartPageGet(@PathVariable("memberId") String memberId, Model model) {
		
		model.addAttribute("cartInfo", cartService.getCartList(memberId));
		
		return "/cart";
	}
	
	@PostMapping("/cart/update")
	public String updateCartPost(CartDTO cart) {
		cartService.modifyCount(cart);
		
		return "redirect:/cart/" +cart.getMemberId();
	}
	
	@PostMapping("/cart/delete")
	public String deleteCartPost(CartDTO cart) {
		cartService.deleteCart(cart.getCartId());
		
		return "redirect:/cart/" +cart.getMemberId();
		
	}
}
