package com.kim.biz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

@Controller
@SessionAttributes("member")
public class MemberController {
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String index() {
		return "login.jsp";
	}
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String selectOneMember(MemberVO mVO,MemberDAO mDAO,Model model,HttpSession session) {
		
		if(mVO==null) { 
			return "login.do";
			
			
		}
		else {
			mVO=mDAO.selectOneMember(mVO);
			session.setAttribute("mid", mVO.getMid());
			model.addAttribute("member",mVO);
			return "main.jsp";
		}
		
	}
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:login.do";
	} 
	@RequestMapping("/mypage.do")
	public String mymage(@ModelAttribute("member")MemberVO mVO,MemberDAO mDAO, Model model ){
		System.out.println(mVO);
		mVO = mDAO.selectOneMember(mVO);
		model.addAttribute("member", mVO);
		
		return "mypage.jsp";
	}
	@RequestMapping("/mupdate.do")
	public String mupdate(@ModelAttribute("member")MemberVO mVO,MemberDAO mDAO, HttpSession session, Model model) {
		System.out.println("update전 멤버정보: "+mVO);
		mDAO.updateMember(mVO);
		MemberVO mVO2 =mDAO.selectOneMember(mVO);
		System.out.println("update후 멤버정보: "+mVO2);
		session.setAttribute("mid", mVO.getMid());
		// 세션 어트리뷰트 후 사용자의 요청수행
		return "mypage.jsp";
	}
	
}
