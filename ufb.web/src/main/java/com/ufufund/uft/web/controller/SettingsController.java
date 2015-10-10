package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.StudentVo;
import com.ufufund.ufb.model.vo.TradeAccoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;


@Controller
public class SettingsController {
	private static final Logger LOG = LoggerFactory.getLogger(SettingsController.class);
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private AutotradeManager autotradeManager;
	@Autowired
	private WorkDayManager workDayManager;
	
	@RequestMapping(value="family/settingAccount")
	public String setAccount(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
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
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAccount";
		}
		return "family/ufb/settingAccount";
	}
	
	/**
	 * 添加自动充值第一步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/autoTrade_add")
	public String autoTradeAdd(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("curCard", tradeAccoList.get(0));
				model.addAttribute("cardList", tradeAccoList);
			}
			
			// 从第二步返回
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				// autotradeVo.getTofundcorpno()
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/settingAutoFund"; 
		}
		return "family/ufb/autoFundStep1";
	}
	/**
	 * 自动充值第二步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/autoTrade_preview")
	public String autoTradePreview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			// 充值周期
			autotradeVo.setCycle("MM");
			String nextdate = autotradeManager.getNextdate(autotradeVo.getCycle(), autotradeVo.getDat());
			nextdate=nextdate.substring(0,4)+"年"+nextdate.substring(4, 6)+"月"+nextdate.substring(6, 8)+"日";
			autotradeVo.setNextdate(nextdate);
			// 获取交易账户列表==20151001
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
						
				if(null != tradeAccoList && tradeAccoList.size() > 0){
					model.addAttribute("curCard", tradeAccoList.get(0));
					model.addAttribute("cardList", tradeAccoList);
				}
				//20151001===
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/autoTrade_add.htm");
			return "error/user_error";
		}
		return "family/ufb/autoFundStep2";
	}
	/**
	 * 自动充值第三步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/autoTrade_result")
	public String autoTradeResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			AddAutotradeAction action = new AddAutotradeAction();
			// 用户信息
			action.setCustno(s_custinfo.getCustno());
			// 银行卡
			action.setFrombankserialid(autotradeVo.getFrombankserialid());	
			// 货币信息
			action.setTofundcorpno(autotradeVo.getTofundcorpno());
			action.setTofundcode(autotradeVo.getTofundcode());
			action.setTochargetype("A");
			// 交易类型
			action.setTradetype(AutoTradeType.AUTORECHARGE);
			// 充值周期
			action.setType("E");
			action.setCycle("MM");
			action.setDat(autotradeVo.getDat());
			action.setNextdate(autotradeVo.getNextdate());
			// 充值金额
			action.setAutoamt(autotradeVo.getAutoamt());
			// 备注
			action.setSummary(autotradeVo.getSummary());
			// 交易密码
			action.setTradepwd(autotradeVo.getTradepwd());
			//申请的时间
			Calendar c = Calendar.getInstance();
		    c.add(Calendar.DATE, -0);
		    String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
		    //下次充值时间
		    String nextdate=autotradeVo.getNextdate();
		    
			model.addAttribute("today",today);
			model.addAttribute("nextWorkDay",nextdate);
			model.addAttribute("autoamt",autotradeVo.getAutoamt());
			model.addAttribute("summary",autotradeVo.getSummary());
			autotradeManager.addAutotrade(action);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("AutoTradeVo", autotradeVo);
			ServletHolder.forward("family/autoFundStep2");
			return "family/ufb/autoFundStep2";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "错误信息");
			model.addAttribute("message_url", "family/autoTrade_add.htm");
			model.addAttribute("message_content0", "新增自动充值计划失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "重新添加自动充值计划");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的自动充值计划，提交失败，您可通过自动充值计划列表确认，如有问题请联系幼富通客服热线。");
			return "error/pay_result";
		}
		return "family/ufb/autoFundStep3";
	}
	/**
	 * 设置密码
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/settingPassword")
	public String setPassword(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			model.addAttribute("TAB", "1");
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingPassword";
		}
		return "family/ufb/settingPassword";
	}
	/**
	 * 修改登录密码step1
	 * 20151002
	 */
	@RequestMapping(value="family/findPasswordStep1")
	public String findPasswordStep1(AutotradeVo autotradeVo, Model model){
		try{
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "family/ufb/findPasswordStep1";
	}
	/**
	 * 修改登录密码step2
	 * 20151002
	 */
	@RequestMapping(value="family/findPasswordStep2")
	public String findPasswordStep2(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo=UserHelper.getCustinfoVo();
			// 校验手机验证码
		//	MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			//手机号是否需要验证与注册的时候的一致？？？
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno", e.getMessage()); //手机号 
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode", e.getMessage()); // 手机验证码
			} else {
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/ufb/findPasswordStep1";
		}
		return "family/ufb/findPasswordStep2";
	}
	/**
	 * 修改登录密码step3
	 * 20151002
	 */
	@RequestMapping(value="family/findPasswordStep3")
	public String findPasswordStep3(String password1, Model model){
		try{
			CustinfoVo s_custinfo=UserHelper.getCustinfoVo();
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
				
			model.addAttribute("CustinfoVo", s_custinfo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
				model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "family/ufb/findPasswordStep3";
	}
	/**
	 * 修改交易密码
	 * @param password0
	 * @param password1
	 * @param password2
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/settingTradePwd")
	public String setTradePwd(String password0, String password1, String password2, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "2");
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADE");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword0(password0);
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("TAB", "2S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.TRADEPWD0.equals(ems)){
				model.addAttribute("errMsg_trade_password0", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_trade_password1", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_trade_password2", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("trade_password0", password0);
			model.addAttribute("trade_password1", password1);
			model.addAttribute("trade_password2", password2);
			model.addAttribute("CustinfoVo", s_custinfo);
			return "family/ufb/settingPassword";
		}
		return "family/ufb/settingPassword";
	}
	
	/**
	 * 获取手机验证码
	 * @param
	 * @return
	 */
	@RequestMapping(value = "family/getMsgCode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> sendMsgCode(String verifycode){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// 校验验证码
				VerifyCodeUtils.validate(verifycode);
				
				// 查询手机号是否注册
				boolean isMobileRegister = custManager.isMobileRegister(s_custinfo.getMobileno());
				if(!isMobileRegister){
					// 错误 手机号未注册
					resultMap.put("errCode", "errMsg_mobileno");
					resultMap.put("errMsg", "手机号未注册");
				}else{
					// 获取手机验证码
					String template = "";
					// 发送短信
					MsgCodeUtils.sendMsg(template, s_custinfo.getMobileno());
					resultMap.put("errCode", "0000");
					resultMap.put("errMsg", "短信已发送");
					//TODO 测试用
					resultMap.put("TODO", MsgCodeUtils.getMsgCode());
				}
			}else{
				//TODO 错误
				resultMap.put("errCode", "errMsg_mobileno");
				resultMap.put("errMsg", "登录超时!");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems)) {
				resultMap.put("errCode", "errMsg_mobileno");
				resultMap.put("errMsg", e.getMessage());
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				resultMap.put("errCode", "errMsg_verifycode");
				resultMap.put("errMsg", e.getMessage());
			}else{
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", e.getMessage());
			}
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="family/settingTradePwdBack")
	public String setTradePwdBack(String password1, String password2, String msgcode, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "3");
			// 校验手机验证码
			MsgCodeUtils.validate(msgcode, s_custinfo.getMobileno());
			
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADEBACK");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("TAB", "3S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_trade_password1_back", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_trade_password2_back", e.getMessage());
			}else
			if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("msgcode", msgcode);
			model.addAttribute("trade_password1", password1);
			model.addAttribute("trade_password2", password2);
			model.addAttribute("CustinfoVo", s_custinfo);
			return "family/ufb/settingPassword";
		}
		return "family/ufb/settingPassword";
	}
	/**
	 * 银行卡管理
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/settingCard")
	public String setCard(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			if("0".equals(s_custinfo.getInvtp())){
				levels.add("0"); // 
			}else{
				levels.add("1"); // 
				levels.add("2"); // 
			}
			List<TradeAccoinfoOfMore> tradeAccoList_Y = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, levels, tradeaccosts);
			if(null != tradeAccoList_Y && tradeAccoList_Y.size() > 0){
				// 获取用户总资产
				Assets assets = queryManager.queryAssets(tradeAccoList_Y, null);
				List<TradeAccoVo> list_y =  assets.getAccoList();
				
				model.addAttribute("cardList_Y", list_y);
			} else {
				model.addAttribute("cardList_Y", null);
			}
			tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("C"); // 
			tradeaccosts.add("F"); // 
			List<TradeAccoinfoOfMore> tradeAccoList_N = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, levels, tradeaccosts);
			if(null != tradeAccoList_N && tradeAccoList_N.size() > 0){
				model.addAttribute("cardList_N", tradeAccoList_N);
			} else {
				model.addAttribute("cardList_N", null);
			}
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingCard";
		}
		return "family/ufb/settingCard";
	}
	

	@RequestMapping(value="family/settingMainCard")
	public String setMainCard(String bankacco, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			// 短信验证
			bankCardManager.setBankCardMainFlag(
					s_custinfo.getCustno(), 
					null, 
					"N");
			bankCardManager.setBankCardMainFlag(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("bankacco"), 
					"Y");
			
			ServletHolder.forward("/family/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingCard";
		}
		return "family/ufb/settingCard";
	}
	
	@RequestMapping(value="family/settingUnbindCard")
	public String setUnbindCard(String serialid, String tradeacco, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 短信验证
			TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco, null);
			BigDecimal total = tradeAccoVo.getTotal();
			BigDecimal available = tradeAccoVo.getAvailable();
			BigDecimal realavailable = tradeAccoVo.getRealavailable();
			BigDecimal frozen = tradeAccoVo.getFrozen();
			
			if (total.compareTo(BigDecimal.ZERO) > 0
					|| available.compareTo(BigDecimal.ZERO) > 0
					|| realavailable.compareTo(BigDecimal.ZERO) > 0
					|| frozen.compareTo(BigDecimal.ZERO) > 0) {
				
				throw new UserException("对不起，您的银行卡有资金交易，暂时不能解绑！");
			}
			bankCardManager.unbindBankCard(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("serialid"), 
					"C");
			
			ServletHolder.forward("/family/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			return "family/ufb/settingCard";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("returnUrl", "family/settingCard.htm");
			return "error/user_error";
		}
		return "family/ufb/settingCard";
	}
	
	@RequestMapping(value="family/settingActiveCard")
	public String setActiveCard(String serialid, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			// 短信验证
			bankCardManager.unbindBankCard(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("serialid"), 
					"Y");
			
			ServletHolder.forward("/family/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingCard";
		}
		return "family/ufb/settingCard";
	}
	@RequestMapping(value="family/deleteCard")
	public String deleteCard(String serialid, String tradeacco, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 短信验证
			TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco, null);
			BigDecimal total = tradeAccoVo.getTotal();
			BigDecimal available = tradeAccoVo.getAvailable();
			BigDecimal realavailable = tradeAccoVo.getRealavailable();
			BigDecimal frozen = tradeAccoVo.getFrozen();
			
			if (total.compareTo(BigDecimal.ZERO) > 0
					|| available.compareTo(BigDecimal.ZERO) > 0
					|| realavailable.compareTo(BigDecimal.ZERO) > 0
					|| frozen.compareTo(BigDecimal.ZERO) > 0) {
				
				throw new UserException("对不起，您的银行卡有资金交易，暂时不能删除！");
			}
			bankCardManager.deleteCard(
					s_custinfo.getCustno(), 
					serialid);
			ServletHolder.forward("/family/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			return "family/ufb/settingCard";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("returnUrl", "family/settingCard.htm");
			return "error/user_error";
		}
		return "family/ufb/settingCard";
	}
	/**
	 * 自动业务管理
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/settingAutoFund")
	public String settingAutoFund(CustinfoVo custinfoVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 获取自动充值计划列表
			List<Autotrade> list = autotradeManager.getAutotradeList(s_custinfo.getCustno());
			List<Autotrade> clist=autotradeManager.getAutotradeCList(s_custinfo.getCustno());
			 
			model.addAttribute("LIST", list);
			model.addAttribute("CLIST", clist);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAutoFund";
		}
		return "family/ufb/settingAutoFund";
	}
	
	@RequestMapping(value="family/autoTrade_update")
	public String autoTradeUpdate(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			//List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("cardList", tradeAccoList);
			}
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			if("u2".equals(autotradeVo.getStep())){
				model.addAttribute("AutoTradeVo", autotradeVo);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(autotradeVo.getAutoid());
				model.addAttribute("AutoTradeVo", autotrade);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAutoFund";
		}
		return "family/ufb/autoFundStepU1";
	}
	
	@RequestMapping(value="family/autoTradeUpdate_preview")
	public String autoTradeUpdate_preview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			// 充值周期
			autotradeVo.setCycle("MM");
			String nextdate = autotradeManager.getNextdate(autotradeVo.getCycle(), autotradeVo.getDat());
			autotradeVo.setNextdate(nextdate);
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/settingAutoFund.htm");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepU2";
	}
	
	@RequestMapping(value="family/autoTradeUpdate_result")
	public String autoTradeUpdateResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ModifyAutotradeAction action = new ModifyAutotradeAction();
			// autoid
			action.setAutoid(autotradeVo.getAutoid());
			// 用户信息
			action.setCustno(s_custinfo.getCustno());
			// 银行卡
			action.setFrombankserialid(autotradeVo.getFrombankserialid());	
			// 货币信息
			action.setTofundcorpno(autotradeVo.getTofundcorpno());
			action.setTofundcode(autotradeVo.getTofundcode());
			action.setTochargetype("A");
			// 交易类型
			action.setTradetype(AutoTradeType.AUTORECHARGE);
			// 充值周期
			action.setType("E");
			action.setCycle("MM");
			action.setDat(autotradeVo.getDat());
			action.setNextdate(autotradeVo.getNextdate());
			// 充值金额
			action.setAutoamt(autotradeVo.getAutoamt());
			// 备注
			action.setSummary(autotradeVo.getSummary());
			// 交易密码
			action.setTradepwd(autotradeVo.getTradepwd());
			
			autotradeManager.modifyAutotrade(action);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("AutoTradeVo", autotradeVo);
			return "setting/autoFundStepU2";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "错误信息");
			model.addAttribute("message_url", "family/settingAutoFund.htm");
			model.addAttribute("message_content0", "修改自动充值计划失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回自动充值计划");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的自动充值计划，提交失败，您可通过自动充值计划列表确认，如有问题请联系幼富通客服热线。");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepU3";
	}
	
	@RequestMapping(value="family/autoTradeStatus_update")
	public String autoTradeStatusUpdate(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			if(null != s_custinfo){
				String custno = UserHelper.getCustno();
				
				ChangeAutoStateAction action = new ChangeAutoStateAction();
				action.setAutoid(autotradeVo.getAutoid());
				action.setState(Constant.Autotrade.STATE$P); //STATE$N,STATE$P,STATE$C
				
				autotradeManager.changestatus(action);
				// 跳转确认页
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("SessionVo", s_custinfo);
			return "family/ufb/settingAutoFund";
		}
		ServletHolder.forward("/family/settingAutoFund.htm");
		return "family/ufb/settingAutoFund";
	}
	
	
	@RequestMapping(value="family/autoTrade_pause")
	public String autoTradePause(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			if("u2".equals(autotradeVo.getStep())){
				model.addAttribute("AutoTradeVo", autotradeVo);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(autotradeVo.getAutoid());
				model.addAttribute("AutoTradeVo", autotrade);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAutoFund";
		}
		return "family/ufb/autoFundStepP1";
	}
	
	@RequestMapping(value="family/autoTradePause_preview")
	public String autoTradePause_preview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/settingAutoFund.htm");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepP2";
	}
	
	@RequestMapping(value="family/autoTradePause_result")
	public String autoTradePauseResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			
			action.setCustno(s_custinfo.getCustno());// 用户信息
			action.setAutoid(autotradeVo.getAutoid());
			action.setState(Constant.Autotrade.STATE$P); //STATE$N,STATE$P,STATE$C
			action.setTradepwd(autotradeVo.getTradepwd());
			autotradeManager.changestatus(action);
		}catch (BizException e){
//			LOG.warn(e.getCodeMsg());
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/settingAutoFund.htm");
			return "error/user_error";
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "错误信息");
			model.addAttribute("message_url", "family/settingAutoFund.htm");
			model.addAttribute("message_content0", "暂停自动充值计划失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回自动充值计划");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的自动充值计划，提交失败，您可通过自动充值计划列表确认，如有问题请联系幼富通客服热线。");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepP3";
	}
	/**
	 * 终止自动充值计划
	 */
	@RequestMapping(value="family/autoTrade_stop")
	public String autoTrade_stop(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			if("u2".equals(autotradeVo.getStep())){
				model.addAttribute("AutoTradeVo", autotradeVo);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(autotradeVo.getAutoid());
				model.addAttribute("AutoTradeVo", autotrade);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAutoFund";
		}
		return "family/ufb/autoFundStepS1";
	}
	
	@RequestMapping(value="family/autoTradeStop_preview")
	public String autoTradeStop_preview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/settingAutoFund.htm");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepS2";
	}
	
	@RequestMapping(value="family/autoTradeStop_result")
	public String autoTradeStopResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			
			action.setCustno(s_custinfo.getCustno());// 用户信息
			action.setAutoid(autotradeVo.getAutoid());
			action.setState(Constant.Autotrade.STATE$C); //STATE$N,STATE$P,STATE$C
			action.setTradepwd(autotradeVo.getTradepwd());
			autotradeManager.changestatus(action);
		}catch (BizException e){
//			LOG.warn(e.getCodeMsg());
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/settingAutoFund.htm");
			return "error/user_error";
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "错误信息");
			model.addAttribute("message_url", "family/settingAutoFund.htm");
			model.addAttribute("message_content0", "暂停自动充值计划失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回自动充值计划");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的自动充值计划，提交失败，您可通过自动充值计划列表确认，如有问题请联系幼富通客服热线。");
			return "error/user_error";
		}
		return "family/ufb/autoFundStepS3";
	}
	/**
	 * 删除自动充值计划
	 */
	@RequestMapping(value="family/autoTrade_delete")
	public String autoTrade_delete(AutotradeVo autotradeVo, Model model){
		try{
			String custno = UserHelper.getCustno();
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				autotradeManager.deleteAutotrade(custno, frombankserialid, autotradeVo.getAutoid());
			}
		 
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/ufb/settingAutoFund";
		}
		ServletHolder.forward("/family/settingAutoFund.htm");
		return "family/ufb/settingAutoFund";
	}
	//帮助中心--关于幼富通
	@RequestMapping(value="family/helpAbout")
	public String helpAbout(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpAbout";
		}
		return "family/ufb/helpAbout";
	}
	//帮助中心--收益
	@RequestMapping(value="family/helpBenefits")
	public String helpBenefits(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpBenefits";
		}
		return "family/ufb/helpBenefits";
	}
	//帮助中心--退费
	@RequestMapping(value="family/helpFee")
	public String helpFee(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpFee";
		}
		return "family/ufb/helpFee";
	}
	//帮助中心--安全性
	@RequestMapping(value="family/helpSecurity")
	public String helpSecurity(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpSecurity";
		}
		return "family/ufb/helpSecurity";
	}
	 // 帮助中心--充值取现
	@RequestMapping(value="family/helpCashandPay")
	public String helpCashandPay(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpCashandPay";
		}
		return "family/ufb/helpCashandPay";
	}
	// 帮助中心--常见问题
	@RequestMapping(value="family/helpQA")
	public String helpQA(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpQA";
		}
		return "family/ufb/helpQA";
	}
	// 帮助中心--联系我们
	@RequestMapping(value="family/helpContact")
	public String helpContact(Model model){
		try {
			
		} catch (BizException e) {
			return "family/ufb/helpContact";
		}
		return "family/ufb/helpContact";
	}
}
