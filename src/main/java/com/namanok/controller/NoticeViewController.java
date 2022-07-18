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
	 * �Խ��� ��� ȭ��
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeListView")
	public String noticeListView() {
		return "/noticeListView";
	}
	
	/**
	 * �Խ��� ��� ȭ��
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeRegForm")
	public String noticeRegForm() {
		return "/noticeRegForm";
	}
}
