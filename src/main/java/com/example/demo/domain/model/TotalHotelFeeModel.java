package com.example.demo.domain.model;

import com.example.demo.domain.reserve.calculation.TotalHotelFee;

public class TotalHotelFeeModel {

	private int amount;

	public TotalHotelFeeModel(TotalHotelFee totalHotelFee) {
		this.amount = totalHotelFee.getTotalFeeAmount().getAmountValue();
	}

	public int getAmount() {
		return amount;
	}
}