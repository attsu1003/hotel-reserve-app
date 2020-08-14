package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.ChangePasswordCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.web.form.ChangePasswordForm;

@Controller
public class ChangePasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String changePassword(Model model) {
		ChangePasswordForm changePasswordForm = new ChangePasswordForm();
		model.addAttribute("changePasswordForm", changePasswordForm);
		return "/usermgmt/changePassword";
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(@Validated ChangePasswordForm changePasswordForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/usermgmt/changePassword";
		}
		ChangePasswordCommand changePasswordCommand = new ChangePasswordCommand(changePasswordForm.getPassword(),
				changePasswordForm.getNewPassword(), changePasswordForm.getNewConfirmPassword(),
				SecurityContextHolder.getContext().getAuthentication().getName());
		try {
			applicationCommandBus.dispatch(changePasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof PasswordNotMatchException) {
				addErrorMessage("MSGE1004");
			}
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1007");
			}
			return "/usermgmt/changePassword";
		}
		addMessage("MSGM1002");
		return "/usermgmt/changePassword";
	}

}
