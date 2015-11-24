package com.ufufund.ufb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，账户相关业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="organ/account")
@Slf4j
public class OrganAccountController {

	/**
	 * 首页，登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model){
		return "organ/account/index";
	}
	
	/**
	 * 注册页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String register(Model model){
		
		// code ...
		
		return "organ/account/register";
	}
	
	/**
	 * 注册页，成功
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register_success", method=RequestMethod.GET)
	public String registerSuccess(Model model){
		
		// code ...
		
		return "redirect:organ/account/auth_index.htm";
	}
	
	/**
	 * 认证页，首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_index", method=RequestMethod.GET)
	public String authIndex(Model model){
		
		// code ...
		
		return "organ/account/auth_index";
	}
	
	/**
	 * 认证页，基本资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_base", method=RequestMethod.GET)
	public String authBase(Model model){
		
		// code ...
		
		return "organ/account/auth_base";
	}
	
	/**
	 * 认证页，资质照片上传
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_certpic", method=RequestMethod.GET)
	public String authCertpic(Model model){
		
		// code ...
		
		return "organ/account/auth_certpic";
	}
	
	/**
	 * 认证页，银行卡资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_card", method=RequestMethod.GET)
	public String auth_card(Model model){
		
		// code ...
		
		return "organ/account/auth_card";
	}
	
	/**
	 * 认证页，银行卡资料填写
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auth_result", method=RequestMethod.GET)
	public String authResult(Model model){
		
		// code ...
		
		return "organ/account/auth_result";
	}
	
}
