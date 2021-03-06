package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.application.LoginApplicationServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginApplicationServiceImpl loginApplicationServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/js/**", "/common/css/**", "/common/image/**", "/requestCreateMember", "/createMember",
						"/webjars/**", "/createdMember", "/requestRePassword", "/setPassword", "/setPasswordComplete")
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").loginProcessingUrl("/authenticate").usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/menu", true).permitAll();

		http.csrf().disable().authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(loginApplicationServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		auth.authenticationProvider(daoAuthenticationProvider);
	}

}