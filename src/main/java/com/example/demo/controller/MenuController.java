package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.model.LoginModel;

@Controller
public class MenuController {

	@RequestMapping(value = "/menu")
	public String reserve(@Validated LoginModel loginModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String name = auth.getName();// get logged in username
//		model.addAttribute("message", "メニュー画面");
//		model.addAttribute("username", name);
		System.out.println("through menu");
		return "menu";
	}
}
