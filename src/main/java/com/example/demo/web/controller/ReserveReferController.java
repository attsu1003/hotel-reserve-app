package com.example.demo.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.query.ReserveApplicationQueryService;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.Guests;
import com.example.demo.domain.reserve.Plan;
import com.example.demo.web.form.ReserveReferForm;

@Controller
public class ReserveReferController extends AbstractController {

	@Autowired
	ReserveApplicationQueryService reserveApplicationQueryService;

	@RequestMapping(value = "/refer", method = RequestMethod.GET)
	public String refer(Model model) {
		List<ReserveModel> reserveModelList = reserveApplicationQueryService
				.referReserve(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("reserveModelList", reserveModelList);
		ReserveReferForm reserveReferForm = new ReserveReferForm();
		reserveReferForm.setPlanList(Arrays.asList(Plan.values()));
		reserveReferForm.setNumberOfGuestList(Guests.getMaxNumberOfGuestList());
		model.addAttribute("reserveReferForm", reserveReferForm);
		return "/reservemgmt/refer";
	}
}
