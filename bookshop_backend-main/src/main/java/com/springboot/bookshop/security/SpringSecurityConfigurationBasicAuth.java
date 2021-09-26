package com.springboot.bookshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors()
		.and()
		.authorizeRequests()
			// user defender filter
		.antMatchers(HttpMethod.GET, "/api/admin").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.GET, "/api/finduser/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.GET, "/api/cart/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.POST, "/api/cartitems").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.PUT, "/api/cartitems").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.DELETE, "/api/cartitems/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.DELETE, "/api/cartitems/deletebyuserid/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.GET, "/api/ordertablelist").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.POST, "/api/ordertables").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.PUT, "/api/ordertables").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.PUT, "/api/account/verify/**").hasAuthority("ROLE_USER")
		.and()
		.httpBasic()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
}
