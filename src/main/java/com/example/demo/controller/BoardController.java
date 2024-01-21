package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	@Autowired
	BoardService service;
	
	@GetMapping("/main")
	public void main() {
		
	}
	
	//목록화면
	@GetMapping("/list")
	public void list(Model model) {
		List<BookDTO> list = service.getList();
		
		model.addAttribute("list",list);
	}
	
	//등록화면 
	@GetMapping("/register")
	public void register() {
		
	}
	

}
