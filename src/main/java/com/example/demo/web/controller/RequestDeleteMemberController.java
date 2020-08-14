package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.web.form.RequestDeleteMemberForm;

@Controller
public class RequestDeleteMemberController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "requestDeleteMember", method = RequestMethod.GET)
	public String requestDeleteMember(Model model) {
		RequestDeleteMemberForm requestDeleteMemberForm = new RequestDeleteMemberForm();
		model.addAttribute("requestDeleteMemberForm", requestDeleteMemberForm);
		return "/reqmail/requestDeleteMember";
	}

	@RequestMapping(value = "requestDeleteMember", method = RequestMethod.POST)
	public String requestDeleteMember(@Validated RequestDeleteMemberForm requestDeleteMemberForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/reqmail/requestDeleteMember";
		}
		RequestDeleteMemberCommand requestDeleteMemberCommand = new RequestDeleteMemberCommand(
				requestDeleteMemberForm.getMailAddress());
		try {
			applicationCommandBus.dispatch(requestDeleteMemberCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1002");
			}
			return "/reqmail/requestDeleteMember";
		}
		return "requestDeleteMemberComplete";
	}
}
