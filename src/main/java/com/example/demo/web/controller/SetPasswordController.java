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
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.web.form.SetPasswordForm;

@Controller
public class SetPasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "setPassword", method = RequestMethod.GET)
	public String setPassword(Model model) {
		SetPasswordForm setPasswordForm = new SetPasswordForm();
		model.addAttribute("setPasswordForm", setPasswordForm);
		return "setPassword";
	}

	@RequestMapping(value = "setPassword", method = RequestMethod.POST)
	public String setPassword(@Validated SetPasswordForm setPasswordForm, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "setPassword";
		}
		SetPasswordCommand setPasswordCommand = new SetPasswordCommand(setPasswordForm.getPassword(),
				setPasswordForm.getConfirmPassword(), (String) request.getSession().getAttribute("mailAddress"));
		try {
			applicationCommandBus.dispatch(setPasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof PasswordNotMatchException) {
				addErrorMessage("MSGE1003");
			}
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1005");
			}
			return "setPassword";
		}
		request.getSession().removeAttribute("mailAddress");
		addMessage("MSGM1003");
		return "setPassword";
	}
}