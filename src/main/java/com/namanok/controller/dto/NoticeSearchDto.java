package com.namanok.controller.dto;

import java.util.List;

import com.namanok.entity.Notice;

import lombok.Data;

@Data
public class NoticeSearchDto {
	private List<Notice> noticeSearch;
}
