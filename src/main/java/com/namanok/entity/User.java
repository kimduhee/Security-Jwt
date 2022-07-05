package com.namanok.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name="\"USER\"")
public class User {
	
	@Id
	@Column(name = "userId", nullable = false, length = 10)
	private String userId;	//���̵�
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;//�н�����
	
	@Column(name = "name", nullable = false, length = 10)
	private String name;	//�̸�

	@CreationTimestamp
	private Timestamp joinDate;	//������
	
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
