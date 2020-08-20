package com.example.demo.web.form;

import java.util.Date;

import com.example.demo.web.validation.ConfirmDayBeforeAndAfter;

@ConfirmDayBeforeAndAfter(checkInDay = "checkInDay", checkOutDay = "checkOutDay")
public class ReserveConfirmForm {

	private Date checkInDay;

	private Date checkOutDay;

	private String memberid;

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