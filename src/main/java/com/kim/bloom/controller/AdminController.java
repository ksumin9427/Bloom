package com.kim.bloom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.bloom.model.AuthorVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.PageDTO;
import com.kim.bloom.service.AdminService;
import com.kim.bloom.service.AuthorService;

@Controller
@RequestMapping("/admin") /* 관리자 호출을 url 경로에서 admin를 타도록 설계 */
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	/* 관리자 페이지 접속*/
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGet() throws Exception{
		logger.info("관리자 페이지 이동");
	}

	/* 상품 관리 페이지 접속*/
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGet(Criteria cri, Model model) throws Exception{
		logger.info("상품 관리 페이지 접속");
		
		List list = adminService.goodsGetList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
	}

	/* 상품 등록 페이지 접속*/
	@RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGet(Model model) throws Exception{
		logger.info("상품 등록 페이지 접속");
		
		ObjectMapper objm = new ObjectMapper();
		
		List list = adminService.cateList();
		
		String cateList = objm.writeValueAsString(list);
		
		model.addAttribute("cateList", cateList);
		
		logger.info("변경 전....... "+list);
		logger.info("변경 후....... "+cateList);
	}
	
	/* 작가 등록 페이지 접속 */
    @RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
    public void authorEnrollGet() throws Exception{
        logger.info("작가 등록 페이지 접속");
    }

	/* 작가 관리 페이지 접속 */
    @RequestMapping(value = "/authorManage", method = RequestMethod.GET)
    public void authorManageGet(Criteria cri, Model model) throws Exception{
        logger.info("작가 관리 페이지 접속............. "+cri);
        
        List list = authorService.authorGetList(cri);
        
		/* 검색어 결과 존재 유무 */ 
        if(!list.isEmpty()) {
        	model.addAttribute("list", list);
        } else {
        	model.addAttribute("listCheck", "empty");
        	
        }
        
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
	
	/* @GetMapping은 URL을 배열로 처리할 수 있다 */
    @GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		
		logger.info("authorDetail......." + authorId);
		
		/* 작가 상세 페이지에서 작가 관리 페이지 이동을 위한 작가 관리 페이지 정보 
		  해당 정보가 없으면 무조건 작가 관리 페이지의 1페이지로 이동하게 된다 */
		model.addAttribute("cri", cri);
		
		/* 클릭한 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
		
	}
    
	/* 뷰에서 받은 데이터를 전달받기 위해 AuthorVO 사용 
	 정보 수정 후 작가 관리 페이지로 이동할 때 수정 성공 여부를 알리기 위해 데이터를 전송하기 위하여 RedirectAttributes 사용 */
    @PostMapping("/authorModify")
    public String authorModifyPost(AuthorVO author, RedirectAttributes rttr) throws Exception{
    	logger.info("authorModifyPost......"+author);
    	
    	int result = authorService.authorModify(author);
    	
    	rttr.addFlashAttribute("modify_result", result);
    	
    	return "redirect:/admin/authorManage";
    }
    
	/* 상품 등록 후, 상품 관리 페이지에 이동하였을 때
	 책이 등록되었음을 알리는 경고창을 위해 상품 이름과 addFlashAttribute 사용 */
    @PostMapping("/goodsEnroll")
    public String goodsEnrollPost(BookVO book, RedirectAttributes rttr) {
    	logger.info("goodsEnrollPost..... "+book);
    	
    	adminService.bookEnroll(book);
    	
    	rttr.addFlashAttribute("enroll_result", book.getBookName());
    	
    	return "redirect:/admin/goodsManage";
    }
    
	/* 작가 검색 팝업창 */
    @GetMapping("/authorPop")
    public void authorPopGet(Criteria cri, Model model) throws Exception{
    	logger.info("authorPopGet...........");
    	
    	cri.setAmount(5);
    	
    	List list = authorService.authorGetList(cri);
    	
    	if(!list.isEmpty()) {
    		model.addAttribute("list", list);
    	} else {
    		model.addAttribute("listCheck", "empty");
    	}
    	
    	model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
    }
    
	/* 상품 조회 페이지 */
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGet(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		logger.info("goodsGetInfoGet...........");
		
		/* 카테고리 리스트 데이터를 전달하기 위해 */
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));
		
		/* 목록 페이지 정보 */
		model.addAttribute("cri", cri);
		/* 조회 페이지 정보*/
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
		
	}
	
	@PostMapping("/goodsModify")
	public String goodsModifyPost(BookVO vo, RedirectAttributes rttr) {
		logger.info("goodsModifyPost...........");
		
		int result = adminService.goodsModify(vo);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	/* 상품 삭제 */
	@PostMapping("/goodsDelete")
	public String goodsDeletePost(int bookId, RedirectAttributes rttr) {
		logger.info("goodsDeletePost...........");
		
		int result = adminService.goodsDelete(bookId);
		
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/goodsManage";
		
	}
	
	
	/* 작가 삭제 */
	/* 참조되지 않는 행을 지울 때는 삭제를 수행하고 작가 목록 페이지로 1을 전송
	 * 예외 발생시, 2를 전송 */
	@PostMapping("/authorDelete")
	public String authorDeletePost(int authorId, RedirectAttributes rttr) {
		logger.info("authorDeletePost...........");
		
		int result = 0;
		
		try {
			
			result = authorService.authorDelete(authorId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result = 2;
			
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
		}
		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/authorManage";
		
	}
	
}
