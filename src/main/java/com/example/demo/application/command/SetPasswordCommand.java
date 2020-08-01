package com.example.demo.application.command;

public class SetPasswordCommand implements Command {

	private String password;
	private String confirmPassword;

	public SetPasswordCommand(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub

	}

}