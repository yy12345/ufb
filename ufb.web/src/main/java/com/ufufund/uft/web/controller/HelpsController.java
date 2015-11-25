package com.ufufund.uft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpsController {
	/**
	 * 家庭版帮助中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/help/{page}")
	public String index(Model model, @PathVariable ("page") String page) {
		return "family/help/" + page;
	}
	
	/**
	 * 机构版帮助中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "corp/help/{page}")
	public String indexCorp(Model model, @PathVariable ("page") String page) {
		return "organ/help/" + page;
	}
}
