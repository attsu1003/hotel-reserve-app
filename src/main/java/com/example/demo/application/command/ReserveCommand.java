package com.example.demo.application.command;

import java.util.Date;

import com.example.demo.domain.reserve.Plan;

public class ReserveCommand implements Command {

	private Plan plan;
	private Date checkInDay;
	private Date checkOutDay;
	private int numberOfGuest;
	private int totalHotelFee;
	private String memberId;

	public ReserveCommand(Plan plan, Date checkInDay, Date checkOutDay, int numberOfGuest, int totalHotelFee,
			String memberId) {
		super();
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.numberOfGuest = numberOfGuest;
		this.totalHotelFee = totalHotelFee;
		this.memberId = memberId;
	}

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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
