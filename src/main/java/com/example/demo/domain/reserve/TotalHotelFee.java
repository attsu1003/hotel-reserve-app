package com.example.demo.domain.reserve;

public class TotalHotelFee {

	private Amount amount;
	private int stayTerm;
	private int numberOfGuest;
	private Plan plan;

	public TotalHotelFee(int stayTerm, int numberOfGuest, Plan plan) {
		super();
		this.stayTerm = stayTerm;
		this.numberOfGuest = numberOfGuest;
		this.plan = plan;
	}

	public void calcTotalHotelFee() {
		this.amount = new Amount(stayTerm * numberOfGuest * plan.getAmount().value);
	}

	public Amount getTotalFeeAmount() {
		return this.amount;
	}
}