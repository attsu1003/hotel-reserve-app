package com.example.demo.application.query;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.TotalHotelFeeModel;
import com.example.demo.domain.reserve.Guest;
import com.example.demo.domain.reserve.Guests;
import com.example.demo.domain.reserve.Plan;
import com.example.demo.domain.reserve.calculation.FeeType;
import com.example.demo.domain.reserve.calculation.TotalHotelFee;

@Component
public class CalculationTotalHotelFeeApplicationQueryServiceImpl
		implements CalculationTotalHotelFeeApplicationQueryService {

	public TotalHotelFeeModel calculationTotalHotelFee(int stayTerm, int numberOfAdultGuest, int numberOfChildrenGuest,
			Plan plan) {
		Guests guests = new Guests();
		Guest adultGuest;
		for (int i = 1; i <= numberOfAdultGuest; i++) {
			adultGuest = new Guest(FeeType.adultFee);
			guests.add(adultGuest);
		}
		Guest childGuest;
		for (int i = 1; i <= numberOfChildrenGuest; i++) {
			childGuest = new Guest(FeeType.childFee);
			guests.add(childGuest);
		}
		TotalHotelFee totalHotelFee = new TotalHotelFee(stayTerm, guests, plan);
		totalHotelFee.calcTotalHotelFee();
		return new TotalHotelFeeModel(totalHotelFee);
	}
}