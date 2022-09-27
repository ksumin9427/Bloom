package com.kim.bloom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kim.bloom.service.MyRoomService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/faq")
public class FaqController {
	
	@Autowired
	private MyRoomService myRoomService;
	
	/* faq페이지 진입 */
	@RequestMapping("/faqMain")
	public void faqGet(Model model) throws Exception {
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
	}
}
