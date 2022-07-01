package com.namanok.common.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ErrorEnum errorEnum = (ErrorEnum)request.getAttribute("exception");
		
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
		response.getWriter().print(responseJson);
	}
}
