package com.namanok.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.controller.dto.NoticeSearchDto;
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
	 * �Խù� ��� ��ȸ
	 * 
	 * @return
	 */
	@PostMapping(value = "/notice/search")
	public NoticeSearchDto noticeSearch() {
		NoticeSearchDto outDto = new NoticeSearchDto();
		outDto.setNoticeSearch(noticeService.noticeSearch());
		
		return outDto;
	}
	
	/**
	 * �Խù� �� ��ȸ
	 * 
	 * @return
	 */
	@GetMapping(value = "/notice/{seq}")
	public String noticeDetail(@RequestParam Long seq) {
		noticeService.noticeDetail(seq);
		return "{\"aa\",\"bb\"}";
	}
	
	/**
	 * �Խù� ���
	 * 
	 * @return
	 */
	@PostMapping(value = "/notice")
	public String noticeInsert(@RequestBody Notice notice) {
		noticeService.noticeInsert(notice);
		return "{\"result\",\"success\"}";
	}

	/**
	 * �Խù� ����
	 * 
	 * @return
	 */
	@PutMapping(value = "/notice/{seq}")
	public String noticeUpdate(@RequestBody Notice notice) {
		noticeService.noticeUpdate(notice);
		return "{\"result\",\"success\"}";
	}
	
	/**
	 * �Խù� ����
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/notice/{seq}")
	public String noticeDelete(@RequestParam Long seq) {
		noticeService.noticeDelete(seq);
		return "{\"result\",\"success\"}";
	}
}
