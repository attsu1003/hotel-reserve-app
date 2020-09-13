package com.example.demo.domain.reserve.calculation;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.reserve.Guest;
import com.example.demo.domain.reserve.Guests;
import com.example.demo.domain.reserve.Plan;

public class TotalHotelFee {

	private Amount amount;
	private int stayTerm;
	private Guests guests;
	private Plan plan;

	public TotalHotelFee(Amount amount) {
		this.amount = amount;
	}

	public TotalHotelFee(int stayTerm, Guests guests, Plan plan) {
		super();
		this.stayTerm = stayTerm;
		this.guests = guests;
		this.plan = plan;
	}

	@SuppressWarnings("serial")
	public void calcTotalHotelFee() {
		Amount amount = new Amount();
		List<Amount> amounts = new ArrayList<>();
		for (Guest guest : this.guests.getGuestList()) {
			amounts.addAll(new ArrayList<Amount>() {
				{
					add(new Amount(guest.feeType().fee().amount(plan).getAmountValue()));
				}
			});
		}
		this.amount = amount.addAll(amounts).multiply(stayTerm);
	}

	public Amount getTotalFeeAmount() {
		return this.amount;
	}
}