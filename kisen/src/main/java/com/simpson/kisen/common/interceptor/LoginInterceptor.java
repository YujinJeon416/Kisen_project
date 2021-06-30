package com.simpson.kisen.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.simpson.kisen.fan.model.vo.Fan;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 여부를 체크해서 로그인하지 않은 경우, return false
		
		// session에 저장된 loginMember가져오기
		// 핸들러매핑 이전이기 때문에, 원래의 방식대로 사용해야함
		HttpSession session = request.getSession();
		
		Fan loginMember = (Fan) session.getAttribute("loginMember");
		if(loginMember == null) {
			// FlashMap을 통해서 redirect후 사용자피드백 전달하기
			// redirectAttribute의 addFlashAttribute는 handler에서만 사용가능함
			// addFlashAttribute는 내부적으로 flashMap 이용
			// flashMap을 이용하면 그와 같은 효과를 낼 수 있음
			FlashMap flashMap = new FlashMap();
			flashMap.put("msg", "로그인 후 사용하실 수 있습니다.");
			// flashMapManager를 통해 flashMap 등록
			FlashMapManager manager 
				= RequestContextUtils.getFlashMapManager(request);
			manager.saveOutputFlashMap(flashMap, request, response);
			
			// 로그인 후 최종이동할 url을 session속성 next로 저장
			// getRequestURL() - http로 시작하는 값이 들어옴
			// http://localhost:9090/spring/board/boardDetail.do?no=60&q=abc
			// requestURL은 물음표 앞의 부분까지만 가져올 수 있음
			String url = request.getRequestURL().toString(); // http://localhost:9090/spring/board/boardDetail.do
			// queryString으로 물음표 뒤의 부분 가져오기
			String queryString = request.getQueryString(); // no=60&q=abc
			session.setAttribute("next", url + "?" + queryString);
			
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}