package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;

public interface BoardService {

	//상품등록
	int register(BookDTO dto);
	
	//게시물 목록조회
	List<BookDTO> getList();
	
	//게시물 상세조회
	BookDTO read(int no);
	
	//게시물 수정
	void modify(BookDTO dto);
	
	//게시물 삭제
	int remove(int no);
	
	//dto를 엔티티로
	default Book dtoToEntity(BookDTO dto) {
		Book entity = Book.builder()
						.no(dto.getNo())
						.title(dto.getTitle())
						.writer(dto.getWriter())
						.company(dto.getCompany())
						.summary(dto.getSummary())
						.price(dto.getPrice())
						.build();
		return entity;
	}
	
	//엔티티를 dto로 변환하는 메소드
	default BookDTO entityToDto(Book entity) {
		
		BookDTO dto = BookDTO.builder()
						.no(entity.getNo())
						.title(entity.getTitle())
						.writer(entity.getWriter())
						.company(entity.getCompany())
						.summary(entity.getSummary())
						.price(entity.getPrice())
						.regDate(entity.getRegDate())
						.build();
		return dto;
	}
}
