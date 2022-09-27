package com.kim.bloom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kim.bloom.model.MemberVO;
import com.kim.bloom.service.MemberSerivice;
import com.kim.bloom.service.MyRoomService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/myRoom")
public class MyRoomController {
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	@Autowired
	private MyRoomService myRoomService;

	@Autowired
	private MemberSerivice memberSerivice;
	
	@RequestMapping(value = "/myRoomMain", method = RequestMethod.GET)
	public void myRoomGet(Model model) throws Exception {

		model.addAttribute("cate1", myRoomService.getCateCode1());
		model.addAttribute("cate2", myRoomService.getCateCode2());

	}

	/* 나의정보확인 */
	@RequestMapping(value = "/myRoomInfo")
	public void myRoomInfoGet(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("member");
		vo = myRoomService.getMyRoomInfo(vo.getMemberId());
		log.info("나의정보확인에서의 vo "+vo);
		
		model.addAttribute("member", vo);
		
	}
	
	/* 나의정보수정 페이지 진입 */
	@GetMapping({"/myRoomInfoModify"})
	public String myRoomInfoModifyGet(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("member");
		vo = myRoomService.getMyRoomInfo(vo.getMemberId());
		
		model.addAttribute("member", vo);
		
		return "myRoom/myRoomInfoModify";
		
	}
	
	/* 나의정보수정 메서드 */
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(MemberVO vo, RedirectAttributes rttr) throws Exception{
		
		int result = myRoomService.memberUpdate(vo);
		
		log.info("나의정보수정 메서드 작동 후의 vo "+vo);
		
		rttr.addFlashAttribute("modify_result", result);
	
		return "redirect:/myRoom/myRoomInfo";

	}
	
	/* 회원탈퇴페이지 진입 */
	@RequestMapping(value = "/memberDelete")
	public String memberDeleteGet(/* HttpServletRequest request, Model model */) {
		log.info("회원탈퇴페이지 진입");
		 
		return "myRoom/memberDelete";
		
	}
	
	/* 회원탈퇴 메서드 */
	@RequestMapping(value = "/memberDelete.do", method = RequestMethod.POST)
	public String memberDeletePost(HttpServletRequest request,MemberVO vo, RedirectAttributes rttr) throws Exception {
		
		log.info("회원탈퇴 메서드 작동vo "+vo);
		
		HttpSession session = request.getSession();
		
		String rawPw = ""; /* 사용자가 제출한 비밀번호 */ 
		String encodePw = ""; /* 데이터베이스에 있는 인코딩된 비밀번호 */
		
		MemberVO Ivo = memberSerivice.memberLogin(vo);
		
		if (Ivo != null) {
			
			rawPw = vo.getMemberPw(); /* 사용자가 제출한 비밀번호 */ 
			encodePw = Ivo.getMemberPw(); /* 데이터베이스에 있는 인코딩된 비밀번호 */
			log.info("일치여부"+pwEncoder.matches(rawPw, encodePw));
			
			if (true == pwEncoder.matches(rawPw, encodePw)) {
				int result = myRoomService.memberDelete(Ivo);
				rttr.addFlashAttribute("delete_result", result);
				session.invalidate(); 
				return "redirect:/main";
			} 
			
		}
		
		return "redirect:/myRoom/memberDelete";
		

		}
		
		
		
		
		
			
			
		
		
		
		
	
	

}
