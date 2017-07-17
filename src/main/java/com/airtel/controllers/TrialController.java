package com.airtel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/trial")
public class TrialController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String getStaticPage(){
		return "redirect:/static/final.html";
	}
}
