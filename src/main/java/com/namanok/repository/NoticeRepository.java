package com.namanok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namanok.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	//finByXXX 시작시 XXX에 대한 조건절 조회
	//countByXXX 시작시 XXX에 대한 레코드 수 조회
	
	List<Notice> findAll();
}
