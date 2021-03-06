package com.example.demo.web.form;

import java.util.Date;

import com.example.demo.domain.reserve.Plan;

public class ReserveForm {

	private Plan plan;

	private Date checkInDay;

	private Date checkOutDay;

	private String memberid;

	private int numberOfAdultGuest;

	private int numberOfChildrenGuest;

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

	public int getNumberOfAdultGuest() {
		return numberOfAdultGuest;
	}

	public void setNumberOfAdultGuest(int numberOfAdultGuest) {
		this.numberOfAdultGuest = numberOfAdultGuest;
	}

	public int getNumberOfChildrenGuest() {
		return numberOfChildrenGuest;
	}

	public void setNumberOfChildrenGuest(int numberOfChildrenGuest) {
		this.numberOfChildrenGuest = numberOfChildrenGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public void setTotalHotelFee(int totalHotelFee) {
		this.totalHotelFee = totalHotelFee;
	}

}
