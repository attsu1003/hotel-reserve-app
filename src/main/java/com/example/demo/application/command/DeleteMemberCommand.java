package com.example.demo.application.command;

public class DeleteMemberCommand implements Command {

	private String id;
	private String password;

	public DeleteMemberCommand(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}
