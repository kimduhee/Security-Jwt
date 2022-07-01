package com.namanok.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.namanok.common.exception.ErrorEnum;
import com.namanok.common.exception.BizException;
import com.namanok.controller.dto.SignupInDto;
import com.namanok.entity.User;
import com.namanok.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; 
	
	/**
	 * 회원가입
	 * 
	 * @param input
	 */
	public User signup(SignupInDto inDto) {

		User input = new User();
		BeanUtils.copyProperties(inDto, input);
		
		//아이디가 이미 사용중인지 확인
		User user = userRepository.findByUserId(input.getUserId());
		if(user != null) {
			//이미 존재하는 아이디입니다.
			throw new BizException(ErrorEnum.ERR0002);
		}

		input.setPassword(passwordEncoder.encode(input.getPassword()));
		input.setRoles("ROLE_USER");
		
		return userRepository.save(input);
	}
	
}
