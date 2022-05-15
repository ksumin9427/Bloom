package com.kim.bloom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.PageDTO;
import com.kim.bloom.service.AuthorService;

@Controller
@RequestMapping("/admin") /* 관리자 호출을 url 경로에서 admin를 타도록 설계 */
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGet() throws Exception{
		logger.info("관리자 페이지 이동");
	}
	
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGet() throws Exception{
		logger.info("상품 관리 페이지 이동");
	}
	
	@RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGet() throws Exception{
		logger.info("상품 등록 페이지 이동");
	}
	
    @RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGet() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }
    
    @RequestMapping(value = "/authorManage", method = RequestMethod.GET)
    public void authorManageGet(Criteria cri, Model model) throws Exception{
        logger.info("작가 관리 페이지 접속............. "+cri);
        
        List list = authorService.authorGetList(cri);
        
        model.addAttribute("list", list);
        
        int total = authorService.authorGetTotal(cri);
        PageDTO pageMaker = new PageDTO(cri, total);
        
        model.addAttribute("pageMaker", pageMaker);
       
    }
    
    @RequestMapping(value = "/authorEnroll.do", method = RequestMethod.POST)
    public String authorEnrollPost(AuthorVO author, RedirectAttributes rttr) throws Exception{
    	logger.info("작가 등록 : "+author);
    	
    	authorService.authorEnroll(author);
    	
    	rttr.addFlashAttribute("enroll_result", author.getAuthorName());
    	
    	return "redirect:/admin/authorManage";
    }
	
	
	
	
}
