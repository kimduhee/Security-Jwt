package com.namanok.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.namanok.common.authentication.CustomUserDetails;
import com.namanok.common.exception.ErrorEnum;
import com.namanok.common.util.JwtUtil;
import com.namanok.entity.User;
import com.namanok.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * BasicAuthenticationFilter 구현
 * -인증 필요한 주소 요청시 처리
 */
@Slf4j
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter{

	private final String secretKey;
	
	private UserRepository userRepository;
	
	public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, String secretKey) {
		super(authenticationManager);
		this.userRepository = userRepository;
		this.secretKey = secretKey;
	}

	/**
	 * 인증 필요한 주소요청시 호출
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String jwtHeader = request.getHeader("Authorization");
		
		//header에 토큰값이 있는지 확인
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		
		//Bearer를 제외한 순수 JWT 토큰값 추출
		jwtHeader = jwtHeader.substring(7);
		
		String userId = "";
		try {
			//JWT Token 검증 및 userId 획득
			userId = JwtUtil.jwtVerifyAndGetUserId(jwtHeader, secretKey);

		} catch(TokenExpiredException e) {
			if(log.isErrorEnabled()) log.error("token verify : {}", e);
			request.setAttribute("exception", ErrorEnum.ERR0005);
			chain.doFilter(request, response);
			return;
		}
		
		if(log.isDebugEnabled()) log.debug("JWT token 인증결과 추출 userId : {}", userId); 
			
		if(userId != null) {
			User user = userRepository.findByUserId(userId);
			if(user != null) {
				CustomUserDetails principalDetails = new CustomUserDetails(user);
				Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
				//Security 세션에 authentication 저장
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(request, response);
			} else {
				//회원정보가 존재하지 않습니다.
				request.setAttribute("exception", ErrorEnum.ERR0004);
				chain.doFilter(request, response);
				return;
			}
		}

	}
}
