package com.example.demo.domain.reserve;

import java.util.Date;

public class ReserveCondition {

	private String memberId;
	private Date checkInDay;
	private Date checkOutDay;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getCheckInDay() {
		return checkInDay;
	}

	public void setCheckInDay(Date checkInDay) {
		this.checkInDay = checkInDay;
	}

	public Date getCheckOutDay() {
		return checkOutDay;
	}

	public void setCheckOutDay(Date checkOutDay) {
		this.checkOutDay = checkOutDay;
	}

	public ReserveCondition(String memberId, Date checkInDay, Date checkOutDay) {
		super();
		this.memberId = memberId;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
	}
}
