package com.namanok.common.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.namanok.model.UserEntity;
import com.namanok.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * UserDetailsService: Spring Security���� ������ ������ �������� interface
 * login ��û�� ���� ó��
 *
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByusername(username);
		return new PrincipalDetails(userEntity);
	}
}
