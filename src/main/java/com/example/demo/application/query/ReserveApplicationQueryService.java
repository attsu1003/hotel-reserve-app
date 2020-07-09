package com.example.demo.application.query;

import com.example.demo.domain.model.ReserveModel;

public interface ReserveApplicationQueryService {
	
	public ReserveModel execute(String reserveId);

}
