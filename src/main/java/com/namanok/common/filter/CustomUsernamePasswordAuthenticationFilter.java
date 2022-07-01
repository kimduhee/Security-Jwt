package com.namanok.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namanok.common.authentication.CustomUserDetails;
import com.namanok.common.exception.ErrorEnum;
import com.namanok.common.util.JwtUtil;
import com.namanok.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final String secretKey;
	private final AuthenticationManager authenticationManager;
	
	public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey) {
		this.authenticationManager = authenticationManager;
		this.secretKey = secretKey;
	}

	/**
	 * �α��� �õ��� ����
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			
			//���̵�� �н����尡 �Էµ��� �ʾ������� ���� ó��
			if(user.getUserId() == null || "".equals(user.getUserId()) || user.getPassword() == null || "".equals(user.getPassword())) {
				request.setAttribute("exception", ErrorEnum.ERR0010);
				request.getRequestDispatcher("/exception").forward(request, response);
			}
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
			
			//UserDetailsService�� loadUserByUsername ����
			Authentication authentication = authenticationManager.authenticate(token);
			return authentication;
			
		} catch (Exception e) {	//�ڰ� ���� ����
			log.error("BadCredentialsException Exception =>{}", e);
			try {
				//�α��ο� �����Ͽ����ϴ�. �� request�� ��Ƽ� /exception ��η� forward ��Ŵ
				request.setAttribute("exception", ErrorEnum.ERR0008);
				request.getRequestDispatcher("/exception").forward(request, response);
			} catch (Exception e1) {
				log.error("attemptAuthentication Exception : {} ", e1);
				/* forward ������ �����ϸ� �Ʒ�ó�� ���⼭ ���̷�Ʈ ó�� ���
				ErrorEnum errorEnum = ErrorEnum.ERR0008;
				
				response.setContentType("application/json;charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_OK);

				JSONObject responseJson = new JSONObject();
				responseJson.put("timestamp", LocalDateTime.now());
				responseJson.put("status", "fail");
				responseJson.put("data", "");

				JSONObject errorJson = new JSONObject();
				errorJson.put("code", errorEnum.getCode());
				errorJson.put("message", errorEnum.getMessage());
		  
				responseJson.put("errorJson", errorJson);
				try {
					response.getWriter().print(responseJson);
				} catch (IOException e2) {
					log.error("attemptAuthentication Exception : {} ", e2);
				}	
				*/
			}
			
		}
		
		return null;
	}
	
	/**
	 * �α��� ���� ���������� ó���� ����
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		log.info("���� ���� !!");
		
		CustomUserDetails principalDetails = (CustomUserDetails)authResult.getPrincipal();
		
		//JWT Token ����
		String jwtToken = JwtUtil.createJWT(principalDetails.getUser().getUserId(), secretKey);
		
		response.addHeader("Authorization", "Bearer " + jwtToken);
	}
}
