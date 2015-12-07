package com.ufufund.ufb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.OrganManager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="admin")
@Slf4j
public class AdaminController {

	@Autowired
	private OrganManager organManager;
	
	@RequestMapping(value="createCode")
	public String createCode(int n){
		organManager.createCodes(10);
		return "";
	}
}
