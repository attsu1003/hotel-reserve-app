package com.example.demo.domain.reserve.calculation;

import com.example.demo.domain.reserve.Plan;

public interface Fee {
	
	Amount amount(Plan plan);
}
