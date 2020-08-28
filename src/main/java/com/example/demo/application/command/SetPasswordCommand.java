package com.example.demo.application.command;

public class SetPasswordCommand implements Command {

	private String password;
	private String confirmPassword;
	private String mailAddress;

	public SetPasswordCommand(String password, String confirmPassword, String mailAddress) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.mailAddress = mailAddress;
	}

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

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}