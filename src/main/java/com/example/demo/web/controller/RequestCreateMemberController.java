package com.example.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.RequestCreateMemberCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.web.form.RequestCreateMemberForm;

@Controller
public class RequestCreateMemberController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "requestCreateMember", method = RequestMethod.GET)
	public String requestCreateMember(Model model) {
		RequestCreateMemberForm requestCreateMemberForm = new RequestCreateMemberForm();
		model.addAttribute("requestCreateMemberForm", requestCreateMemberForm);
		return "requestCreateMember";
	}

	@RequestMapping(value = "requestCreateMember", method = RequestMethod.POST)
	public String requestCreateMember(@Validated RequestCreateMemberForm requestCreateMemberForm,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "requestCreateMember";
		}
		RequestCreateMemberCommand requestCreateMemberCommand = new RequestCreateMemberCommand(
				requestCreateMemberForm.getMailAddress());
		try {
			applicationCommandBus.dispatch(requestCreateMemberCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof MemberAlreadyExistException) {
				addErrorMessage("MSGE1008");
				return "requestCreateMember";
			}
		}
		return "requestCreateMemberComplete";
	}
}
