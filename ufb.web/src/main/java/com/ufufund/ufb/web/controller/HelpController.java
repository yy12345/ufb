package com.ufufund.ufb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {
	/**
	 * 家庭版帮助中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/help/{page}")
	public String index(Model model, @PathVariable ("page") String page) {
		return "help/" + page;
	}
}
