package com.example.demo.domain.reserve.calculation;

import com.example.demo.domain.reserve.Plan;

public class AdultFee implements Fee {

	private static String standardPlan = "スタンダードプラン";
	private static String premiumPlan = "プレミアムプラン";

	private static int adultFeeOfStandardPlan = 9000;
	private static int adultFeeOfPremiumPlan = 12000;

	@Override
	public Amount amount(Plan plan) {
		if (plan.getPlanName().equals(standardPlan)) {
			return new Amount(adultFeeOfStandardPlan);
		} else if (plan.getPlanName().equals(premiumPlan)) {
			return new Amount(adultFeeOfPremiumPlan);
		}
		return new Amount();
	}
}
