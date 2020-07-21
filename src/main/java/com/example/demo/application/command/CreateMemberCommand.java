package com.example.demo.application.command;

public class CreateMemberCommand implements Command {
	
	private String memberId;
	private String password;
	
	

	public CreateMemberCommand(String memberId, String password) {
		super();
		this.memberId = memberId;
		this.password = password;
	}
	
	



	public String getMemberId() {
		return memberId;
	}





	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
