package com.example.demo.application.command;

public class ReferCommand implements Command{
	
	private String reserveId;

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
}
