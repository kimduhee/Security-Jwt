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
	 * H2 web console 접근을 위해 설정함
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/h2-console/**");	//H2 콘솔 접근 허가
//			.antMatchers("/swagger-resources/**", "/swagger-ui.html", "/swagger/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		// 스프링시큐리티가 세션을 생성하지않고 기존세션을 사용하지도 않음(JWT 토큰방식을 사용하기 위함)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.cors()
			.and()
			.exceptionHandling()
			
			//비인증에 대한 에러 처리
			.authenticationEntryPoint(authenticationEntryPoint())
			.and()
			.formLogin().disable()	//폼로그인 사용안함
			.httpBasic().disable()	//header id:password 방식 사용안함
			.authorizeHttpRequests()
//			.antMatchers("/권한필요주소").hasAuthority("ROLE_USER")
			.anyRequest().permitAll();
		/**
		 * UsernamePasswordAuthenticationFilter
		 * -username / password 로 로그인을 하려고 하는지 체크하여 승인이 되면 Authentication을 부여함
		 */
		http.addFilter(jwtAuthorizationFilter());
		/**
		 * BasicAuthenticationFilter
		 * -인증 필요한 주소요청시 처리 필터
		 */
		http.addFilter(new CustomBasicAuthenticationFilter(authenticationManager(), userRepository, secretKey));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인증되지 않은 사용자에 대한 처리
	 * 
	 * @return
	 */
	@Bean
	public CustomAuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	/**
	 * security 자체 로그인 주소인 '/login'을 변경하고자 할때 사용
	 * @return
	 * @throws Exception
	 */
	public CustomUsernamePasswordAuthenticationFilter jwtAuthorizationFilter() throws Exception {
	    CustomUsernamePasswordAuthenticationFilter jwtAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager(), secretKey);
	    jwtAuthenticationFilter.setFilterProcessesUrl("/login");	//이 주소가 로그인 요청 주소가 된다.
	    return jwtAuthenticationFilter;
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");	//모든 IP 응답 허용
        configuration.addAllowedHeader("*");	//모든 Header 응답 허용
        configuration.addAllowedMethod("*");	//모든 method 응답 허용
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
