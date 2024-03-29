package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
//	@Override
//	public List<BookDTO> getList() {
//		List<Book> result = repository.findAll();
//		List<BookDTO> list = new ArrayList<>();
//		
//		list = result.stream()
//						.map(entity -> entityToDto(entity))
//						.collect(Collectors.toList());
//		
//		return list;
//	}
	
	//0126리스트페이징처리
	@Override
	public Page<BookDTO> getList(int pageNum){
		int PageNumber = (pageNum == 0) ? 0 : pageNum-1;
		Pageable pageable = PageRequest.of(PageNumber, 8,Sort.by("no").descending());
		
		Page<Book> entityPage = repository.findAll(pageable);
		
		Page<BookDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		return dtoPage;
	}
	
	//0125검색기능
//	public List<BookDTO> searchList(String keyword){
//		List<Book> result = repository.findByTitleContaining(keyword);
//		List<BookDTO> list = new ArrayList<>();
//		
//		list = result.stream()
//						.map(entity -> entityToDto(entity))
//						.collect(Collectors.toList());
//		return list;
//	}
	
	//0128검색후 페이징 처리
	public Page<BookDTO> searchList1(String keyword, int pageNum){
		
		int pageNumber = (pageNum ==0)? 0 : pageNum-1;
		Pageable pageable = PageRequest.of(pageNum, 8, Sort.by("no").descending());
		
		Page<Book> entityPage = repository.findByTitleContaining(keyword, pageable);
		
		Page<BookDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		return dtoPage;
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
