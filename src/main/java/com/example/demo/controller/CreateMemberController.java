package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.model.LoginModel;

@Controller
public class CreateMemberController extends AbstractController {

	@Autowired
	private ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "/createMember")
	public String createMember(Model model) {
		model.addAttribute(new LoginModel());
		return "createMember";
	}

	@RequestMapping(value = "/createdMember")
	public String createdMember(@Validated LoginModel loginModel, BindingResult bindingResult, Model model)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bindingResult.hasErrors()) {

		}
		CreateMemberCommand createMemberCommand = new CreateMemberCommand(loginModel.getUsername(),
				loginModel.getPassword());
		try {
			applicationCommandBus.dispatch(createMemberCommand);
		} catch (Exception e) {
			addErrorMessage("MSGE1001");
			return "createMember";
		}
		addMessage("MSGE1006");
		return "createMember";
	}
}