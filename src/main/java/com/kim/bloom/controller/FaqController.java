package com.kim.bloom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.bloom.mapper.FaqMapper;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.FaqVO;
import com.kim.bloom.model.PageDTO;
import com.kim.bloom.service.FaqService;
import com.kim.bloom.service.MyRoomService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private MyRoomService myRoomService;

	@Autowired
	private FaqService faqService;
	
	@Autowired
	private FaqMapper faqMapper;

	/* faq 목록 페이지 진입(페이지 적용) */
	@GetMapping("/faqList")
	public void faqListGet(Model model, Criteria cri) throws Exception {

		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		
		List list = faqService.getFaqList(cri);
		model.addAttribute("list", list);
		log.info("@@@@@@@@@@@@@@@@@@###############list "+list);
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = faqService.getTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
		
		

	}
	
	/* 상품 검색 */
	@GetMapping("/faqSearch")
	public void searchFaqGET(Criteria cri, Model model) throws Exception {
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		List<FaqVO> list = faqService.getFaqSearch(cri);
		
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listcheck", "empty");
		}
		model.addAttribute("pageMaker", new PageDTO(cri, faqService.getFaqSearchTotal(cri)));
		log.info("search 메서드 사용@@@@@@@@@@@@@@@@@@@@@");
	}

	/* faq 등록 페이지 진입 */
	@GetMapping("/faqEnroll")
	public void faqEnrollGet(Model model) throws Exception {
		log.info("faq 등록 페이지 진입");
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());

		ObjectMapper objm = new ObjectMapper();

		List list = faqService.cateList();
		

		String cateList = objm.writeValueAsString(list); /* 자바 객체를 string 타입의 json 형식 데이터로 변환 */

		model.addAttribute("cateList", cateList);

		log.info("변경 전.........." + list);
		log.info("변경 후.........." + cateList);

	}

	/* faq 게시글 등록 */
	@PostMapping("/faqEnroll.do")
	public String faqEnrollPost(FaqVO faq, RedirectAttributes rttr, Model model) throws Exception {
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		if ((faq.getCateCode()).equals("101")) { faq.setCateName("주문"); } 
		else if((faq.getCateCode()).equals("102")) { faq.setCateName("결제"); } 
		else if((faq.getCateCode()).equals("103")) { faq.setCateName("교환"); } 
		else if((faq.getCateCode()).equals("104")) { faq.setCateName("환불"); } 
		else if((faq.getCateCode()).equals("105")) { faq.setCateName("포인트"); } 
		else if((faq.getCateCode()).equals("106")) { faq.setCateName("회원"); } 
		
		faqService.faqEnroll(faq);

		rttr.addFlashAttribute("result", "enroll_success");

		return "redirect:/faq/faqList";

	}

	/* faq 게시글 조회 */
	@GetMapping({ "/faqGet", "/faqModify" })
	public void FaqGetPageGet(int fno, Model model, Criteria cri) throws Exception {
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		ObjectMapper mapper = new ObjectMapper();

		model.addAttribute("pageMaker", faqService.getFaqPage(fno));
		model.addAttribute("cri", cri);
		model.addAttribute("cateList", mapper.writeValueAsString(faqService.cateList()));

	}

	@PostMapping("/faqModify")
	public String faqModifyPost(FaqVO faq, RedirectAttributes rttr, Model model) throws Exception {
		
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		faqService.faqModify(faq);

		rttr.addFlashAttribute("result", "modify_success");

		return "redirect:/faq/faqList";
	}

	@PostMapping("/faqDelete")
	public String faqDeletePost(int fno, RedirectAttributes rttr, Model model) throws Exception {
		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());
		
		faqService.faqDelete(fno);

		rttr.addFlashAttribute("result", "delete_success");

		return "redirect:/faq/faqList";
	}
	
	

}
