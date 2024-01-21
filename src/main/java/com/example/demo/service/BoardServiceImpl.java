package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BookRepository repository;

	
	//게시물 등록  
	@Override
	public int register(BookDTO dto) {
		Book entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		
		
		return newNo;
	}


	//게시물 목록조회 
	@Override
	public List<BookDTO> getList() {
		List<Book> result = repository.findAll();
		List<BookDTO> list = new ArrayList<>();
		
		list = result.stream()
						.map(entity -> entityToDto(entity))
						.collect(Collectors.toList());
		
		return list;
	}
	
	
	
	
	
}
