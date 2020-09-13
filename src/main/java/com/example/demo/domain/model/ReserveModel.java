package com.example.demo.domain.model;

import java.util.Date;

public class ReserveModel {

	private String id;
	private String plan;
	private Date checkindate;
	private Date checkoutdate;
	private int numberOfAdultGuest;
	private int numberOfChildrenGuest;
	private int totalHotelFee;
	private String memberid;

	public ReserveModel() {
		super();
	}

	public ReserveModel(String id, String plan, Date checkindate, Date checkoutdate, int numberOfAdultGuest,
			int numberOfChildrenGuest, int totalHotelFee, String memberid) {
		super();
		this.id = id;
		this.plan = plan;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
		this.numberOfAdultGuest = numberOfAdultGuest;
		this.numberOfChildrenGuest = numberOfChildrenGuest;
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

	public int getNumberOfAdultGuest() {
		return numberOfAdultGuest;
	}

	public int getNumberOfChildrenGuest() {
		return numberOfChildrenGuest;
	}

	public int getTotalHotelFee() {
		return totalHotelFee;
	}

	public String getMemberid() {
		return memberid;
	}
}
