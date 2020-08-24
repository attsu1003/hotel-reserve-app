package com.example.demo.domain.model;

import java.util.Date;

public class ReserveModel {

	private String id;

	private Date checkindate;

	private Date checkoutdate;

	private String memberid;

	public ReserveModel(String id, Date checkindate, Date checkoutdate, String memberid) {
		super();
		this.id = id;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
		this.memberid = memberid;
	}

	public ReserveModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCheckindate() {
		return checkindate;
	}

	public void setCheckindate(Date checkindate) {
		this.checkindate = checkindate;
	}

	public Date getCheckoutdate() {
		return checkoutdate;
	}

	public void setCheckoutdate(Date checkoutdate) {
		this.checkoutdate = checkoutdate;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

}
