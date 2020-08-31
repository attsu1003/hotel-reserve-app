package com.example.demo.application.command;

public class ChangePasswordCommand implements Command {

	private String password;
	private String newPassword;
	private String newConfirmPassword;
	private String mailAddress;

	public ChangePasswordCommand(String password, String newPassword, String newConfirmPassword, String mailAddress) {
		super();
		this.password = password;
		this.newPassword = newPassword;
		this.newConfirmPassword = newConfirmPassword;
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}

	public String getMailAddress() {
		return mailAddress;
	}
}
