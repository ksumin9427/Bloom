package com.kim.bloom.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kim.bloom.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO Ivo = (MemberVO) session.getAttribute("member");
		
		if (Ivo == null || Ivo.getAdminCk() == 0) {
			
			response.sendRedirect("/main");
			
			return false;
			
		}
		
		return true;
	}
	
}
