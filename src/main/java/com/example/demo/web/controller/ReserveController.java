package com.example.demo.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.query.ReserveApplicationQueryService;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.web.form.DeleteReserveForm;
import com.example.demo.web.form.ReserveConfirmForm;
import com.example.demo.web.form.ReserveForm;

@Controller
@SessionAttributes(types = { ReserveForm.class })
public class ReserveController extends AbstractController {

	@Autowired
	private ApplicationCommandBus applicationCommandBus;

	@Autowired
	ReserveApplicationQueryService reserveApplicationQueryService;

	@RequestMapping(value = "/reserve", method = RequestMethod.GET)
	public String reserve(Model model) {
		ReserveConfirmForm reserveConfirmForm = new ReserveConfirmForm();
		model.addAttribute("reserveConfirmForm", reserveConfirmForm);
		return "reserve";
	}

	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public String reserve(@Validated ReserveConfirmForm reserveConfirmForm, BindingResult bindingResult, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bindingResult.hasErrors()) {
			reserveConfirmForm.setCheckInDay(null);
			reserveConfirmForm.setCheckOutDay(null);
			return "reserve";
		}
		if (!reserveApplicationQueryService.isReservable(reserveConfirmForm.getCheckInDay(),
				reserveConfirmForm.getCheckOutDay())) {
			reserveConfirmForm.setCheckInDay(null);
			reserveConfirmForm.setCheckOutDay(null);
			addErrorMessage("MSGE1008");
			return "reserve";
		}
		// 予約フォームの作成
		ReserveForm reserveForm = new ReserveForm();
		reserveForm.setCheckInDay(reserveConfirmForm.getCheckInDay());
		reserveForm.setCheckOutDay(reserveConfirmForm.getCheckOutDay());
		reserveForm.setMemberid(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("reserveForm", reserveForm);
		return "confirm";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model) {
		// ユーザの予約を参照し、その一覧を取得する
		List<ReserveModel> reserveModelList = reserveApplicationQueryService.referReserve("22");
		DeleteReserveForm deleteReserveForm = new DeleteReserveForm();
		model.addAttribute("deleteReserveForm", deleteReserveForm);
		model.addAttribute("reserveModelList", reserveModelList);
		return "delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(name = "id") String id, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DeleteCommand deleteCommand = new DeleteCommand(id);
		try {
			applicationCommandBus.dispatch(deleteCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "cancel";
	}

}
