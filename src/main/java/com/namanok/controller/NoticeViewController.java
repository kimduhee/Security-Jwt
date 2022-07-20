package com.namanok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeViewController {
	
	/**
	 * 게시판 목록 화면
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeList")
	public String noticeList() {
		return "/noticeList";
	}
	
	/**
	 * 게시판 상세 화면
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeDetail")
	public String noticeDetail() {
		return "/noticeDetail";
	}
	
	/**
	 * 게시판 등록 화면
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeRegForm")
	public String noticeRegForm() {
		return "/noticeRegForm";
	}
	
	/**
	 * 게시판 수정 화면
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeUpdForm")
	public String noticeUpdForm() {
		return "/noticeUpdForm";
	}
}
