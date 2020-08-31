package com.example.demo.application.command;

public class RequestCreateMemberCommand implements Command {

	private String mailAddress;

	public RequestCreateMemberCommand(String mailAddress) {
		super();
		this.mailAddress = mailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}
}
