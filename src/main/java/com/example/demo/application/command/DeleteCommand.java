package com.example.demo.application.command;

import com.example.demo.domain.reserve.ReserveId;

public class DeleteCommand implements Command {
	
	private ReserveId reserveId;	
	
	public DeleteCommand(ReserveId reserveId) {
		super();
		this.reserveId = reserveId;
	}	

	public ReserveId getReserveId() {
		return reserveId;
	}

	public void setReserveId(ReserveId reserveId) {
		this.reserveId = reserveId;
	}

	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub

	}

}
