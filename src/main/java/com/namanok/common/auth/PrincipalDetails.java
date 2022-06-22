package com.namanok.common.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.namanok.model.UserEntity;

/**
 * UserDetails: 사용자의 정보를 담는 interface
 * 
 * @author namanok
 *
 */
public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID = 838160654911836036L;
	
	private UserEntity userEntity;
	
	public PrincipalDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	/**
	 * 계정의 권한 목록을 리턴
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		userEntity.getRoleList().forEach(r -> {
			authorities.add(()->r);
		});
		return null;
	}
	
	/**
	 * 계정의 비밀번호를 리턴
	 */
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	/**
	 * 계정의 고유한 값을 리턴
	 */
	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

	/**
	 * 계정의 만료 여부 리턴
	 * true: 만료 안됨
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 계정의 잠김 여부 리턴
	 * true: 잠기지 않음
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 비밀번호 만료 여부 리턴
	 * true: 만료 안됨
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 계정의 활성화 여부 리턴
	 * true: 활성화 됨
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
