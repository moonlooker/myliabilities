package com.myliabilities.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myliabilities.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private UserService us = new UserService();

	@RequestMapping( value = "login")
	public String login( Model model) {
		return "login";
	}

	@PostMapping( value = "submit" )
	public String submit( @RequestParam( "username" ) String userName) {
		boolean result = us.login(userName);
		if (result) {
			log.info("用户[{}]登录",userName);
			return "redirect:/accountbook";
		} else {
			return "redirect:/register";
		}
	}
	
	@RequestMapping( value = "register")
	public String registerweb( Model model) {
		return "register";
	}
	
	@RequestMapping( value = "doregister" )
	public String doregister( @RequestParam( "username" ) String userName,@RequestParam( "nickname" ) String nickName) {
		boolean result = us.register(userName, nickName);
		if (result) {
			log.info("用户[{}]注册成功",userName);
			return "redirect:/accountbook";
		} else {
			return "redirect:/register";
		}
	}
}
