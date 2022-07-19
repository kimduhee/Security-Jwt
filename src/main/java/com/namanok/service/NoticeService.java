package com.namanok.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.namanok.entity.Notice;
import com.namanok.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;
	
	/**
	 * 게시물 상세
	 * 
	 * @param seq
	 * @return
	 */
	public Optional<Notice> noticeDetail(Long seq) {
		return noticeRepository.findById(seq);
	}
	
	/**
	 * 게시물 삭제
	 * 
	 * @param seq
	 */
	public void noticeDelete(Long seq) {
		noticeRepository.deleteById(seq);
	}

	/**
	 * 게시물 등록
	 * 
	 * @param notice
	 */
	public void noticeInsert(Notice notice) {
		noticeRepository.save(notice);
	}
	
}
