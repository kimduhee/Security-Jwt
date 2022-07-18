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
	@GetMapping(value="/noticeListView")
	public String noticeListView() {
		return "/noticeListView";
	}
	
	/**
	 * 게시판 상세 화면
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeDetailView")
	public String noticeDetailView() {
		return "/noticeDetailView";
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
