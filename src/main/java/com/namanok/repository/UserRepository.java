package com.namanok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namanok.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
	//finByXXX ���۽� XXX�� ���� ������ ��ȸ
	//countByXXX ���۽� XXX�� ���� ���ڵ� �� ��ȸ
	public User findByUserId(String userId);
}
