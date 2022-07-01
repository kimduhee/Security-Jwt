package com.namanok.controller.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 회원가입 입력 DTO
 */
@Data
public class SignupInDto {
	
	@NotEmpty(message = "아이디는 필수입력값 입니다.")
	@ApiModelProperty(value="아이디", example="namanok", required=true)
	private String userId;		//아이디
	
	@NotEmpty(message = "패스워드는 필수입력값 입니다.")
	@ApiModelProperty(value="패스워드", example="1234", required=true)
	private String password;	//패스워드
	
	@NotEmpty(message = "이름은 필수입력값 입니다.")
	@ApiModelProperty(value="이름", example="손흥민", required=true)
	private String name;		//이름
}
