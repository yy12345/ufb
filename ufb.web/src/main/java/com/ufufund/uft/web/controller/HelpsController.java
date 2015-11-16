package com.ufufund.uft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="family/help")
public class HelpsController {
	/**
	 * 帮助中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{page}")
	public String index(Model model, @PathVariable ("page") String page) {
		return "family/help/" + page;
	}
}
