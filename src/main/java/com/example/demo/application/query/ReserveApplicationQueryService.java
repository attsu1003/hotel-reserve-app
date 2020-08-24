package com.example.demo.application.query;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.model.ReserveModel;

public interface ReserveApplicationQueryService {
	
	public List<ReserveModel> referReserve(String reserveId);

	public boolean isReservable(Date CheckInDay, Date checkOutDay);

}
