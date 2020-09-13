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

	private List<Integer> numberOfAdultGuestList;

	private List<Integer> numberOfChildrenGuestList;

	private Plan plan;

	private int numberOfAdultGuest;

	private int numberOfChildrenGuest;

	public ReserveReferForm() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Integer> getNumberOfAdultGuestList() {
		return numberOfAdultGuestList;
	}

	public void setNumberOfAdultGuestList(List<Integer> numberOfAdultGuestList) {
		this.numberOfAdultGuestList = numberOfAdultGuestList;
	}

	public List<Integer> getNumberOfChildrenGuestList() {
		return numberOfChildrenGuestList;
	}

	public void setNumberOfChildrenGuestList(List<Integer> numberOfChildrenGuestList) {
		this.numberOfChildrenGuestList = numberOfChildrenGuestList;
	}

	public int getNumberOfAdultGuest() {
		return numberOfAdultGuest;
	}

	public void setNumberOfAdultGuest(int numberOfAdultGuest) {
		this.numberOfAdultGuest = numberOfAdultGuest;
	}

	public int getNumberOfChildrenGuest() {
		return numberOfChildrenGuest;
	}

	public void setNumberOfChildrenGuest(int numberOfChildrenGuest) {
		this.numberOfChildrenGuest = numberOfChildrenGuest;
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
