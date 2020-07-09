package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String reserve(RedirectAttributes attributes) {
//		attributes.addFlashAttribute("message", "メニュー画面");
//		return "redirect:/menu";
//	}
	
//	@RequestMapping(value="/menu", method = RequestMethod.GET)
//	  public String printUser(Model model, RedirectAttributes attributes) {
//	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	      String name = auth.getName();//get logged in username
//	      attributes.addFlashAttribute("message", "メニュー画面");
//	      attributes.addFlashAttribute("username", name);
//	      return "redirect:/menu";
//	  }
}
