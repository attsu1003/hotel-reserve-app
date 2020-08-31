package com.example.demo.domain.reserve;

import java.util.Date;
import java.util.List;

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
		reserveMapper.insert(reserve.getReserveId(), reserve.getPlan(), reserve.getCheckInDay(),
				reserve.getCheckOutDay(), reserve.getNumberOfGuest(), reserve.getTotalHotelFee(),
				reserve.getMemberId());
	}

	public boolean updateReserve(Reserve reserve) {
		return reserveMapper.update(reserve);
	}

	public ReserveModel referReserve(ReserveId reserveId) {
		return reserveMapper.selectByReserveId(reserveId.getReserveId());
	}

	public List<ReserveModel> referReserve(String memberId) {
		return reserveMapper.select(memberId);
	}

	public boolean deleteReserve(String reserveId) {
		return reserveMapper.delete(reserveId);
	}
}
