package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;


public class MypageController{


	public ModelAndView mymage(MemberVO mVO,MemberDAO mDAO,ModelAndView mav){
	
		mVO = mDAO.selectOneMember(mVO);
		
		mav.addObject("data",mVO);
		mav.setViewName("mypage.jsp");
		return mav;
	}
	

}
