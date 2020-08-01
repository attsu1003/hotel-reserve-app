package com.example.demo.controller;

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
			@ModelAttribute("mailAddress") String mailAddress) {
		SetPasswordCommand setPasswordCommand = new SetPasswordCommand(setPasswordModel.getPassword(),
				setPasswordModel.getConfirmPassword());
		try {
			System.out.println(mailAddress);
			applicationCommandBus.dispatch(setPasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof PasswordNotMatchException) {
				addErrorMessage("MSGE1003");
				return "setPassword";
			}
		}
		return "setPassword";
	}
}