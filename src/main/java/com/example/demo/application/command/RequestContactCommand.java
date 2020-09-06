package com.example.demo.application.command;

public class RequestContactCommand implements Command {

	private String name;
	private String mailAddress;
	private String category;
	private String contents;

	public RequestContactCommand(String name, String mailAddress, String category, String contents) {
		super();
		this.name = name;
		this.mailAddress = mailAddress;
		this.category = category;
		this.contents = contents;
	}

	public String getName() {
		return name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getCategory() {
		return category;
	}

	public String getContents() {
		return contents;
	}

}
