package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BookDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;
	
	@Test
	public void 게시물등록() {
		BookDTO dto = BookDTO.builder()
						.title("둘리")
						.writer("또치")
						.summary("길동")
						.company("책방")
						.price(12000)
						.build();
		int no = service.register(dto);
		
		System.out.println("새로운 책: " + no);
	}
	
//	@Test
//	public void 게시물목록조회() {
//		
//		List<BookDTO> list = service.getList();
//		
//		for (BookDTO bookDTO : list) {
//			System.out.println(bookDTO);
//		}
//	}
	
	@Test
	public void 게시물수정() {
		BookDTO dto = service.read(10); 
		dto.setCompany("수정");
		dto.setPrice(120000);
		service.modify(dto);
		System.out.println(dto);
	}
	
	@Test
	public void 게시물삭제() {
		service.remove(1);
	}

}
