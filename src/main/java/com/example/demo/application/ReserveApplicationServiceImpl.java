package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.domain.reserve.Amount;
import com.example.demo.domain.reserve.Reserve;
import com.example.demo.domain.reserve.ReserveRepository;
import com.example.demo.domain.reserve.ReserveService;
import com.example.demo.domain.reserve.TotalHotelFee;
import com.example.demo.domain.room.RoomRepository;

@Component
public class ReserveApplicationServiceImpl implements ReserveApplicationService {

	@Autowired
	private ReserveRepository reserveRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReserveService reserveService;

	@Override
	public void execute(ReserveCommand reserveCommand) {
		if (reserveService.isReservable(reserveCommand.getCheckInDay(), reserveCommand.getCheckOutDay(),
				roomRepository.countRoom())) {

			Reserve reserve = new Reserve(reserveCommand.getPlan(), reserveCommand.getCheckInDay(),
					reserveCommand.getCheckOutDay(), reserveCommand.getNumberOfGuest(),
					new TotalHotelFee(new Amount(reserveCommand.getTotalHotelFee())), reserveCommand.getMemberId());
			reserveRepository.reserve(reserve);
		}
	}

	@Override
	public void execute(DeleteCommand deleteCommand) {
		reserveRepository.deleteReserve(deleteCommand.getReserveId());
	}

}
