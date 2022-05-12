package com.kim.bloom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kim.bloom.model.DesignerVO;
import com.kim.bloom.service.DesignerService;

@Controller
@RequestMapping("/admin") /* 관리자 호출을 url 경로에서 admin를 타도록 설계 */
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private DesignerService designerServiece;
	
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGet() throws Exception{
		logger.info("상품 관리 페이지 이동");
	}
	
	@RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGet() throws Exception{
		logger.info("상품 등록 페이지 이동");
	}
	
	@RequestMapping(value = "/designerEnroll", method = RequestMethod.GET)
	public void designerEnrollGet() throws Exception{
		logger.info("디자이너 등록 페이지 이동");
	}
	
	@RequestMapping(value = "/designerManage", method = RequestMethod.GET)
	public void designerManageGet() throws Exception{
		logger.info("디자이너 관리 페이지 이동");
	}
	
	@RequestMapping(value = "/designerEnroll.do", method = RequestMethod.POST)
	public String DesignerEnrollPost(DesignerVO designer, RedirectAttributes rttr) throws Exception{
		logger.info("designerEnroll : "+ designer);
		
		designerServiece.DesignerEnroll(designer);
		
		/* 리다이렉트할 때 파라미터를 보내줄 때 사용*/
		rttr.addFlashAttribute("enroll_result", designer.getDesignerName());
		
		return "redirect:/admin/designerManage";
		
	}
	
}
