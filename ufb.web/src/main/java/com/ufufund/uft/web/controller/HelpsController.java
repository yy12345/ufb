package com.ufufund.uft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="family/help")
@Slf4j
public class HelpsController {
	//帮助中心--关于幼富通
	@RequestMapping(value="aboutuft")
	public String aboutuft(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/aboutuft";
		}
		return "family/help/aboutuft";
	}
	//帮助中心--收益
	@RequestMapping(value="benefits")
	public String benefits(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/benefits";
		}
		return "family/help/benefits";
	}
	//帮助中心--退费
	@RequestMapping(value="payment")
	public String payment(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/payment";
		}
		return "family/help/payment";
	}
	//帮助中心--安全性
	@RequestMapping(value="security")
	public String security(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/security";
		}
		return "family/help/security";
	}
	 // 帮助中心--充值取现
	@RequestMapping(value="cashandPay")
	public String cashandPay(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/cashandPay";
		}
		return "family/help/cashandPay";
	}
	// 帮助中心--常见问题
	@RequestMapping(value="question")
	public String question(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/question";
		}
		return "family/help/question";
	}
	// 帮助中心--联系我们
	@RequestMapping(value="contact")
	public String contact(Model model){
		try {
			
		} catch (BizException e) {
			return "family/help/contact";
		}
		return "family/help/contact";
	}
}
