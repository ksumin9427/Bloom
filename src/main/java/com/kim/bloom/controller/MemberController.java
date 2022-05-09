package com.kim.bloom.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kim.bloom.model.MemberVO;
import com.kim.bloom.service.MemberSerivice;

/*회원과 관련된 요청을 따로 관리하기 위해 controller를 분리*/
@Controller
@RequestMapping("/member") /* 회원과 관련된 호출을 url 경로에서 member를 타도록 설계 */
public class MemberController {
	
	/* Console 창에 해당 로그가 찍혀 프로그램이 오류 발생 시 어디서 어떤 이유로 오류가 발생하는지 알 수 있어 이슈 처리가 용이 */
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberSerivice memberSerivice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String loginGet() {
		logger.info("회원가입 페이지 진입");
		
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberVO member) throws Exception {
		
		String rawPw = ""; /* 인코딩 전 비밀번호 */
		String encodePw= ""; /* 인코딩 후 비밀번호 */
		
		rawPw = member.getMemberPw();
		encodePw = pwEncoder.encode(rawPw); /* 비밀번호를 인코딩 */
		member.setMemberPw(encodePw); /* 인코딩한 비밀번호를 member에 저장 */
		
		memberSerivice.memberJoin(member);
		
		return "redirect:/main";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String joinGet() {
		logger.info("로그인 페이지 진입");
		
		return "login";
	}
	
	/* 아이디 중복 검사를 실행하는 메서드 */
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody /* ajax를 통해서 서버에 요청을 하는 방식이라면 해당 메서드에 @responseBody를 붙여야 한다 */
	public String memberIdChkPost(String memberId) throws Exception{
		logger.info(" memberIdChk() 진입");
		
		int result = memberSerivice.idCheck(memberId);
		
		logger.info("결과값 = "+result);
		
		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}
		
	}
	
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody /* ajax를 통해서 서버에 요청을 하는 방식이라면 해당 메서드에 @responseBody를 붙여야 한다 */
	public String mailCheckGet(String email) throws Exception{
		
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증이메일 : "+email);
		
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111; /* 111111-999999까지의 범위 */
		logger.info("인증번호 : "+checkNum);
		
		String setFrom = "ksumin9427@gmail.com";
		String toMail = email;
		String title = "Bloom 회원가입 인증 이메일 입니다.";
		String content = "Bloom을 방문해주셔서 감사합니다."
					+"<br><br>"
					+"인증번호는 "+checkNum+" 입니다."
					+"<br>"
					+"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		/* 이메일 전송을 위한 코드 */
		  try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	            helper.setFrom(setFrom);
	            helper.setTo(toMail);
	            helper.setSubject(title);
	            helper.setText(content,true);
	            mailSender.send(message);
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
		/* ajax를 통한 요청으로 뷰로 데이터를 반환할 때 string 타입만 가능하다 */
		  String num = Integer.toString(checkNum);
		  
		  return num;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
		/* MemberVO 데이터를 전달받기 위해
		 HttpServletRequest 로그인 성공시에 session에 회원 정보를 저장하기 위해
		 RedirectAttributes(리다이렉트 할때 파라미터를 넘겨야 할 경우 사용)은 로그인 실패시에 리다이렉트 된 로그인 페이지에 실패를 의미하는 데이터를 전송하기 위해   */
		
		HttpSession session = request.getSession();
		String rawPw = ""; 
		String encodePw= ""; 
		
		MemberVO Ivo = memberSerivice.memberLogin(member);
		
		if (Ivo != null) {
		
			rawPw = member.getMemberPw(); /* 사용자가 제출한 비밀번호 */ 
			encodePw = Ivo.getMemberPw(); /* 데이터베이스에 있는 인코딩된 비밀번호 */
			
			if (true == pwEncoder.matches(rawPw, encodePw)) {
				
				Ivo.setMemberPw(""); /* 세션에 사용자 비밀번호 정보는 지워준다 */
				session.setAttribute("member", Ivo);
				return "redirect:/main";
				
			} else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
			 
			
		} else {
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";
		}
		
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutMainGet(HttpServletRequest request) throws Exception{
		
		logger.info("logoutMainGet메서드 진입");
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	@ResponseBody /* ajax를 통해서 서버에 요청을 하는 방식이라면 해당 메서드에 @responseBody를 붙여야 한다 */
	public void logoutPost(HttpServletRequest request) throws Exception{
		logger.info("비동기 로그아웃 메서드 진입");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
	}
	
}
