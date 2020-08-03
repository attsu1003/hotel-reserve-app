package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.domain.model.SetPasswordModel;

@Controller
public class SetPasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "setPwd", method = RequestMethod.GET)
	public String setPwd(Model model) {
		SetPasswordModel setPasswordModel = new SetPasswordModel();
		model.addAttribute("setPasswordModel", setPasswordModel);
		return "setPassword";
	}

	@RequestMapping(value = "setPwd", method = RequestMethod.POST)
	public String setPwd(@Validated SetPasswordModel setPasswordModel, BindingResult bindingResult,
			@ModelAttribute("mailAddress") String mailAddress, HttpServletRequest request) {
		SetPasswordCommand setPasswordCommand = new SetPasswordCommand(setPasswordModel.getPassword(),
				setPasswordModel.getConfirmPassword(), (String) request.getSession().getAttribute("mailAddress"));
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