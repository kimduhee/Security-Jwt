package com.namanok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namanok.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
	//finByXXX 시작시 XXX에 대한 조건절 조회
	//countByXXX 시작시 XXX에 대한 레코드 수 조회
	public User findByUserId(String userId);
}
