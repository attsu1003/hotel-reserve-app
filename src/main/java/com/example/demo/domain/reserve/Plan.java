package com.example.demo.domain.reserve;

import com.example.demo.domain.reserve.calculation.Amount;

public enum Plan {
	standardPlan("スタンダードプラン", new Amount(100)), premiumPlan("プレミアムプラン", new Amount(1000));

	private String planName;
	private Amount amount;

	Plan(String planName, Amount amount) {
		this.planName = planName;
		this.amount = amount;
	}

	public String getPlanName() {
		return this.planName;
	}

	public Amount getAmount() {
		return this.amount;
	}
}
