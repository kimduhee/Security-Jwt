package com.namanok.common.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.namanok.entity.User;

import lombok.Getter;


/**
 * UserDetails: ������� ������ ��� interface
 */
@Getter
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -6005048232838207154L;
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	/**
	 * ������ ���� ����� ����
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
	 * ������ ��й�ȣ�� ����
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * ������ ������ ���� ����
	 */
	@Override
	public String getUsername() {
		return user.getUserId();
	}

	/**
	 * ������ ���� ���� ����
	 * true: ���� �ȵ�
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * ������ ��� ���� ����
	 * true: ����� ����
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * ��й�ȣ ���� ���� ����
	 * true: ���� �ȵ�
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * ������ Ȱ��ȭ ���� ����
	 * true: Ȱ��ȭ ��
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
