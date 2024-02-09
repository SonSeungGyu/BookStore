package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

public interface MemberService {
	Page<MemberDTO> getList(int pageNumber);
}
