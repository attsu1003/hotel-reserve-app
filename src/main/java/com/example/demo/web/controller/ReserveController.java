package com.example.demo.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.demo.application.query.CalculationTotalHotelFeeApplicationQueryService;
import com.example.demo.application.query.ReserveApplicationQueryService;
import com.example.demo.common.DateUtil;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.model.TotalHotelFeeModel;
import com.example.demo.domain.reserve.Guest;
import com.example.demo.domain.reserve.Plan;
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

	@Autowired
	CalculationTotalHotelFeeApplicationQueryService CalculationTotalHotelFeeApplicationQueryService;

	@RequestMapping(value = "/reserve", method = RequestMethod.GET)
	public String reserve(Model model) {
		ReserveConfirmForm reserveConfirmForm = new ReserveConfirmForm();
		reserveConfirmForm.setPlanList(Arrays.asList(Plan.values()));
		reserveConfirmForm.setNumberOfGuestList(getNumberOfGuestList(Guest.MAXIMUM_APPROVE_GUEST_NUMBER));
		model.addAttribute("reserveConfirmForm", reserveConfirmForm);
		return "/reservemgmt/reserve";
	}

	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public String reserve(@Validated ReserveConfirmForm reserveConfirmForm, BindingResult bindingResult, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bindingResult.hasErrors()) {
			// todo エラーがあった場合チェックイン日とチェックアウト日の入力が消えず、日付の表示形式が崩れて表示されるため明示的にnullをセット
			reserveConfirmForm.setCheckInDay(null);
			reserveConfirmForm.setCheckOutDay(null);
			return "reserve";
		}
		if (!reserveApplicationQueryService.isReservable(reserveConfirmForm.getCheckInDay(),
				reserveConfirmForm.getCheckOutDay())) {
			// todo エラーがあった場合チェックイン日とチェックアウト日の入力が消えず、日付の表示形式が崩れて表示されるため明示的にnullをセット
			reserveConfirmForm.setCheckInDay(null);
			reserveConfirmForm.setCheckOutDay(null);
			addErrorMessage("MSGE1010");
			return "/reservemgmt/reserve";
		}
		// todo 料金計算
		TotalHotelFeeModel totalHotelFeeModel = CalculationTotalHotelFeeApplicationQueryService
				.calculationTotalHotelFee(
						DateUtil.diffDate(reserveConfirmForm.getCheckInDay(), reserveConfirmForm.getCheckOutDay()),
						reserveConfirmForm.getNumberOfGuest(), reserveConfirmForm.getPlan());

		// 予約フォームの作成
		ReserveForm reserveForm = new ReserveForm();
		reserveForm.setPlan(reserveConfirmForm.getPlan());
		reserveForm.setCheckInDay(reserveConfirmForm.getCheckInDay());
		reserveForm.setCheckOutDay(reserveConfirmForm.getCheckOutDay());
		reserveForm.setMemberid(SecurityContextHolder.getContext().getAuthentication().getName());
		reserveForm.setNumberOfGuest(reserveConfirmForm.getNumberOfGuest());
		reserveForm.setTotalHotelFee(totalHotelFeeModel.getAmount());
		model.addAttribute("reserveForm", reserveForm);
		return "/reservemgmt/confirm";
	}

	@RequestMapping(value = "/deleteReserve", method = RequestMethod.GET)
	public String delete(Model model) {
		// ユーザの予約を参照し、その一覧を取得する
		List<ReserveModel> reserveModelList = reserveApplicationQueryService
				.referReserve(SecurityContextHolder.getContext().getAuthentication().getName());
		DeleteReserveForm deleteReserveForm = new DeleteReserveForm();
		model.addAttribute("deleteReserveForm", deleteReserveForm);
		model.addAttribute("reserveModelList", reserveModelList);
		return "/reservemgmt/deleteReserve";
	}

	@RequestMapping(value = "/deleteReserve", method = RequestMethod.POST)
	public String delete(@RequestParam(name = "id") String id, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DeleteCommand deleteCommand = new DeleteCommand(id);
		try {
			applicationCommandBus.dispatch(deleteCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/reservemgmt/completeDeleteReserve";
	}

	private List<Integer> getNumberOfGuestList(int maximumApproveGuestNumber) {
		List<Integer> numberOfGuestList = new ArrayList<>();
		for (int i = 1; i <= maximumApproveGuestNumber; i++) {
			numberOfGuestList.add(i);
		}
		return numberOfGuestList;
	}
}