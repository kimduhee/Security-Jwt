package com.namanok.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.namanok.model.UserEntity;
import com.namanok.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping(value="/join")
	public String join(@RequestBody UserEntity user) {
		userService.join(user);
		return "{\"result\":\"success\"}";
	}
	
	@PostMapping(value="/login")
	public UserEntity getUser(@RequestBody UserEntity user) {
		return null;
	}
}
