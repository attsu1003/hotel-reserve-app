package com.example.demo.application.query;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.TotalHotelFeeModel;
import com.example.demo.domain.reserve.Plan;
import com.example.demo.domain.reserve.TotalHotelFee;

@Component
public class CalculationTotalHotelFeeApplicationQueryServiceImpl
		implements CalculationTotalHotelFeeApplicationQueryService {

	public TotalHotelFeeModel calculationTotalHotelFee(int stayTerm, int numberOfGuest, Plan plan) {
		TotalHotelFee totalHotelFee = new TotalHotelFee(stayTerm, numberOfGuest, plan);
		totalHotelFee.calcTotalHotelFee();
		return new TotalHotelFeeModel(totalHotelFee);
	}
}