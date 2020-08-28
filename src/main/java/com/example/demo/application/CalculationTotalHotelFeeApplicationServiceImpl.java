package com.example.demo.application;

import com.example.demo.domain.reserve.Plan;
import com.example.demo.domain.reserve.TotalHotelFee;

public class CalculationTotalHotelFeeApplicationServiceImpl {

	public void calculationTotalHotelFee(int stayTerm, int numberOfGuest, Plan plan) {
		TotalHotelFee totalHotelFee = new TotalHotelFee(stayTerm, numberOfGuest, plan);
	}
}