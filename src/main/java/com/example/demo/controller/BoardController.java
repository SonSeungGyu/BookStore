package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {


	@Autowired
	BoardService service;


	// 목록화면
	@GetMapping("/list")
	public void list(Model model) {
		List<BookDTO> list = service.getList();

		model.addAttribute("list", list);
	}

	// 등록화면
	@GetMapping("/register")
	public void register() {

	}

	// 등록처리
	@PostMapping("/register")
	public String registerPost(BookDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", no);
		return "redirect:/board/list";

	}

	// 상세화면
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		BookDTO dto = service.read(no);
		model.addAttribute("dto", dto);


	}


	// 수정화면
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		BookDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	// 수정진행
	@PostMapping("/modify")
	public String modifyPost(BookDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/board/read";
	}

	// 상품삭제
	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/board/list";
	}






}
