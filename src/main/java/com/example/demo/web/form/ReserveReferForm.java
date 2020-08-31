package com.example.demo.web.form;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.reserve.Plan;
import com.example.demo.web.validation.ConfirmDayBeforeAndAfter;

@ConfirmDayBeforeAndAfter(checkInDay = "checkInDay", checkOutDay = "checkOutDay")
public class ReserveReferForm {

	private String id;

	private List<Plan> planList;

	private Date checkInDay;

	private Date checkOutDay;

	private String memberid;

	private List<Integer> numberOfGuestList;

	private Plan plan;

	private int numberOfGuest;

	public ReserveReferForm() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Integer> getNumberOfGuestList() {
		return numberOfGuestList;
	}

	public void setNumberOfGuestList(List<Integer> numberOfGuestList) {
		this.numberOfGuestList = numberOfGuestList;
	}

	public int getNumberOfGuest() {
		return numberOfGuest;
	}

	public void setNumberOfGuest(int numberOfGuest) {
		this.numberOfGuest = numberOfGuest;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
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
