package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.domain.model.RequestDeleteMemberModel;

@Controller
public class RequestDeleteMemberController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "requestDeleteMember", method = RequestMethod.GET)
	public String requestDeleteMember(Model model) {
		RequestDeleteMemberModel requestDeleteMemberModel = new RequestDeleteMemberModel();
		model.addAttribute("requestDeleteMemberModel", requestDeleteMemberModel);
		return "requestDeleteMember";
	}
	
	@RequestMapping(value = "requestDeleteMember", method = RequestMethod.POST)
	public String requestDeleteMember(@Validated RequestDeleteMemberModel requestDeleteMemberModel,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		RequestDeleteMemberCommand requestDeleteMemberCommand = new RequestDeleteMemberCommand(
				requestDeleteMemberModel.getMailAddress());
		try {
			applicationCommandBus.dispatch(requestDeleteMemberCommand);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			addErrorMessage("MSGE1002");
			return "requestDeleteMember";
		}
		return "requestDeleteMemberComplete";
	}

}
