package com.namanok.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="\"USER\"")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String roles;
	
	public List<String> getRoleList() {
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		
		return new ArrayList<>();
	}
}
