package com.example.demo.application.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveRepository;

@Component
public class ReserveApplicationQueryServiceImpl implements ReserveApplicationQueryService{
	
	@Autowired
	private ReserveRepository reserveRepository;
	
	@Override
	public ReserveModel execute(String reserveId) {
		return reserveRepository.referReserve(reserveId);
	}
}
