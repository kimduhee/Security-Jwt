package com.namanok.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name="\"USER\"")	//h2 ��� �̿� ���� ""�� name�� ������ ��� ��
public class User {
	
	@Id
	private String userId;	//���̵�
	
	@Column(name = "password", nullable = false)
	private String password;//�н�����
	
	@Column(name = "name", nullable = false)
	private String name;	//�̸�

	@Column(name = "roles", nullable = false)
	private String roles;	//����
	
	public List<String> getRoleList() {
		if(roles != null) {
			if(this.roles.length() > 0) {
				return Arrays.asList(this.roles.split(","));
			}
		}

		return new ArrayList<>();
	}
}
