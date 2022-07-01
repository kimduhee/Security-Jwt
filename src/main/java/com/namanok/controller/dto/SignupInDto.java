package com.namanok.controller.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ȸ������ �Է� DTO
 */
@Data
public class SignupInDto {
	
	@NotEmpty(message = "���̵�� �ʼ��Է°� �Դϴ�.")
	@ApiModelProperty(value="���̵�", example="namanok", required=true)
	private String userId;		//���̵�
	
	@NotEmpty(message = "�н������ �ʼ��Է°� �Դϴ�.")
	@ApiModelProperty(value="�н�����", example="1234", required=true)
	private String password;	//�н�����
	
	@NotEmpty(message = "�̸��� �ʼ��Է°� �Դϴ�.")
	@ApiModelProperty(value="�̸�", example="�����", required=true)
	private String name;		//�̸�
}
