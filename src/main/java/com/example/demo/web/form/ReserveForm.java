package com.example.demo.web.form;

import java.util.Date;

import com.example.demo.domain.reserve.Plan;

public class ReserveForm {

	private Plan plan;

	private Date checkInDay;

	private Date checkOutDay;

	private String memberid;

	private int numberOfGuest;

	private int totalHotelFee;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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

	public int getNumberOfGuest() {
		return numberOfGuest;
	}

	public void setNumberOfGuest(int numberOfGuest) {
		this.numberOfGuest = numberOfGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public void setTotalHotelFee(int totalHotelFee) {
		this.totalHotelFee = totalHotelFee;
	}

}
