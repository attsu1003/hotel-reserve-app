package com.example.demo.web.form;

import javax.validation.constraints.Size;

import com.example.demo.web.validation.ConfirmChangePassword;

@ConfirmChangePassword(password = "password", newPassword = "newPassword")
public class ChangePasswordForm {

	@Size(min = 8, max = 16)
	private String password;
	@Size(min = 8, max = 16)
	private String newPassword;
	@Size(min = 8, max = 16)
	private String newConfirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}

	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
	}

}
