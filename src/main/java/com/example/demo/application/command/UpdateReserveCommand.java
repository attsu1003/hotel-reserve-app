package com.example.demo.application.command;

public class UpdateReserveCommand implements Command {

	private String reserveId;

	public UpdateReserveCommand(String reserveId) {
		super();
		this.reserveId = reserveId;
	}

	public String getReserveId() {
		return reserveId;
	}
}
