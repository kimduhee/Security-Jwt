package com.namanok.service;

import java.util.List;
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
	
	public List<Notice> noticeSearch() {
		return noticeRepository.findAll();
	}
	
	/**
	 * �Խù� ��
	 * 
	 * @param seq
	 * @return
	 */
	public Optional<Notice> noticeDetail(Long seq) {
		return noticeRepository.findById(seq);
	}
	
	/**
	 * �Խù� ����
	 * 
	 * @param seq
	 */
	public void noticeUpdate(Notice notice) {
		noticeRepository.save(notice);
	}
	
	/**
	 * �Խù� ����
	 * 
	 * @param seq
	 */
	public void noticeDelete(Long seq) {
		noticeRepository.deleteById(seq);
	}

	/**
	 * �Խù� ���
	 * 
	 * @param notice
	 */
	public void noticeInsert(Notice notice) {
		noticeRepository.save(notice);
	}
	
}
