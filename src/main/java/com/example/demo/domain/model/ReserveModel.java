package com.example.demo.domain.model;

import java.util.Date;

public class ReserveModel {

	private String id;
	private String plan;
	private Date checkindate;
	private Date checkoutdate;
	private int numberOfGuest;
	private int totalHotelFee;
	private String memberid;

	public ReserveModel() {
		super();
	}

	public ReserveModel(String id, String plan, Date checkindate, Date checkoutdate, int numberOfGuest,
			int totalHotelFee, String memberid) {
		super();
		this.id = id;
		this.plan = plan;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
		this.numberOfGuest = numberOfGuest;
		this.totalHotelFee = totalHotelFee;
		this.memberid = memberid;
	}

	public String getId() {
		return id;
	}

	public String getPlan() {
		return plan;
	}

	public Date getCheckindate() {
		return checkindate;
	}

	public Date getCheckoutdate() {
		return checkoutdate;
	}

	public int getNumberOfGuest() {
		return numberOfGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public String getMemberid() {
		return memberid;
	}
}
