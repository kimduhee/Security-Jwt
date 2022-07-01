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
 * BasicAuthenticationFilter ����
 * -���� �ʿ��� �ּ� ��û�� ó��
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
	 * ���� �ʿ��� �ּҿ�û�� ȣ��
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String jwtHeader = request.getHeader("Authorization");
		
		//header�� ��ū���� �ִ��� Ȯ��
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		
		//Bearer�� ������ ���� JWT ��ū�� ����
		jwtHeader = jwtHeader.substring(7);
		
		String userId = "";
		try {
			//JWT Token ���� �� userId ȹ��
			userId = JwtUtil.jwtVerifyAndGetUserId(jwtHeader, secretKey);

		} catch(TokenExpiredException e) {
			if(log.isErrorEnabled()) log.error("token verify : {}", e);
			request.setAttribute("exception", ErrorEnum.ERR0005);
			chain.doFilter(request, response);
			return;
		}
		
		if(log.isDebugEnabled()) log.debug("JWT token ������� ���� userId : {}", userId); 
			
		if(userId != null) {
			User user = userRepository.findByUserId(userId);
			if(user != null) {
				CustomUserDetails principalDetails = new CustomUserDetails(user);
				Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
				//Security ���ǿ� authentication ����
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(request, response);
			} else {
				//ȸ�������� �������� �ʽ��ϴ�.
				request.setAttribute("exception", ErrorEnum.ERR0004);
				chain.doFilter(request, response);
				return;
			}
		}

	}
}
