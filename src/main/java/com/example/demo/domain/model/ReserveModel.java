package com.example.demo.domain.model;

import java.util.Date;

import com.example.demo.web.validation.ConfirmDayBeforeAndAfter;

@ConfirmDayBeforeAndAfter(checkInDay = "checkInDay", checkOutDay = "checkOutDay")
public class ReserveModel {

	private Date checkInDay;

	private Date checkOutDay;

	private String memberid;

	public ReserveModel(Date checkInDay, Date checkOutDay, String memberid) {
		super();
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.memberid = memberid;
	}

	public ReserveModel() {
		super();
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

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

}
