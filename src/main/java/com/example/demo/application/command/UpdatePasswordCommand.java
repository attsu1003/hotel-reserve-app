package com.example.demo.application.command;

public class UpdatePasswordCommand implements Command {

	private String password;
	private String newPassword;
	private String newConfirmPassword;
	private String mailAddress;

	public UpdatePasswordCommand(String password, String newPassword, String newConfirmPassword, String mailAddress) {
		super();
		this.password = password;
		this.newPassword = newPassword;
		this.newConfirmPassword = newConfirmPassword;
		this.mailAddress = mailAddress;
	}

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

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub

	}

}
