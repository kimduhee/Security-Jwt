package com.namanok.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * �α��� �Է� DTO
 *
 * DESC
 * �����δ� ������ �ʰ� ���� swagger�� ���� ������ DTO
 * 
 * �ΰ�����(�н�����)�� ���� �Ʒ�ó�� �����ϸ� �ȵ�����
 * swagger �׽�Ʈ�� ���� �ӽ÷� ������ ����
 */
@Data
public class LoginInDto {
	
	@ApiModelProperty(value="���̵�", example="namanok", required=true)
	private String userId;		//���̵�
	
	@ApiModelProperty(value="�н�����", example="1234", required=true)
	private String password;	//�н�����
}
