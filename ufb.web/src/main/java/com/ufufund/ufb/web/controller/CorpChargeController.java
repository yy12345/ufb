package com.ufufund.ufb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，机构收费、退费模块
 * 2015年12月04日
 */
@Controller
@RequestMapping(value="corp/charge")
@Slf4j
public class CorpChargeController {
	
	public String priceIndex(Model model){
		
		return "organ/charge/price_index";
	}

}
