package com.namanok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namanok.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	//finByXXX ���۽� XXX�� ���� ������ ��ȸ
	//countByXXX ���۽� XXX�� ���� ���ڵ� �� ��ȸ
	public UserEntity findByusername(String username);
}
