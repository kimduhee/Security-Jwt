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
	 * 로그인 시도시 실행
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			
			//아이디와 패스워드가 입력되지 않았을때는 에러 처리
			if(user.getUserId() == null || "".equals(user.getUserId()) || user.getPassword() == null || "".equals(user.getPassword())) {
				request.setAttribute("exception", ErrorEnum.ERR0010);
				request.getRequestDispatcher("/exception").forward(request, response);
			}
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
			
			//UserDetailsService의 loadUserByUsername 실행
			Authentication authentication = authenticationManager.authenticate(token);
			return authentication;
			
		} catch (Exception e) {	//자격 증명 실패
			log.error("BadCredentialsException Exception =>{}", e);
			try {
				//로그인에 실패하였습니다. 를 request에 담아서 /exception 경로로 forward 시킴
				request.setAttribute("exception", ErrorEnum.ERR0008);
				request.getRequestDispatcher("/exception").forward(request, response);
			} catch (Exception e1) {
				log.error("attemptAuthentication Exception : {} ", e1);
				/* forward 마저도 실패하면 아래처럼 여기서 다이렉트 처리 고려
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
	 * 로그인 인증 정상적으로 처리후 실행
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		log.info("인증 성공 !!");
		
		CustomUserDetails principalDetails = (CustomUserDetails)authResult.getPrincipal();
		
		//JWT Token 생성
		String jwtToken = JwtUtil.createJWT(principalDetails.getUser().getUserId(), secretKey);
		
		response.addHeader("Authorization", "Bearer " + jwtToken);
	}
}
