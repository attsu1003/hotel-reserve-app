package com.example.demo.domain.reserve;

import java.util.Date;

public class ReserveCondition {

	private Date checkInDay;
	private Date checkOutDay;
	private String memberId;

	public ReserveCondition(Date checkInDay, Date checkOutDay, String memberId) {
		super();
		this.memberId = memberId;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
	}

	public Date getCheckInDay() {
		return checkInDay;
	}

	public Date getCheckOutDay() {
		return checkOutDay;
	}

	public String getMemberId() {
		return memberId;
	}
}
