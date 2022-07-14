package com.namanok.controller;

import org.springframework.web.bind.annotation.RestController;

import com.namanok.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	
}
