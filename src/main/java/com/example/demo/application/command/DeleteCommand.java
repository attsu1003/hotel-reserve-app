package com.example.demo.application.command;

public class DeleteCommand implements Command {

	private String reserveId;

	public DeleteCommand(String reserveId) {
		super();
		this.reserveId = reserveId;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub

	}

}
