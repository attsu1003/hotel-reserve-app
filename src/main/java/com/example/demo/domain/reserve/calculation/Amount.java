package com.example.demo.domain.reserve.calculation;

import java.util.List;
import java.util.Objects;

/**
 * 金額
 */
public class Amount {

	private int value;

	public Amount(int value) {
		this.value = value;
	}

	public Amount() {
	}

	public Amount add(Amount other) {
		return new Amount(value + other.value);
	}

	public Amount addAll(List<Amount> amounts) {
		int totalValue = 0;
		for (Amount amount : amounts) {
			totalValue += amount.value;
		}
		return new Amount(totalValue);
	}

	public Amount multiply(int number) {
		return new Amount(value * number);
	}

	@Override
	public String toString() {
		return String.format("%,d円", value);
	}

	@Override
	public boolean equals(Object other) {
		return isEqual((Amount) other);
	}

	private boolean isEqual(Amount amount) {
		return value == amount.value;
	}

	public int getAmountValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

}