package com.app.Notas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginControllerTemplate {

    @GetMapping("/")
	public String LoginTemplatec(Model model) {
		return "login";
	}

}
