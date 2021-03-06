package com.example.demo.domain.reserve;

import java.util.Date;

import com.example.demo.domain.reserve.calculation.TotalHotelFee;

public class Reserve {
	private ReserveId reserveId;
	private Plan plan;
	private Date checkInDay;
	private Date checkOutDay;
	private int numberOfAdultGuest;
	private int numberOfChildrenGuest;
	private TotalHotelFee totalHotelFee;
	private String memberId;

	public Reserve(ReserveId reserveId, Plan plan, Date checkInDay, Date checkOutDay, int numberOfAdultGuest,
			int numberOfChildrenGuest, TotalHotelFee totalHotelFee, String memberId) {
		super();
		this.reserveId = reserveId;
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.numberOfAdultGuest = numberOfAdultGuest;
		this.numberOfChildrenGuest = numberOfChildrenGuest;
		this.totalHotelFee = totalHotelFee;
	}

	public Reserve(Plan plan, Date checkInDay, Date checkOutDay, int numberOfAdultGuest, int numberOfChildrenGuest,
			TotalHotelFee totalHotelFee, String memberId) {
		super();
		this.reserveId = new ReserveId();
		this.plan = plan;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.numberOfAdultGuest = numberOfAdultGuest;
		this.numberOfChildrenGuest = numberOfChildrenGuest;
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

	public int getNumberOfAdultGuest() {
		return numberOfAdultGuest;
	}

	public int getNumberOfChildrenGuest() {
		return numberOfChildrenGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee.getTotalFeeAmount().getAmountValue();
	}

	public String getMemberId() {
		return memberId;
	}
}
