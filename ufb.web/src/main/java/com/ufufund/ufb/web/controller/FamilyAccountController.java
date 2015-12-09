package com.ufufund.ufb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.ChinapayManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 家庭版注册、登录页
 * @author ayis
 * 2015年10月23日
 */
@Controller
@RequestMapping(value="family/account")
@Slf4j
public class FamilyAccountController {
	
	private static final String REGISTER_VO = "register_vo";
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private ChinapayManager chinapayManager;
	
	/**
	 * 家庭版：登录
	 * @param Custinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String,Object> login(Custinfo custinfo, Model model) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			// 参数校验
			VerifyCodeUtils.validate(custinfo.getVerifycode());
			// 登录
			 custinfo = custManager.loginIn(custinfo);
			// 保存用户会话
			UserHelper.saveCustinfo(custinfo);
			
			resultMap.put("errCode", "0000");
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
	 * 家庭版：退出登录
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(){
		ServletHolder.getSession().invalidate();
		return "redirect:/family/index.htm";
	}
	
	/**
	 * 家庭版：注册，首页-手机验证
	 * @param Custinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register_index")
	public String registerIndex(Custinfo custinfo, Model model){
		return "account/register_index";
	}
	
	/**
	 * 家庭版：注册，密码填写
	 * @param Custinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_passwd")
	public String registerPasswd(Custinfo custinfo, Model model) {
		try{
			// 后台验证手机验证码
			MsgCodeUtils.validate(custinfo.getMsgcode(), custinfo.getMobileno());
			ServletHolder.getSession().setAttribute(REGISTER_VO, custinfo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "account/register_passwd";
	}
	
	/**
	 * 家庭版：注册，绑定银行卡
	 * @param Custinfo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_card")
	public String registerCard(Custinfo custinfo,  Model model) {
		
		try{
			// 缓存表单提交数据
			Custinfo r_Custinfo = (Custinfo)ServletHolder.getSession().getAttribute(REGISTER_VO);
			if(r_Custinfo == null ){
				throw new UserException("您访问的页面已失效，请返回至首页，重新访问！");
			}else{
				r_Custinfo.setPasswd(custinfo.getPasswd());
				r_Custinfo.setTradepwd(custinfo.getTradepwd());
				r_Custinfo.setName(custinfo.getName());
				r_Custinfo.setIdno(custinfo.getIdno());
				ServletHolder.getSession().setAttribute(REGISTER_VO, r_Custinfo);
			}
			
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			// 支持幼富宝的银行
			List<BankBaseInfo> ufbBankList= new ArrayList<BankBaseInfo>();
			// 仅支持幼富通的银行
			List<BankBaseInfo> uftBankList= new ArrayList<BankBaseInfo>();
			for(BankBaseInfo bankinfo:bankBaseList){
				if("2".equals(bankinfo.getLevel())){
					uftBankList.add(bankinfo);
				}
				else if("1".equals(bankinfo.getLevel())){
					ufbBankList.add(bankinfo);
				}
			}
			 
			model.addAttribute("ufbBankList", ufbBankList);
			model.addAttribute("uftBankList", uftBankList);
			model.addAttribute("Custinfo", r_Custinfo);
			model.addAttribute("firstBank", ufbBankList.get(0));
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "account/register_card";
	}
	
	/**
	 * 家庭版：注册，成功页
	 * @param Custinfo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_success")
	public String registerSuccess(BankCardVo bankCardVo, Model model) {
		
		try{
			// 缓存表单提交数据
			Custinfo r_custinfo = (Custinfo)ServletHolder.getSession().getAttribute(REGISTER_VO);
			String otherserial = (String) ServletHolder.getSession().getAttribute("otherserial");
			if(r_custinfo == null ){
				throw new UserException("您访问的页面已失效，请返回至首页，重新访问！");
			}
			
			// 注册信息验证
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(r_custinfo.getMobileno());
			registerAction.setPasswd(r_custinfo.getPasswd());
			registerAction.setTradepwd(r_custinfo.getTradepwd());
			registerAction.setIdno(r_custinfo.getIdno());
			registerAction.setState("1");
			custManager.validateFamily(registerAction);
			
			// 对象组装
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			openAccountAction.setBankno(bankCardVo.getBankno());
			openAccountAction.setBanknm(r_custinfo.getName());
			openAccountAction.setBankacco(bankCardVo.getBankacco());
			openAccountAction.setCerttype("0");
			openAccountAction.setCertno(r_custinfo.getIdno());
			openAccountAction.setMobile(bankCardVo.getMobile());
			openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
			openAccountAction.setOtherserial(otherserial);
			
			String banklevel = bankBaseManager.getLevelByBankno(openAccountAction.getBankno());
			// 幼富宝卡，海富通开户
			if("1".equals(banklevel)){
				bankCardManager.openAccount3(openAccountAction);
				bankCardManager.openAccount4(openAccountAction);
			}
			
			// 银联账户验证
			chinapayManager.checkAccount(openAccountAction);
			
			// 用户注册
			openAccountAction.setName(r_custinfo.getName());
			openAccountAction.setIdtp("0");
			openAccountAction.setIdno(r_custinfo.getIdno());
			String custno = custManager.register(registerAction, openAccountAction);
			
			// 注册成功，保存用户至session
			Custinfo custinfo = custManager.getCustinfo(custno);
			UserHelper.saveCustinfo(custinfo);
			ServletHolder.getSession().removeAttribute(REGISTER_VO);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "account/register_success";
	}
}
