package com.example.demo.controller;

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
import com.example.demo.domain.model.RequestRePasswordModel;

@Controller
public class RequestRePasswordController extends AbstractController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "requestRePassword", method = RequestMethod.GET)
	public String requestRePassword(Model model) {
		RequestRePasswordModel requestRePasswordModel = new RequestRePasswordModel();
		model.addAttribute("requestRePasswordModel", requestRePasswordModel);
		return "requestRePassword";
	}

	@RequestMapping(value = "requestRePassword", method = RequestMethod.POST)
	public String requestedRePassword(@Validated RequestRePasswordModel requestRePasswordModel,
			BindingResult bindingResult, Model model, HttpServletRequest request)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RequestRePasswordCommand requestRePasswordCommand = new RequestRePasswordCommand(
				requestRePasswordModel.getMailAddress());
		try {
			applicationCommandBus.dispatch(requestRePasswordCommand);
		} catch (Exception e) {
			if (e.getCause() instanceof MemberNotFoundException) {
				addErrorMessage("MSGE1002");
			}
			return "requestRePassword";
		}
		request.getSession().setAttribute("mailAddress", requestRePasswordModel.getMailAddress());
		return "requestRePasswordComplete";
	}

}