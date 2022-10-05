package com.kim.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;
import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

@Controller
@SessionAttributes("data") // "data"라는 이름의 데이터가 Model 객체에 세팅이된다면, 그것을 session에 기억시키겠다.
public class BoardController {
	
	@Autowired
	private BoardService boardService; // 비즈니스 컴포넌트. DAO를 직접 이용 xxx
	@Autowired
	private MemberService memberService;
	
	@ModelAttribute("scMap")
	public Map<String,String> searchConditionMap(){
		Map<String,String> scMap=new HashMap<String,String>();
		scMap.put("제목", "TITLE");
		scMap.put("작성자", "WRITER");
		return scMap;
	}
	
	@RequestMapping("/main.do")
	public String main(HttpSession session,MemberVO mVO,BoardVO bVO,Model model) {
		// 검색조건이 NULL인지아닌지 체크하는 로직
		if(bVO.getSearchCondition()==null) {
			bVO.setSearchCondition("TITLE");
		}
		if(bVO.getSearchContent()==null) {
			bVO.setSearchContent("");
		}
		
		List<BoardVO> datas=boardService.selectAllBoard(bVO);
		model.addAttribute("datas", datas);

		mVO=(MemberVO)session.getAttribute("user");
		mVO=memberService.selectOneMember(mVO);
		model.addAttribute("userName", mVO.getName());
		
		return "main.jsp";
	}
	
	@RequestMapping("/board.do")
	public String board(BoardVO bVO,Model model) {
		bVO=boardService.selectOneBoard(bVO);

		model.addAttribute("data", bVO);
		return "board.jsp";
	}
	
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String showInsert() {
		return "insert.jsp";
	}
	
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public String insert(BoardVO bVO) throws IllegalStateException, IOException {
		System.out.println("insert.do 로그: "+bVO);
		
		/*
		MultipartFile uploadFile=bVO.getUploadFile();
		if(!uploadFile.isEmpty()) { // 업로드한 파일 존재여부 확인
			String fileName=uploadFile.getOriginalFilename(); // 업로드한 파일명
			uploadFile.transferTo(new File("D:\\0607KIM\\workspace2\\test\\src\\main\\webapp\\images\\"+fileName));
			// 업로드한 파일을 지정한 경로에 저장
		}
		*/
		
		bVO.setFileName("default.jpg");
		MultipartFile uploadFile=bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName=uploadFile.getOriginalFilename();
			bVO.setFileName(fileName);
			uploadFile.transferTo(new File("D:\\0607KIM\\workspace2\\test\\src\\main\\webapp\\images\\"+fileName));
		}
		
		boardService.insertBoard(bVO);
		return "redirect:main.do";
	}
	@RequestMapping("/update.do")
	public String update(@ModelAttribute("data")BoardVO bVO) throws IllegalStateException, IOException {
		System.out.println("update.do 로그: "+bVO); // @SA,@MA 덕분에 writer 등의 값도 로그에 출력된다.
		
		MultipartFile uploadFile=bVO.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName=uploadFile.getOriginalFilename();
			bVO.setFileName(fileName);
			uploadFile.transferTo(new File("D:\\0607KIM\\workspace2\\test\\src\\main\\webapp\\images\\"+fileName));
		}
		
		boardService.updateBoard(bVO);
		return "redirect:main.do";
	}
	@RequestMapping("/delete.do")
	public String delete(BoardVO bVO) {
		boardService.deleteBoard(bVO);
		return "redirect:main.do";
	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO bVO=new BoardVO();
		bVO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDAO bDAO=new BoardDAO();
		bVO=bDAO.selectOneBoard(bVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("data", bVO);
	
		return "board";
	}
	 */
	
}
