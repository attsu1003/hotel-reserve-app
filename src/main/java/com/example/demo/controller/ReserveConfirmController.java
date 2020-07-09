package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveCondition;
import com.example.demo.domain.reserve.ReserveId;

@Controller
public class ReserveConfirmController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String reserve(@RequestParam(name = "checkInDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDay,
			@RequestParam(name = "checkOutDay") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDay, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();// get logged in username

		ReserveCondition reserveCondition = new ReserveCondition(name, checkInDay, checkOutDay);
		ReserveCommand reserveCommand = new ReserveCommand(new ReserveId("test11"), reserveCondition);
		applicationCommandBus.dispatch(reserveCommand);
		ReserveModel reserveModel = new ReserveModel(reserveCommand.getReserveId().getReserveId(),
				reserveCommand.getCheckInDay(), reserveCommand.getCheckOutDay(), reserveCommand.getMemberId());
		model.addAttribute("reserveModel", reserveModel);
		model.addAttribute("name", name);
		model.addAttribute("message", "予約確認画面");
		return "confirm";
	}
	
	@RequestMapping(value = "confirm", params = "submit", method = RequestMethod.POST)
	public String reserve(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();// get logged in username
		model.addAttribute("name", name);
		model.addAttribute("message", "予約完了画面");
		return "complete";
	}
	
	@RequestMapping(value = "confirm", params = "back", method = RequestMethod.POST)
	public String back(Model model) {
		System.out.println("through post  back reserve");
		ReserveModel reserveModel = new ReserveModel();
		model.addAttribute("reserveModel", reserveModel);
		model.addAttribute("message", "予約画面");
		return "reserve";
	}

//	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
//	public String reserve(@ModelAttribute ReserveModel reserveModel, RedirectAttributes redirectAttributes, Model model)
//			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		model.addAttribute("message", "予約確認画面");
//		model.addAttribute("checkInDay", reserveModel.getCheckInDay());
//		model.addAttribute("checkOutDay", reserveModel.getCheckOutDay());
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String name = auth.getName();// get logged in username
//		ReserveCondition reserveCondition = new ReserveCondition(name, DateUtil.date(reserveModel.getCheckInDay()),
//				DateUtil.date(reserveModel.getCheckOutDay()));
//		ReserveCommand reserveCommand = new ReserveCommand(new ReserveId(), reserveCondition);
//		applicationCommandBus.dispatch(reserveCommand);
//		System.out.println("through");
//		return "confirm";
//	}

}
