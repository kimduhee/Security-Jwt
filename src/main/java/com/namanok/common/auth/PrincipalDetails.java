package com.namanok.common.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.namanok.model.UserEntity;

/**
 * UserDetails: ������� ������ ��� interface
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
	 * ������ ���� ����� ����
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
	 * ������ ��й�ȣ�� ����
	 */
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	/**
	 * ������ ������ ���� ����
	 */
	@Override
	public String getUsername() {
		return userEntity.getUsername();
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
