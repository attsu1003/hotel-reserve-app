package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.DeleteCommand;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveId;

@Controller
public class ReserveController {

	@Autowired
	private ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "/reserve", method = RequestMethod.GET)
	public String reserve(Model model) {
		ReserveModel reserveModel = new ReserveModel();
		model.addAttribute("reserveModel", reserveModel);
		model.addAttribute("message", "予約画面");
		return "reserve";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(name = "id") String id, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DeleteCommand deleteCommand = new DeleteCommand(new ReserveId(id));
		applicationCommandBus.dispatch(deleteCommand);
		return "cancel";
	}
}
