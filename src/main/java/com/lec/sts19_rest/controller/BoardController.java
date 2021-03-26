package com.lec.sts19_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts19_rest.domain.WriteDTO;
import com.lec.sts19_rest.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	

	public BoardController() {
		System.out.println("BoardController() 생성");
	}
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("list", boardService.list());
		return "board/list";
	}
	
	@RequestMapping("/write.do")
	public String write(Model model) {
		return "board/write";
	}
	
	@RequestMapping("/writeOk.do")
	public String writeOk(WriteDTO dto, Model model) {
		model.addAttribute("result", boardService.write(dto));
		return "board/writeOk";
	}
	
	@GetMapping("/view.do")
	public String view(int uid, Model model) {
		model.addAttribute("list", boardService.viewByUid(uid));
		return "board/view";
	}
	
	@RequestMapping("/update.do")
	public String update(int uid, Model model) {
		model.addAttribute("list", boardService.selectByUid(uid));		
		return "board/update";
	}

	@PostMapping("/updateOk.do")
	public String updateOk(WriteDTO dto, Model model) {
		model.addAttribute("result", boardService.update(dto));
		return "board/updateOk";
	}

	@GetMapping("/deleteOk.do")
	public String deleteOk(int uid, Model model) {
		model.addAttribute("result", boardService.deleteByUid(uid));
		return "board/deleteOk";
	}
	
	// REST 게시판 작성
	@RequestMapping(value = "/rest")
	public String rest() {
		return "board/rest";
	}
	
}





























