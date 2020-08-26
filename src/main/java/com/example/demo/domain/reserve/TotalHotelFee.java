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

	public Amount calcTotalHotelFee() {
		return new Amount(stayTerm * numberOfGuest * plan.getAmount().value);
	}

}
