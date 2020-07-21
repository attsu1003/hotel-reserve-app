package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.model.LoginModel;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String reserve(Model model) {
		LoginModel loginModel = new LoginModel();
		model.addAttribute("loginModel", loginModel);
		model.addAttribute("message", "ログイン画面");
		return "login";
	}

	@RequestMapping(value = "/registUser", method = RequestMethod.GET)
	public String showRegistMemberForm(Model model) {

		model.addAttribute(new LoginModel());

		// 会員情報入力画面。
		return "registUser";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String reserve(Model model, RedirectAttributes attributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();// get logged in username
		model.addAttribute("message", "メニュー画面");
		model.addAttribute("username", name);
		return "menu";
	}
}