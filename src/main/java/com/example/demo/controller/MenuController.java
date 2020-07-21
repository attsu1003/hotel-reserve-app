package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MenuController {

//	@RequestMapping(value = "/menu", method = RequestMethod.GET)
//	public String reserve(Model model, RedirectAttributes attributes) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String name = auth.getName();// get logged in username
//		model.addAttribute("message", "メニュー画面");
//		model.addAttribute("username", name);
//		return "menu";
//	}
}
