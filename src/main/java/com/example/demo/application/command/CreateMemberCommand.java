package com.example.demo.application.command;

public class CreateMemberCommand implements Command {
	
	private String username;
	private String password;

	public CreateMemberCommand(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub
		
	}

}
