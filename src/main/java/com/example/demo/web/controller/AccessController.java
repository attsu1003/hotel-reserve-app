package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessController {
	
	@RequestMapping(value = "access", method = RequestMethod.GET)
	public String changePassword(Model model) {
		return "/access";
	}

}
