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
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.ReserveCondition;
import com.example.demo.web.form.ReserveForm;

@Controller
@SessionAttributes(types = { ReserveForm.class })
public class ReserveConfirmController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "reserveConfirm", method = RequestMethod.POST)
	public String reserve(@ModelAttribute ReserveForm reserveForm, BindingResult bindingResult, Model model,
			SessionStatus sessionStatus) {
		ReserveCondition reserveCondition = new ReserveCondition(
				SecurityContextHolder.getContext().getAuthentication().getName(), reserveForm.getCheckInDay(),
				reserveForm.getCheckOutDay());
		ReserveCommand reserveCommand = new ReserveCommand(reserveCondition);
		try {
			applicationCommandBus.dispatch(reserveCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("name", reserveCondition.getMemberId());
		model.addAttribute("message", "予約完了画面");

		// セッション情報の削除
		sessionStatus.setComplete();

		return "complete";
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(Model model) {
		ReserveModel reserveModel = new ReserveModel();
		model.addAttribute("reserveModel", reserveModel);
		model.addAttribute("message", "予約画面");
		return "reserve";
	}

}