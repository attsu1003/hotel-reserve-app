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

	public Date getCheckInDay() {
		return checkInDay;
	}

	public Date getCheckOutDay() {
		return checkOutDay;
	}

	public int getNumberOfGuest() {
		return numberOfGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public String getMemberId() {
		return memberId;
	}
}
