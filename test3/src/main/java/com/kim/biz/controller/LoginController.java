package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO2;

/*
package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
*/
// @Controller
public class LoginController  { // Spring에 있는 Controller를 상속받는다
								// 상단 import를 보면 알 수 있다.
	
	
	/*
	@Autowired
	private MemberDAO2 mDAO;
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO mVO=new MemberVO();
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		System.out.println(mVO);
		
		
		mVO=mDAO.selectOneMember(mVO);
		
		ModelAndView mav=new ModelAndView();
		if(mVO==null) {
			mav.setViewName("redirect:login.jsp"); // redirect : sendRedirect 방식으로 보낸다.
		}										   // 안쓰면 veiwResolve에 의해 앞뒤에 WEB-INF . jsp가 붙음
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			mav.setViewName("redirect:main.do");
		}
		return mav;
	}
	
*/
	
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		MemberVO mVO=new MemberVO();
//		mVO.setMid(request.getParameter("mid"));
//		mVO.setMpw(request.getParameter("mpw"));
//		System.out.println(mVO);
//		
//		
//		mVO=mDAO.selectOneMember(mVO);
//		
//		ModelAndView mav=new ModelAndView();
//		if(mVO==null) {
//			mav.setViewName("redirect:login.jsp"); // redirect : sendRedirect 방식으로 보낸다.
//		}										   // 안쓰면 veiwResolve에 의해 앞뒤에 WEB-INF . jsp가 붙음
//		else {
//			HttpSession session=request.getSession();
//			session.setAttribute("member", mVO);
//			
//			mav.setViewName("redirect:main.do");
//		}
//		return mav;
//	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String mid=request.getParameter("mid");
		String mpw=request.getParameter("mpw");
		MemberVO mVO=new MemberVO();
		mVO.setMid(mid);
		mVO.setMpw(mpw);
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		if(mVO==null) {
			return "login"; // VR가 .jsp를 추가하기때문에 생략해서 반환
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			return "main.do";
		}
	}
	*/

}
