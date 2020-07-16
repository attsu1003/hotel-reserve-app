package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.DateUtil;
import com.example.demo.domain.reserve.ReserveRepository;
import com.example.demo.domain.room.RoomRepository;

@Controller
public class ReserveReferVacancyController {

	@Autowired
	private ReserveRepository reserverepository;

	@Autowired
	private RoomRepository roomRepository;

	@RequestMapping(value = "/referVacancy", method = RequestMethod.GET)
	public String referVacancy(Model model) {
		model.addAttribute("message", "空き状況照会画面");

		Date date = new Date();

		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(ysdf.format(date));
		model.addAttribute("year", year);
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		int month = Integer.parseInt(msdf.format(date));
		model.addAttribute("month", month);
		model.addAttribute("roomStatements",
				this.checkOneMonthRoomStatements(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
						Integer.parseInt(new SimpleDateFormat("MM").format(date))));
		return "referVacancy";
	}

	@RequestMapping(value = "/referVacancyNextDate", method = RequestMethod.GET)
	public String referVacancyNextDate(@RequestParam(name = "year") String year,
			@RequestParam(name = "month") String month, Model model) {
		model.addAttribute("message", "空き状況照会画面");

		model.addAttribute("year", Integer.parseInt(year));
		model.addAttribute("month", Integer.parseInt(month));
		model.addAttribute("roomStatements",
				this.checkOneMonthRoomStatements(Integer.parseInt(year), Integer.parseInt(month)));
		return "referVacancy";
	}

	public int countReserve(Date date) {
		return reserverepository.countReserve(date);
	}

	private List<String> checkOneMonthRoomStatements(int year, int month) {
		List<String> roomStatements = new ArrayList<>();
		Date date = new Date(year, month - 1, 1);
		int roomNum = roomRepository.countRoom();
		for (int i = 1; i <= DateUtil.countMonthDays(date); i++) {
			int reserveCount = reserverepository
					.countReserve(DateUtil.getDateFrom(new SimpleDateFormat("yyyy").format(date),
							new SimpleDateFormat("MM").format(date), String.format("%02d", i)));
			roomStatements.add(this.roomStatement(reserveCount, roomNum));
			date = DateUtil.getNextDate(date);
		}
		return roomStatements;
	}

	String roomStatement(int reserveCount, int roomNum) {
		String st;
		if (reserveCount >= roomNum) {
			st = "×";
		} else if (roomNum - reserveCount >= 6) {
			st = "△";
		} else {
			st = "〇";
		}
		return st;
	}
}
