package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

		http.authorizeRequests().antMatchers("/js/**", "/css/**","/createMember","/createdMember").permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").loginProcessingUrl("/authenticate")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/menu", true)
        .permitAll();

		http.csrf().disable().authorizeRequests()
        
        .anyRequest().authenticated();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginApplicationServiceImpl)
            .passwordEncoder(passwordEncoder());
    }

}