package com.example.demo.domain.reserve;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.command.ReserveCommand;
import com.example.demo.common.DateUtil;

@Service
public class ReserveService {

	@Autowired
	ReserveRepository reserveRepository;

	public boolean isReservable(Date CheckInDay, Date checkOutDay, int roomNum) {
		return reserveRepository.isReservable(CheckInDay, checkOutDay, roomNum);
	}

	private boolean correctStayPeriod(ReserveCommand reserveCommand) {
		long oneday = 24 * 60 * 60 * 60 * 1000;
		return (DateUtil.diffDate(reserveCommand.getCheckInDay(), reserveCommand.getCheckOutDay()) >= oneday);
	}

}
