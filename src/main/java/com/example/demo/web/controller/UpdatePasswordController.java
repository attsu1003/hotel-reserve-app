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
import com.example.demo.application.command.UpdatePasswordCommand;
import com.example.demo.controller.AbstractController;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.web.form.UpdatePasswordForm;

@Controller
public class UpdatePasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "updatePwd", method = RequestMethod.GET)
	public String updatePwd(Model model) {
		UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
		model.addAttribute("updatePasswordForm", updatePasswordForm);
		return "updatePassword";
	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(@Validated UpdatePasswordForm updatePasswordForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "updatePassword";
		}
		UpdatePasswordCommand updatePasswordCommand = new UpdatePasswordCommand(updatePasswordForm.getPassword(),
				updatePasswordForm.getNewPassword(), updatePasswordForm.getNewConfirmPassword(),
				SecurityContextHolder.getContext().getAuthentication().getName());
		try {
			applicationCommandBus.dispatch(updatePasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof PasswordNotMatchException) {
				addErrorMessage("MSGE1004");
			}
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1007");
			}
			return "updatePassword";
		}
		addMessage("MSGM1002");
		return "updatePassword";
	}

}
