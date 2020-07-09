package com.example.demo.application.command;

import java.util.Date;

import com.example.demo.domain.reserve.ReserveCondition;
import com.example.demo.domain.reserve.ReserveId;

public class ReserveCommand implements Command {

	private ReserveId reserveId;
	private ReserveCondition reserveCondition;

	public ReserveCommand(ReserveId reserveId, ReserveCondition reserveCondition) {
		super();
		this.reserveId = reserveId;
		this.reserveCondition = reserveCondition;
	}

	public ReserveId getReserveId() {
		return reserveId;
	}

	public void setReserveId(ReserveId reserveId) {
		this.reserveId = reserveId;
	}

	public ReserveCondition getReserveCondition() {
		return reserveCondition;
	}

	public void setReserveCondition(ReserveCondition reserveCondition) {
		this.reserveCondition = reserveCondition;
	}

	public Date getCheckInDay() {
		return reserveCondition.getCheckInDay();
	}

	public Date getCheckOutDay() {
		return reserveCondition.getCheckOutDay();
	}

	public String getMemberId() {
		return reserveCondition.getMemberId();
	}

	@Override
	public void execute(Command command) {
		// TODO Auto-generated method stub

	}

}
