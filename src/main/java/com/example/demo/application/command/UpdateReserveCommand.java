package com.example.demo.application.command;

import java.util.Date;

import com.example.demo.domain.reserve.Plan;

public class UpdateReserveCommand implements Command {

	private String reserveId;
	private Plan plan;
	private Date checkInDay;
	private Date checkOutDay;
	private int numberOfGuest;
	private int totalHotelFee;
	private String memberId;

	public UpdateReserveCommand(String reserveId) {
		super();
		this.reserveId = reserveId;
	}

	public UpdateReserveCommand(String reserveId, Plan plan, Date checkInDay, Date checkOutDay, int numberOfGuest,
			int totalHotelFee, String memberId) {
		super();
		this.reserveId = reserveId;
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.numberOfGuest = numberOfGuest;
		this.totalHotelFee = totalHotelFee;
		this.memberId = memberId;
	}

	public String getReserveId() {
		return reserveId;
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
