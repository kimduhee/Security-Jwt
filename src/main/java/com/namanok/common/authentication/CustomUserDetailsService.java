package com.namanok.common.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.namanok.entity.User;
import com.namanok.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService: Spring Security���� ������ ������ �������� interface
 * login ��û�� ���� ó��
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	/**
	 * CustomUsernamePasswordAuthenticationFilter �� authenticationManager.authenticate(token) ����� ȣ���
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserId(username);
		if(user == null) {
			// UsernameNotFoundException�� throw �ϸ�
			// CustomUsernamePasswordAuthenticationFilter �� attemptAuthentication���� Exception ó��
			throw new UsernameNotFoundException("");
		}
		
		return new CustomUserDetails(user);
	}

}
