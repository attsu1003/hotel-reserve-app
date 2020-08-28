package com.example.demo.application.command;

import java.util.Date;

import com.example.demo.domain.reserve.ReserveCondition;

public class ReserveCommand implements Command {

	private ReserveCondition reserveCondition;

	public ReserveCommand(ReserveCondition reserveCondition) {
		super();
		this.reserveCondition = reserveCondition;
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
}
