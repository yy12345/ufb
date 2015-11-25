package com.ufufund.ufb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，账户相关业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="corp/uft")
@Slf4j
public class UftCorpController {
	
	/**
	 * 机构版，幼富通首页
	 */
	@RequestMapping(value="uft_index")
	public String uftIndex(Model model){
		
		return "";
	}

	/**
	 * 机构版，收费明细
	 */
	@RequestMapping(value="records")
	public String records(Model model){
		
		return "";
	}
}
