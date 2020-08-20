package com.example.demo.domain.reserve;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DateUtil;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.port.ReserveMapper;

@Repository
public class ReserveRepository {

	@Autowired
	ReserveMapper reserveMapper;

	public boolean isReservable(Date checkInDay, Date checkOutDay, int roomNum) {
		long diffdate = DateUtil.diffDate(checkInDay, checkOutDay);

		for (long i = 0; i <= diffdate; i++) {
			int reserveNum = reserveMapper.isReservable(checkInDay, checkOutDay, roomNum);
			if (reserveNum >= roomNum) {
				return false;
			}
		}
		return true;
	}

	public int countReserve(Date date) {
		return reserveMapper.count(date);
	}

	public void reserve(Reserve reserve) {
		reserveMapper.insert(reserve.getReserveId(), reserve.getCheckInDay(), reserve.getCheckOutDay(),
				reserve.getMemberId());
	}

	public ReserveModel referReserve(String reserveId) {
		return reserveMapper.select(reserveId);
	}

	public void deleteReserve(String reserveId) {
		reserveMapper.delete(reserveId);
	}
}
