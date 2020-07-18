package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveCondition;
import com.example.demo.domain.reserve.ReserveId;

@Controller
public class ReserveConfirmController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String reserve(@Validated ReserveModel reserveModel, BindingResult bindingResult, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();// get logged in username
		System.out.println(reserveModel);
		ReserveCondition reserveCondition = new ReserveCondition(name, reserveModel.getCheckInDay(),
				reserveModel.getCheckOutDay());
		ReserveCommand reserveCommand = new ReserveCommand(new ReserveId("test11"), reserveCondition);
		applicationCommandBus.dispatch(reserveCommand);

		reserveModel.setId(reserveCommand.getReserveId().getReserveId());
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

}