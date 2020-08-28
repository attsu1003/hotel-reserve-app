package com.example.demo.application.command;

public class RequestDeleteMemberCommand implements Command {

	private String mailAddress;

	public RequestDeleteMemberCommand(String mailAddress) {
		super();
		this.mailAddress = mailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}
