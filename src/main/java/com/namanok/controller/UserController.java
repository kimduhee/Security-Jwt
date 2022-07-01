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
	 * ȸ������
	 * 
	 * @param input
	 * @return
	 */
	@ApiOperation(value="ȸ������", notes="ȸ�������� ó���ϴ� API�Դϴ�.")
	@PostMapping(value="/signup")
	public ResponseEntity<Object> signup(@RequestBody @Validated SignupInDto input) {
		userService.signup(input);
		CommResponse response = CommResponse.builder()
				.httpStatus(HttpStatus.OK)
				.status("success")
				.responseObj("ȸ�������� ���� ó���Ǿ����ϴ�.")
				.build();
		return response.generateResponse();
	}
	
	/**
	 * ���̷α���
	 * : swagger�� �����Ű�� ���� ���̷� ����
	 *   
	 * @param input
	 * @return
	 */
	@ApiOperation(value="�α���", notes="�α����� ó���ϴ� API")
	@PostMapping(value="/login")
	public String dummyLogin(@RequestBody LoginInDto input)  {
		return "";
	}
}
