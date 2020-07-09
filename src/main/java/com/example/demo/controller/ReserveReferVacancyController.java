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

import com.example.demo.application.query.ReserveReferApplicationQueryServiceImpl;
import com.example.demo.common.DateUtil;
import com.example.demo.domain.reserve.ReserveRepository;
import com.example.demo.domain.room.RoomRepository;

@Controller
public class ReserveReferVacancyController {

//	@Autowired
//	private ReserveReferApplicationQueryServiceImpl reserveReferApplicationQueryServiceImpl;

	@Autowired
	private ReserveRepository reserverepository;

	@Autowired
	private RoomRepository roomRepository;

	@RequestMapping(value = "/referVacancy", method = RequestMethod.GET)
	public String referVacancy(Model model) {
		model.addAttribute("message", "空き状況照会画面");

		int roomNum = roomRepository.countRoom();
		List<String> roomStatements = new ArrayList<>();

		Date date = new Date();
		int monthDays = DateUtil.countMonthDays(date);
		for (int i = 1; i <= monthDays; i++) {
			// int reserveCount = reserverepository.countReserve(DateUtil.firstDate(date));
			int reserveCount = reserverepository
					.countReserve(DateUtil.getDate2(new SimpleDateFormat("yyyy").format(date),
							new SimpleDateFormat("MM").format(date), String.format("%02d", i)));

			roomStatements.add(this.roomStatement(reserveCount, roomNum));
			date = DateUtil.getNextDate(date);
		}
		model.addAttribute("roomStatements", roomStatements);
		return "referVacancy";
	}
	
	@RequestMapping(value = "/referVacancyDate", method = RequestMethod.POST)
	public String referVacancyDate(Model model) {
		model.addAttribute("message", "空き状況照会画面");

		int roomNum = roomRepository.countRoom();
		List<String> roomStatements = new ArrayList<>();

		Date date = new Date();
		int monthDays = DateUtil.countMonthDays(date);
		for (int i = 1; i <= monthDays; i++) {
			// int reserveCount = reserverepository.countReserve(DateUtil.firstDate(date));
			int reserveCount = reserverepository
					.countReserve(DateUtil.getDate2(new SimpleDateFormat("yyyy").format(date),
							new SimpleDateFormat("MM").format(date), String.format("%02d", i)));

			roomStatements.add(this.roomStatement(reserveCount, roomNum));
			date = DateUtil.getNextDate(date);
		}
		model.addAttribute("roomStatements", roomStatements);
		model.addAttribute("hoge", new hoge());
		return "referVacancy";
	}

	public int countReserve(Date date) {
		return reserverepository.countReserve(date);
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
	
	public static class hoge{
		public int publicMethod() {return 2;}
	}

}
