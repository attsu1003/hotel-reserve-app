package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.NoVacancyRoomException;
import com.example.demo.web.form.ReserveForm;

@Controller
@SessionAttributes(types = { ReserveForm.class })
public class ReserveConfirmController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "reserveConfirm", method = RequestMethod.POST)
	public String reserve(@ModelAttribute ReserveForm reserveForm, BindingResult bindingResult, Model model,
			SessionStatus sessionStatus) {
		ReserveCommand reserveCommand = new ReserveCommand(reserveForm.getPlan(), reserveForm.getCheckInDay(),
				reserveForm.getCheckOutDay(), reserveForm.getNumberOfGuest(), reserveForm.getTotalHotelFee(),
				SecurityContextHolder.getContext().getAuthentication().getName());
		try {
			applicationCommandBus.dispatch(reserveCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof NoVacancyRoomException) {
				addErrorMessage("MSGE1010");
			}
			return "/reservemgmt/reserve";
		}
		model.addAttribute("name", SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("message", "予約完了画面");

		// セッション情報の削除
		sessionStatus.setComplete();

		return "/reservemgmt/complete";
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(Model model) {
		ReserveModel reserveModel = new ReserveModel();
		model.addAttribute("reserveModel", reserveModel);
		model.addAttribute("message", "予約画面");
		return "/reservemgmt/reserve";
	}

}