package com.namanok.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.namanok.common.exception.CustomAuthenticationEntryPoint;
import com.namanok.common.filter.CustomUsernamePasswordAuthenticationFilter;
import com.namanok.common.filter.CustomBasicAuthenticationFilter;
import com.namanok.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	
	private final UserRepository userRepository;
	
	@Value("${jwt.secret-key}")
	private String secretKey;

	/**
	 * H2 web console ������ ���� ������
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/h2-console/**");	//H2 �ܼ� ���� �㰡
//			.antMatchers("/swagger-resources/**", "/swagger-ui.html", "/swagger/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		// ��������ť��Ƽ�� ������ ���������ʰ� ���������� ��������� ����(JWT ��ū����� ����ϱ� ����)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.cors()
			.and()
			.exceptionHandling()
			
			//�������� ���� ���� ó��
			.authenticationEntryPoint(authenticationEntryPoint())
			.and()
			.formLogin().disable()	//���α��� ������
			.httpBasic().disable()	//header id:password ��� ������
			.authorizeHttpRequests()
//			.antMatchers("/�����ʿ��ּ�").hasAuthority("ROLE_USER")
			.anyRequest().permitAll();
		/**
		 * UsernamePasswordAuthenticationFilter
		 * -username / password �� �α����� �Ϸ��� �ϴ��� üũ�Ͽ� ������ �Ǹ� Authentication�� �ο���
		 */
		http.addFilter(jwtAuthorizationFilter());
		/**
		 * BasicAuthenticationFilter
		 * -���� �ʿ��� �ּҿ�û�� ó�� ����
		 */
		http.addFilter(new CustomBasicAuthenticationFilter(authenticationManager(), userRepository, secretKey));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * �������� ���� ����ڿ� ���� ó��
	 * 
	 * @return
	 */
	@Bean
	public CustomAuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	/**
	 * security ��ü �α��� �ּ��� '/login'�� �����ϰ��� �Ҷ� ���
	 * @return
	 * @throws Exception
	 */
	public CustomUsernamePasswordAuthenticationFilter jwtAuthorizationFilter() throws Exception {
	    CustomUsernamePasswordAuthenticationFilter jwtAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager(), secretKey);
	    jwtAuthenticationFilter.setFilterProcessesUrl("/login");	//�� �ּҰ� �α��� ��û �ּҰ� �ȴ�.
	    return jwtAuthenticationFilter;
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");	//��� IP ���� ���
        configuration.addAllowedHeader("*");	//��� Header ���� ���
        configuration.addAllowedMethod("*");	//��� method ���� ���
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
