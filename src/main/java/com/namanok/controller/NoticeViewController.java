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
	@GetMapping(value="/noticeList")
	public String noticeList() {
		return "/noticeList";
	}
	
	/**
	 * �Խ��� �� ȭ��
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeDetail")
	public String noticeDetail() {
		return "/noticeDetail";
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
	
	/**
	 * �Խ��� ���� ȭ��
	 * 
	 * @return
	 */
	@GetMapping(value="/noticeUpdForm")
	public String noticeUpdForm() {
		return "/noticeUpdForm";
	}
}
