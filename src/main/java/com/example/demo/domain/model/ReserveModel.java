package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ReserveModel {
	private String id;
	
	
	private Date checkInDay;
	
	
	private Date checkOutDay;

	private String memberid;

	public ReserveModel(String id, Date checkInDay, Date checkOutDay, String memberid) {
		super();
		this.id = id;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
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

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

}
