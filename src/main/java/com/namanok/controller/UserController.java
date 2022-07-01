package com.namanok.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.common.response.CommResponse;
import com.namanok.controller.dto.LoginInDto;
import com.namanok.controller.dto.SignupInDto;
import com.namanok.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	private UserService userService;
	
	private UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 회원가입
	 * 
	 * @param input
	 * @return
	 */
	@ApiOperation(value="회원가입", notes="회원가입을 처리하는 API입니다.")
	@PostMapping(value="/signup")
	public ResponseEntity<Object> signup(@RequestBody @Validated SignupInDto input) {
		userService.signup(input);
		CommResponse response = CommResponse.builder()
				.httpStatus(HttpStatus.OK)
				.status("success")
				.responseObj("회원가입이 정상 처리되었습니다.")
				.build();
		return response.generateResponse();
	}
	
	/**
	 * 더미로그인
	 * : swagger에 노출시키기 위해 더미로 만듬
	 *   
	 * @param input
	 * @return
	 */
	@ApiOperation(value="로그인", notes="로그인을 처리하는 API")
	@PostMapping(value="/login")
	public String dummyLogin(@RequestBody LoginInDto input)  {
		return "";
	}
}
