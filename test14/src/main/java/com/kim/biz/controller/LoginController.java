package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

/*
package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
*/
public class LoginController {

	public void selectOneMember(HttpServletRequest request) {
		MemberVO mVO=new MemberVO();
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO); // 로그인을 수행하는 비즈니스 메서드 R
		
		if(mVO==null) { // 로그인 실패했을 때
		}
		else {
			HttpSession session=request.getSession(); // 로그인 성공했을 때
			session.setAttribute("member", mVO); // 왜 session? 브라우저 끄기전까지 무조건 가지고있어야하니까.
		}
	}
	
	/*
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO mVO=new MemberVO();
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		
		MemberDAO mDAO=new MemberDAO();
		mVO=mDAO.selectOneMember(mVO);
		
		ModelAndView mav=new ModelAndView();
		if(mVO==null) {
			mav.setViewName("redirect:login.jsp");
		}
		else {
			HttpSession session=request.getSession();
			session.setAttribute("member", mVO);
			
			mav.setViewName("redirect:main.do");
		}
		return mav;
	}
	*/

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
