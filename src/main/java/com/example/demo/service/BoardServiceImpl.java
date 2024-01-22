package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


	//게시물 상세조회
	@Override
	public BookDTO read(int no) {
		Optional<Book> result = repository.findById(no);
		if(result.isPresent()) {
			Book book = result.get();
			BookDTO bookDTO = entityToDto(book);
			return bookDTO;
		}
		return null;
	}


	//게시물 수정
	@Override
	public void modify(BookDTO dto) {

		Optional<Book> result = repository.findById(dto.getNo());
		if(result.isPresent()) {
			Book entity = result.get();
			
			entity.setCompany(dto.getCompany());
			entity.setPrice(dto.getPrice());
			entity.setSummary(dto.getSummary());
			entity.setTitle(dto.getTitle());
			entity.setWriter(dto.getWriter());
			
			repository.save(entity);
		}
	}


	@Override
	public int remove(int no) {
		
		Optional<Book> result = repository.findById(no);
		if(result.isPresent()) {
			repository.deleteById(no);
			return 1;
		}else {
			return 0;
		}
	}
	
	
	
	
	
	
	
}
