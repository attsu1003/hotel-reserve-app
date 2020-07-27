package com.example.demo.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginModel{
	
	
	@NotBlank
	private String username;
	
	@NotNull
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
