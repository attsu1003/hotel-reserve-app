package com.example.demo.domain.model;

import java.util.Date;

public class ReserveModel {
	private String id;

	private Date checkindate;

	private Date checkoutdate;

	private String memberid;

	public ReserveModel(String id, Date checkInDay, Date checkOutDay, String memberid) {
		super();
		this.id = id;
		this.checkindate = checkInDay;
		this.checkoutdate = checkOutDay;
		this.memberid = memberid;
	}

	public ReserveModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCheckInDay() {
		return checkindate;
	}

	public void setCheckInDay(Date checkInDay) {
		this.checkindate = checkInDay;
	}

	public Date getCheckOutDay() {
		return checkoutdate;
	}

	public void setCheckOutDay(Date checkOutDay) {
		this.checkoutdate = checkOutDay;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

}
