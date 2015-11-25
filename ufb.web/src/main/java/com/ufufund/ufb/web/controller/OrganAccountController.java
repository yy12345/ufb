package com.ufufund.ufb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.OrganManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.OrgCodesMapper;
import com.ufufund.ufb.model.db.OrgCodes;
import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.MsgCodeUtils.MsgCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构版，账户相关业务模块
 * @author ayis
 * 2015年11月24日
 */
@Controller
@RequestMapping(value="corp/account")
@Slf4j
public class OrganAccountController {
	@Autowired
	private OrganManager organManager;
	@Autowired
	private OrgCodesMapper orgCodesMapper;

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
	 * 首页，登录
	 * @param orginfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String,Object> login(Orginfo orginfo, Model model) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 注册页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String register(Model model){
		
		// code ...
		//邀请码校验
		
		return "organ/account/register";
	}
	
	/**
	 * 校验手机号是否注册
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="isRegister")
	@ResponseBody
	public Map<String,Object> isRegister(String mobile){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			Orginfo orginfo = new Orginfo();
			orginfo.setOperator_mobile(mobile);
			boolean result=organManager.isMobileRegister(orginfo);
			resultMap.put("errCode", "0000");
			resultMap.put("isRegister", result);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 发送手机验证码
	 * @param mobileno
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "msgcode_send")
	@ResponseBody
	public Map<String,Object> msgcodeSend(String mobile, String code) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(StringUtils.isBlank(mobile)||StringUtils.isBlank(code)){
				throw new BizException("参数为空！");
			} 
			
			OrgCodes orgCodes = new OrgCodes();
			orgCodes.setCode(code);
			List<OrgCodes> orgcodeList = orgCodesMapper.getOrgCodeList(orgCodes);
			if(orgcodeList.size()==0){
				resultMap.put("errCode", "0001");
				resultMap.put("errMsg", "邀请码不正确！");
				return resultMap;
			}
			
			MsgCodeUtils.sendMsg(mobile, "0J001");
			resultMap.put("errCode", "0000");
			
			// this is test code ... removed later...
			MsgCode msgcode = (MsgCode)ServletHolder.getSession().getAttribute("MSGCODE");
			resultMap.put("msgcode", msgcode.getMsgCode());
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
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
