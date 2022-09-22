package com.kim.biz.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kim.biz.board.BoardVO;
import com.kim.biz.board.impl.BoardDAO;


@Controller
public class BoardController {

	
	public String bUpdate(@ModelAttribute("data")BoardVO bVO,BoardDAO bDAO,Model model) {
	bDAO.updateBoard(bVO);
	
	model.addAttribute("data", bVO);
	return "main.jsp";
		
	}
		
		

}
