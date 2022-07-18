package com.namanok.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeApiController {

	private final NoticeService noticeService;
	
	@PostMapping(value = "/notice")
	public String noticeList() {
		return "{\"aa\",\"bb\"}";
	}
}
