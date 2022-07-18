package com.namanok.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeApiController {

	private final NoticeService noticeService;
	
	/**
	 * �Խù� ��� ��ȸ
	 * 
	 * @return
	 */
	@GetMapping(value = "/notice")
	public String noticeList() {
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * �Խù� �� ��ȸ
	 * 
	 * @return
	 */
	@GetMapping(value = "/notice/{seq}")
	public String noticeDetail() {
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * �Խù� ���
	 * 
	 * @return
	 */
	@PostMapping(value = "/notice")
	public String noticeInsert() {
		return "{\"aa\",\"bb\"}";
	}

	/**
	 * �Խù� ����
	 * 
	 * @return
	 */
	@PutMapping(value = "/notice/{seq}")
	public String noticeUpdate() {
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * �Խù� ����
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/notice/{seq}")
	public String noticeDelete() {
		return "{\"aa\",\"bb\"}";
	}
}