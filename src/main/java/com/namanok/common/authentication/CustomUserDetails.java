package com.namanok.common.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.namanok.entity.User;

import lombok.Getter;


/**
 * UserDetails: 사용자의 정보를 담는 interface
 */
@Getter
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -6005048232838207154L;
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	/**
	 * 계정의 권한 목록을 리턴
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoleList().forEach(r->{
			authorities.add(()->r);
		});
		return authorities;
	}

	/**
	 * 계정의 비밀번호를 리턴
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * 계정의 고유한 값을 리턴
	 */
	@Override
	public String getUsername() {
		return user.getUserId();
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
