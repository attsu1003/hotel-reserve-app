package com.example.demo.application.command;

import java.util.Date;

import com.example.demo.domain.reserve.Plan;

public class UpdateReserveCommand implements Command {

	private String reserveId;
	private Plan plan;
	private Date checkInDay;
	private Date checkOutDay;
	private int numberOfAdultGuest;
	private int numberOfChildrenGuest;
	private int totalHotelFee;
	private String memberId;

	public UpdateReserveCommand(String reserveId) {
		super();
		this.reserveId = reserveId;
	}

	public UpdateReserveCommand(String reserveId, Plan plan, Date checkInDay, Date checkOutDay, int numberOfAdultGuest,
			int numberOfChildrenGuest, int totalHotelFee, String memberId) {
		super();
		this.reserveId = reserveId;
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.numberOfAdultGuest = numberOfAdultGuest;
		this.numberOfChildrenGuest = numberOfChildrenGuest;
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

	public int getNumberOfAdultGuest() {
		return numberOfAdultGuest;
	}

	public int getNumberOfChildrenGuest() {
		return numberOfChildrenGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public String getMemberId() {
		return memberId;
	}
}
