package com.example.demo.domain.reserve;

import java.util.Date;

public class Reserve {
	private ReserveId reserveId;
	private Plan plan;
	private Date checkInDay;
	private Date checkOutDay;
	private int NumberOfGuest;
	private TotalHotelFee totalHotelFee;
	private String memberId;

	public Reserve(Plan plan, Date checkInDay, Date checkOutDay, int numberOfGuest, TotalHotelFee totalHotelFee,
			String memberId) {
		super();
		this.reserveId = new ReserveId();
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		NumberOfGuest = numberOfGuest;
		this.totalHotelFee = totalHotelFee;
		this.memberId = memberId;
	}

	public String getReserveId() {
		return reserveId.getReserveId();
	}

	public String getPlan() {
		return plan.getPlanName();
	}

	public Date getCheckInDay() {
		return checkInDay;
	}

	public Date getCheckOutDay() {
		return checkOutDay;
	}

	public int getNumberOfGuest() {
		return NumberOfGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee.getTotalFeeAmount().value;
	}

	public String getMemberId() {
		return memberId;
	}
}
