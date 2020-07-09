package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.query.ReserveApplicationQueryService;
import com.example.demo.domain.model.ReserveModel;

@Controller
public class ReserveReferController {

	@Autowired
	ReserveApplicationQueryService reserveApplicationQueryService;

	@RequestMapping(value = "/refer", method = RequestMethod.GET)
	public String refer(Model model) {
		model.addAttribute("message", "予約照会画面");
		ReserveModel reserveModel = new ReserveModel();
		model.addAttribute("reserveModel", reserveModel);
		return "refer";
	}

	@RequestMapping(value = "/referDetail", method = RequestMethod.POST)
	public String refer(@RequestParam(name = "id") String id, Model model) {
		ReserveModel reserveModel = reserveApplicationQueryService.execute(id);
		model.addAttribute("message", "予約明細画面");
		model.addAttribute("reserveModel", reserveModel);
		return "referDetail";
	}

}
