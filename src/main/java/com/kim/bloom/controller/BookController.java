package com.kim.bloom.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kim.bloom.mapper.AttachMapper;
import com.kim.bloom.model.AttachImageVO;
import com.kim.bloom.model.BookVO;
import com.kim.bloom.model.Criteria;
import com.kim.bloom.model.PageDTO;
import com.kim.bloom.model.ReplyDTO;
import com.kim.bloom.service.AttachService;
import com.kim.bloom.service.BookService;
import com.kim.bloom.service.ReplyService;

@Controller
public class BookController {
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReplyService replyService;
	
	/* Console 창에 해당 로그가 찍혀 프로그램이 오류 발생 시 어디서 어떤 이유로 오류가 발생하는지 알 수 있어 이슈 처리가 용이 */
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainPageGet(Model model) {
		logger.info("메인페이지 진입");
		
		model.addAttribute("cate1", bookService.getCateCode1());
		model.addAttribute("cate2", bookService.getCateCode2());
		model.addAttribute("ls", bookService.likeSelect());
		
		return "main";
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		logger.info("getImage............"+fileName);
		
		File file = new File("c:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return result;
	}
	
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		logger.info("getAttachList............"+bookId);
		
		return new ResponseEntity(attachService.getAttachList(bookId), HttpStatus.OK);
		
	}
	
	/* 상품 검색 */
	@GetMapping("search")
	public String searchGoodsGet(Criteria cri, Model model) {
		logger.info("searchGoodsGet............");
		
		List<BookVO> list = bookService.getGoodsList(cri);
		
		logger.info("pre list : "+list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : "+list);
		} else {
			model.addAttribute("listCheck", "empty");
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		
		String[] typeArr = cri.getType().split("");
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", bookService.getCateInfoList(cri));
			}
		}
		
		return "search";
		
	}
	
	@GetMapping("/goodsDetail/{bookId}")
	public String goodsDetailGet(@PathVariable("bookId")int bookId, Model model) {
		logger.info("goodsDetailGet............");
		
		model.addAttribute("goodsInfo", bookService.getGoodsInfo(bookId));
		
		return "/goodsDetail";
		
	}
	
	@GetMapping("/replyEnroll/{memberId}")
	public String replyEnrollWindowGet(@PathVariable("memberId")String memberId, int bookId, Model model) {
		
		BookVO book = bookService.getBookIdName(bookId);
		model.addAttribute("bookInfo",book);
		model.addAttribute("memberId",memberId);
		
		return "/replyEnroll";
		
	}
	
	@GetMapping("/replyUpdate")
	public String replyUpdateWindowGet(ReplyDTO dto, Model model) {
		
		BookVO book = bookService.getBookIdName(dto.getBookId());
		model.addAttribute("bookInfo", book);
		model.addAttribute("replyInfo", replyService.getUpdateReply(dto.getReplyId()));
		model.addAttribute("memberId", dto.getMemberId());
		
		return "/replyUpdate";
		
	}
	
	
}
