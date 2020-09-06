package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.application.command.RequestContactCommand;
import com.example.demo.web.form.RequestContactForm;

@Controller
public class RequestContactController {

	@Autowired
	ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String requestContact(Model model) {
		RequestContactForm requestContactForm = new RequestContactForm();
		model.addAttribute("requestContactForm", requestContactForm);
		return "/contact";
	}

	@RequestMapping(value = "contact", method = RequestMethod.POST)
	public String requestContact(@Validated RequestContactForm requestContactForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

		}
		RequestContactCommand requestContactCommand = new RequestContactCommand(requestContactForm.getName(),
				requestContactForm.getMailAddress(), requestContactForm.getCategory(),
				requestContactForm.getContents());
		try {
			applicationCommandBus.dispatch(requestContactCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleteMemberComplete";
	}
}
