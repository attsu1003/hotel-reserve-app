package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.application.ApplicationCommandBus;
import com.example.demo.domain.model.MemberModel;
import com.example.demo.domain.model.RequestRePasswordModel;

@Controller
public class DeleteMemberController {

	@Autowired
	private ApplicationCommandBus applicationCommandBus;

	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public String deleteMember(Model model) {
		return "deleteMember";
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(@Validated RequestRePasswordModel requestRePasswordModel, BindingResult bindingResult,
			Model model) {
		return "deleteMember";
	}
}
