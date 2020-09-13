package com.example.demo.application.query;

import com.example.demo.domain.model.TotalHotelFeeModel;
import com.example.demo.domain.reserve.Plan;

public interface CalculationTotalHotelFeeApplicationQueryService {

	public TotalHotelFeeModel calculationTotalHotelFee(int stayTerm, int numberOfAdultGuest, int numberOfChildrenGuest,
			Plan plan);
}
