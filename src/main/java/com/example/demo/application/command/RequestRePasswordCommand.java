package com.example.demo.application.command;

public class RequestRePasswordCommand implements Command {

	private String mailAddress;

	public RequestRePasswordCommand(String mailAddress) {
		super();
		this.mailAddress = mailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}
}