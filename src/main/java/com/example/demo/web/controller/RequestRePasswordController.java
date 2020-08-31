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
import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.web.form.RequestRePasswordForm;

@Controller
public class RequestRePasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "requestRePassword", method = RequestMethod.GET)
	public String requestRePassword(Model model) {
		RequestRePasswordForm requestRePasswordForm = new RequestRePasswordForm();
		model.addAttribute("requestRePasswordForm", requestRePasswordForm);
		return "/reqmail/requestRePassword";
	}

	@RequestMapping(value = "requestRePassword", method = RequestMethod.POST)
	public String requestedRePassword(@Validated RequestRePasswordForm requestRePasswordForm,
			BindingResult bindingResult, Model model, HttpServletRequest request)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RequestRePasswordCommand requestRePasswordCommand = new RequestRePasswordCommand(
				requestRePasswordForm.getMailAddress());
		try {
			applicationCommandBus.dispatch(requestRePasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1002");
			}
			return "/reqmail/requestRePassword";
		}
		request.getSession().setAttribute("mailAddress", requestRePasswordForm.getMailAddress());
		return "/reqmail/requestRePasswordComplete";
	}

}