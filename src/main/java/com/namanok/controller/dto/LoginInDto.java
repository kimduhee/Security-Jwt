package com.namanok.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 로그인 입력 DTO
 *
 * DESC
 * 실제로는 사용되지 않고 단지 swagger를 위해 생성한 DTO
 * 
 * 민감정보(패스워드)는 원래 아래처럼 기입하면 안되지만
 * swagger 테스트를 위해 임시로 기입해 놓음
 */
@Data
public class LoginInDto {
	
	@ApiModelProperty(value="아이디", example="namanok", required=true)
	private String userId;		//아이디
	
	@ApiModelProperty(value="패스워드", example="1234", required=true)
	private String password;	//패스워드
}
