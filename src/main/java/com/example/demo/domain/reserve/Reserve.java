package com.example.demo.domain.reserve;

import java.util.Date;

public class Reserve {
	private ReserveId reserveId;
	private ReserveCondition reserveCondition;
	private TotalHotelFee totalHotelFee;
	private int NumberOfGuest;
	private Plan plan;

	public Reserve(ReserveCondition reserveCondition, TotalHotelFee totalHotelFee) {
		this.reserveId = new ReserveId();
		this.reserveCondition = reserveCondition;
		this.totalHotelFee = totalHotelFee;
	}

	public Reserve(ReserveCondition reserveCondition) {
		this.reserveId = new ReserveId();
		this.reserveCondition = reserveCondition;
	}

	public String getReserveId() {
		return reserveId.getReserveId();
	}

	public Date getCheckInDay() {
		return reserveCondition.getCheckInDay();
	}

	public Date getCheckOutDay() {
		return reserveCondition.getCheckOutDay();
	}

	public String getMemberId() {
		return reserveCondition.getMemberId();
	}

}
