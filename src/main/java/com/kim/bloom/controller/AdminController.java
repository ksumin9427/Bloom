package com.kim.bloom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin") /* 관리자 호출을 url 경로에서 admin를 타도록 설계 */
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGet() throws Exception{
		logger.info("관리자 페이지 이동");
	}
}
