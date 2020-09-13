package com.example.demo.domain.reserve;

import com.example.demo.domain.reserve.calculation.FeeType;

public class Guest {
	private FeeType feeType;

	public Guest(FeeType feeType) {
		this.feeType = feeType;
	}

	public FeeType feeType() {
		return this.feeType;
	}
}
