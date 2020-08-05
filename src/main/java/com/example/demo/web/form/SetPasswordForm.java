package com.example.demo.web.form;

import javax.validation.constraints.Size;

public class SetPasswordForm {

	@Size(min = 8, max = 16)
	private String password;
	@Size(min = 8, max = 16)
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}