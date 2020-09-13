package com.example.demo.domain.reserve.calculation;

public enum FeeType {

	adultFee(new AdultFee()), childFee(new ChildFee());

	private Fee fee;

	private FeeType(Fee fee) {
		this.fee = fee;
	}

	public Fee fee() {
		return this.fee;
	}
}
