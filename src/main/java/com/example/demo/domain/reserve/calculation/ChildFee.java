package com.example.demo.domain.reserve.calculation;

import com.example.demo.domain.reserve.Plan;

public class ChildFee implements Fee {

	private static String standardPlan = "スタンダードプラン";
	private static String premiumPlan = "プレミアムプラン";

	private static int childFeeOfStandardPlan = 3000;
	private static int childFeeOfPremiumPlan = 5000;

	@Override
	public Amount amount(Plan plan) {
		if (plan.getPlanName().equals(standardPlan)) {
			return new Amount(childFeeOfStandardPlan);
		} else if (plan.getPlanName().equals(premiumPlan)) {
			return new Amount(childFeeOfPremiumPlan);
		}
		return new Amount();
	}
}
