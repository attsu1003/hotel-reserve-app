package com.example.demo.application.query;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveRepository;
import com.example.demo.domain.reserve.ReserveService;
import com.example.demo.domain.room.RoomRepository;

@Component
public class ReserveApplicationQueryServiceImpl implements ReserveApplicationQueryService {

	@Autowired
	private ReserveRepository reserveRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReserveService reserveService;

	@Override
	public List<ReserveModel> referReserve(String memberId) {
		return reserveRepository.referReserve(memberId);
	}
	
	@Override
	public boolean isReservable(Date CheckInDay, Date checkOutDay) {
		return reserveService.isReservable(CheckInDay, checkOutDay, roomRepository.countRoom());
	}

}
