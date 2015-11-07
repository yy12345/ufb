package com.ufufund.uft.web.controller;

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
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.StudentVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="family/setting")
@Slf4j
public class SettingsController {
	private static final String CARD_INDEX="family/setting/card_index.htm";
	private static final String CARD_INDEX_NAME="我的银行卡";
	private static final String ACCOUNT_INDEX="family/setting/account_index.htm";
	private static final String PASSWORD_INDEX="family/setting/password_index.htm";
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private ChinapayManager chinapayManager;
	
	/**
	 * 账户信息
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="account_index")
	public String accountIndex(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdno(s_custinfo.getIdno());             
			//学生信息
			List<StudentVo> list=new ArrayList<StudentVo>();
		    List<Student> slists=custManager.queryStudentsByCustno(s_custinfo.getCustno());
		    if(slists.size()>0&&slists!=null){
		    	for(Student student:slists){
		    		StudentVo studentVo=new StudentVo();
		    		studentVo.setBirthday(student.getBirthday().substring(0, 4)+"年"+student.getBirthday().substring(5, 7)+"月"+student.getBirthday().substring(8, 10)+"日");
		    		studentVo.setName(student.getName());
		    		studentVo.setSex(student.getSex());
		    		String orgname=((StudentVo)custManager.queryOrgsByCid(student.getCid())).getOrganization();
		    		studentVo.setOrganization(orgname);
		    		list.add(studentVo);
		    	}
		    }
			model.addAttribute("CustinfoVo", custinfoVo);
			model.addAttribute("StudentList", list);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_url", ACCOUNT_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/account_index";
	}
	
	
	/**
	 * 密码设置
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="password_index")
	public String passwordIndex(CustinfoVo custinfoVo, Model model){
		return "family/setting/password_index";
	}
	
	/**
	 * 登录状态      修改登录密码
	 * @param password1
	 * @param model
	 * @return
	 */
 	@RequestMapping(value="update_loginPwd")
 	@ResponseBody
	public Map<String,Object> updateLoginPwd(String password0,String password1){
 		Map<String,Object> resultMap=new HashMap<String,Object>();
 		try{
			String custno=UserHelper.getCustno();
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(custno);
			changePasswordAction.setPassword1(password1);
			Custinfo custinfo=custManager.getCustinfo(custno);
			String password=custinfo.getLoginpwd();
			password0=EncryptUtil.md5(password0);
			if(!password.equals(password0)){
				resultMap.put("errCode", "0001");
				resultMap.put("errMsg", "原登录密码不正确！");
				return resultMap;
			}
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
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
	 * 修改交易密码
	 * @param password0
	 * @param password1
	 * @param password2
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update_tradePwd")
	@ResponseBody
	public Map<String,Object> updateTradePwd(String password0, String password1,String password2){
		String custno=UserHelper.getCustno();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try{
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADE");
			changePasswordAction.setCustno(custno);
			changePasswordAction.setPassword0(password0);
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			Custinfo custinfo=custManager.getCustinfo(custno);
			String tradePwd=custinfo.getTradepwd();
			password0=EncryptUtil.md5(password0);
			
			// 交易密码与原交易密码不同
			if(!password0.equals(tradePwd)){
				resultMap.put("errCode", "0001");
				return resultMap;
			}
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
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
	 * 找回交易密码
	 * @param password1
	 * @param password2
	 * @param msgcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="tradePwd_back")
	@ResponseBody
	public Map<String,Object> tradePwdBack(String password1, String password2){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADEBACK");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
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
	 * 修改登录密码    未登录状态step1
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="findpwd_index")
	public String findpwdIndex(AutotradeVo autotradeVo, Model model){
		try{
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改登录密码");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "family/setting/findpwd_index";
	}
	/**
	 * 修改登录密码   未登录状态step2
	 * 20151002
	 */
	@RequestMapping(value="findpwd_confirm")
	public String findpwdConfirm(CustinfoVo custinfoVo, Model model){
		try{
			
			model.addAttribute("CustInfoVo", custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改登录密码");
			model.addAttribute("message_url", "family/setting/findpwd_index.htm");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/findpwd_confirm";
	}
	/**
	 * 修改登录密码   未登录状态step3
	 * 20151002
	 */
	@RequestMapping(value="findpwd_success")
	@ResponseBody
	public Map<String,Object> findpwdSuccess(String mobileno,String password1, Model model){
		Map<String,Object> resultMap= new HashMap<String, Object>();
		try{
			Custinfo custinfo=custManager.getCustInfoByMobileno(mobileno);
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
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
	 * 修改登录密码结果   未登录状态
	 */
	@RequestMapping(value="findpwd_result")
	public String findpwdResult(AutotradeVo autotradeVo, Model model){
		return "family/setting/findpwd_success";
	}
	
		
	/**
	 * 银行卡管理
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="card_index")
	public String cardIndex(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo custinfo = UserHelper.getCustinfoVo();
			
			// 查询用户的银行卡信息
	        Bankcardinfo card = bankCardManager.getBankCardInfo(custinfo.getCustno());
			
			model.addAttribute("card", card);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", CARD_INDEX);
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/setting/card_index";
	}
	
	/**
	 * 银行卡，升级至幼富宝卡，首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "card_update")
	public String cardUpdate(Model model) {
		
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			
			// 业务规则校验
			boolean isUfb = false;
			
			// 获取用户是否已为幼富宝用户
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y");  
			tradeaccosts.add("N");  
			List<TradeAccoinfoOfMore> list=tradeAccoManager.getTradeAccoList(custinfoVo.getCustno(),null,tradeaccosts);
			if(list.size()>0&&null!=list){
				isUfb=true;
			}
			
			if(isUfb){
				throw new UserException("您已为幼富宝用户！");
			}
			
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			// 支持幼富宝的银行
			List<BankBaseInfo> ufbBankList= new ArrayList<BankBaseInfo>();
			for(BankBaseInfo bankinfo:bankBaseList){
				if("1".equals(bankinfo.getLevel())){
					ufbBankList.add(bankinfo);
				}
			}
			model.addAttribute("ufbBankList", ufbBankList);
			model.addAttribute("custinfoVo", custinfoVo);
			model.addAttribute("firstBank", ufbBankList.get(0));
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", CARD_INDEX);
			model.addAttribute("back_module", CARD_INDEX_NAME);
			return "error/error";
		}
		return "family/setting/card_update";
	}
	
	/**
	 * 升级银行卡，确认
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "card_result")
	public String cardResult(BankCardVo bankCardVo, Model model) {
		
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			String otherserial = (String) ServletHolder.getSession().getAttribute("otherserial");
			
			// 数据验证
			// 1.传入参数验证
			if(StringUtils.isBlank(bankCardVo.getBankno())||StringUtils.isBlank(bankCardVo.getBankacco())
					||StringUtils.isBlank(bankCardVo.getBankmobile())||StringUtils.isBlank(bankCardVo.getMobileautocode())){
				throw new UserException("您所填写的信息含空值！");
			}
			
			// 2.业务规则验证
			String banklevel = bankBaseManager.getLevelByBankno(bankCardVo.getBankno());
			if(!"1".equals(banklevel)){
				throw new UserException("您选择的银行卡，不支持幼富宝！");
			}
		
			// 对象组装
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			openAccountAction.setBankno(bankCardVo.getBankno());
			openAccountAction.setBankacnm(custinfoVo.getInvnm());
			openAccountAction.setBankacco(bankCardVo.getBankacco());
			openAccountAction.setBankidtp("0");
			openAccountAction.setBankidno(custinfoVo.getIdno());
			openAccountAction.setBankmobile(bankCardVo.getBankmobile());
			openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
			openAccountAction.setOtherserial(otherserial);
			openAccountAction.setCustno(custinfoVo.getCustno());
			
			// 幼富宝卡，海富通开户
			bankCardManager.openAccount3(openAccountAction);
			bankCardManager.openAccount4(openAccountAction);
			// 银联账户验证
			chinapayManager.checkAccount(openAccountAction);
			
			// 新幼富宝卡，基金开户成功，银联账户验证成功，添加bankcardinfo、tradeaccoinfo记录
			bankCardManager.updateCard(openAccountAction);
				
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "redirect:/family/setting/card_index.htm";
	}

}
