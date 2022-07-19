package com.namanok.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.entity.Notice;
import com.namanok.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeApiController {

	private final NoticeService noticeService;
	
	/**
	 * 게시물 목록 조회
	 * 
	 * @return
	 */
	@GetMapping(value = "/notice")
	public String noticeList() {
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * 게시물 상세 조회
	 * 
	 * @return
	 */
	@GetMapping(value = "/notice/{seq}")
	public String noticeDetail(@RequestParam Long seq) {
		noticeService.noticeDetail(seq);
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * 게시물 등록
	 * 
	 * @return
	 */
	@PostMapping(value = "/notice")
	public String noticeInsert(@RequestBody Notice notice) {
		noticeService.noticeInsert(notice);
		return "{\"aa\",\"bb\"}";
	}

	/**
	 * 게시물 수정
	 * 
	 * @return
	 */
	@PutMapping(value = "/notice/{seq}")
	public String noticeUpdate(@RequestBody Notice notice) {
		noticeService.noticeUpdate(notice);
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * 게시물 삭제
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/notice/{seq}")
	public String noticeDelete(@RequestParam Long seq) {
		noticeService.noticeDelete(seq);
		return "{\"aa\",\"bb\"}";
	}
}
