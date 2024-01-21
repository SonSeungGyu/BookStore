package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Book;

@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	BookRepository repository;
	
	@Test
	void 상품등록() {
		List<Book> list = new ArrayList<>();
		Book book1 = Book.builder()
					.title("읽을거리")
					.writer("김민정")
					.company("난다")
					.summary("인 김민정이 매일매일 그러모은 1월의, 1월에 의한, 1월을 위한 단 한 권의 읽을거리")
					.price(16000)
					.build();
		list.add(book1);
		Book book2 = Book.builder()
					.title("보통 이하의 것들")
					.writer("페렉")
					.company("녹색광선")
					.summary("한 권의 읽을거리")
					.price(15000)
					.build();
		list.add(book2);
		
		repository.saveAll(list);
		
	}
	
	@Test
	void 상품목록조회() {
		List<Book> list = repository.findAll();
		for (Book book : list) {
			System.out.println(book);
		}
	}
}
