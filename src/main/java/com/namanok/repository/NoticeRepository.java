package com.namanok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namanok.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	//finByXXX ���۽� XXX�� ���� ������ ��ȸ
	//countByXXX ���۽� XXX�� ���� ���ڵ� �� ��ȸ
	
}
