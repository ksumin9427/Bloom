package com.kim.bloom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kim.bloom.service.MyRoomService;

@Controller
@RequestMapping("/myRoom")
public class MyRoomController {
	
	@Autowired
	private MyRoomService myRoomService;
	
	@RequestMapping(value = "/myRoomMain", method = RequestMethod.GET)
	public void myRoomGet(Model model) throws Exception{
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
	}
}
