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
 * UserDetailsService: Spring Security에서 유저의 정보를 가져오는 interface
 * login 요청에 대한 처리
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	/**
	 * CustomUsernamePasswordAuthenticationFilter 의 authenticationManager.authenticate(token) 실행시 호출됨
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserId(username);
		if(user == null) {
			// UsernameNotFoundException를 throw 하면
			// CustomUsernamePasswordAuthenticationFilter 의 attemptAuthentication에서 Exception 처리
			throw new UsernameNotFoundException("");
		}
		
		return new CustomUserDetails(user);
	}

}
