package com.example.demo.web.controller;

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
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.DeleteMemberCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.WrongPasswordException;
import com.example.demo.domain.model.LoginModel;
import com.example.demo.web.form.DeleteMemberForm;

@Controller
public class MemberController extends AbstractController {

	@Autowired
	private ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "/createMember")
	public String createMember(Model model) {
		model.addAttribute(new LoginModel());
		return "/usermgmt/createMember";
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
			if (e.getCause() instanceof MemberAlreadyExistException) {
				addErrorMessage("MSGE1001");
				return "/usermgmt/createMember";
			}
		}
		addMessage("MSGM1001");
		return "/usermgmt/createMember";
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public String deleteMember(Model model) {
		DeleteMemberForm deleteMemberForm = new DeleteMemberForm();
		model.addAttribute("deleteMemberForm", deleteMemberForm);
		return "/usermgmt/deleteMember";
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(@Validated DeleteMemberForm deleteMemberForm, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "/usermgmt/deleteMember";
		}
		DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(
				(String) request.getSession().getAttribute("mailAddress"), deleteMemberForm.getPassword());
		try {
			applicationCommandBus.dispatch(deleteMemberCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1006");
			}
			if (e.getCause() instanceof WrongPasswordException) {
				addErrorMessage("MSGE1007");
			}
			return "/usermgmt/deleteMember";
		}
		request.getSession().removeAttribute("mailAddress");
		return "deleteMemberComplete";
	}
}