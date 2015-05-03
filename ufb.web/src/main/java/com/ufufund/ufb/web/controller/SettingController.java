package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;


@Controller
public class SettingController {
	private static final Logger LOG = LoggerFactory.getLogger(SettingController.class);
	
	@Autowired
	private CustManager custManager;
	
	@RequestMapping(value="setting/settingAccount")
	public String getRegistPage(CustinfoVo custinfoVo, Model model){
		
		
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		if(null != s_custinfo){
			// Session登录
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrganization(s_custinfo.getOrganization()); 
			custinfoVo.setBusiness(s_custinfo.getBusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			custinfoVo.setOpenaccount(s_custinfo.getOpenaccount());
		}
		
		model.addAttribute("CustinfoVo", custinfoVo);
		return "setting/account";
	}
	
}
