package com.ufufund.ufb.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
//import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FundInfo;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class CustController {
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private FundManager fundManager;
	
	/**
	 * 注册页
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register/index")
	public String getRegistPage(CustinfoVo custinfoVo, Model model){
		try{
			// 清除Session
			UserHelper.removeCustinfoVo();
			
			// 初始化数据
			custinfoVo.setInvtp(Invtp.PERSONAL.getValue()); //个人注册开户
			custinfoVo.setLevel(Level.PERSONAL.getValue()); //家庭身份
			
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
//		}catch(UserException ue){
//			LOG.warn(ue.getCodeMsg());
//			model.addAttribute("errorMsg", ue.getMessage());
//			model.addAttribute("returnUrl", "home/index.htm");
//			return "error/user_error";
		}
		return "register/indexPage";
	}
	
	/**
	 * 注册用户：家庭注册
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register/family")
	public String registerfamily(CustinfoVo custinfoVo, Model model) {
		
		try{
			// 初始化数据
			custinfoVo.setInvtp(Invtp.PERSONAL.getValue()); // 个人
			custinfoVo.setLevel(Level.PERSONAL.getValue()); // 家庭
			
			// 防止重复注册
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				if(s_custinfo.getMobileno().equals(custinfoVo.getMobileno())){
//					model.addAttribute("SessionVo", s_custinfo);
					return "register/successPage";
				}
			}
			
			// 校验手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			
			// 注册对象封装 
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLoginCode(custinfoVo.getMobileno());
			registerAction.setLoginPassword(custinfoVo.getPswpwd());
			registerAction.setLoginPassword2(custinfoVo.getPswpwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setLevel(Level.PERSONAL); // 家庭
			// 注册
			custManager.register(registerAction);

			// 注册成功，保存用户至session
			custinfoVo.setCustno(registerAction.getCustNo());
			UserHelper.saveCustinfoVo(custinfoVo);
//			model.addAttribute("SessionVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno_family", e.getMessage()); // 
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode_family", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode_family", e.getMessage()); // 手机验证码
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd_family", e.getMessage()); // 登录密码
			} else if (BisConst.Register.LOGINPASSWORD2.equals(ems)) {
				model.addAttribute("errMsg_pswpwd2_family", e.getMessage()); // 登录确认密码
			} else {
				// TODO
				model.addAttribute("errMsg_family", e.getMessage());
			}
			model.addAttribute("CustinfoVo_family", custinfoVo);
			return "register/indexPage";
		}
		return "register/successPage";
	}
	
	/**
	 * 注册用户：经办人注册
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register/org")
	public String registerOrg(CustinfoVo custinfoVo, Model model) {
		
		try{
			custinfoVo.setInvtp(Invtp.PERSONAL.getValue()); // 个人
			custinfoVo.setLevel(Level.OPERATOR.getValue()); // 经办人
			
			// 防止重复注册
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				if(s_custinfo.getMobileno().equals(custinfoVo.getMobileno())){
//					model.addAttribute("SessionVo", s_custinfo);
					return "register/successPage";
				}
			}
			
			// 校验手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			
			// 注册对象封装 
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLoginCode(custinfoVo.getMobileno());
			registerAction.setLoginPassword(custinfoVo.getPswpwd());
			registerAction.setLoginPassword2(custinfoVo.getPswpwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setLevel(Level.OPERATOR); // 经办人
			registerAction.setOrganization(custinfoVo.getOrganization());
			registerAction.setBusiness(custinfoVo.getBusiness());
			// 注册
			custManager.register(registerAction);

			// 注册成功，保存用户至session
			custinfoVo.setCustno(registerAction.getCustNo());
			UserHelper.saveCustinfoVo(custinfoVo);
//			model.addAttribute("SessionVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno_org", e.getMessage()); // 
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode_org", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode_org", e.getMessage()); // 手机验证码
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd_org", e.getMessage()); // 登录密码
			} else if (BisConst.Register.LOGINPASSWORD2.equals(ems)) {
				model.addAttribute("errMsg_pswpwd2_org", e.getMessage()); // 登录确认密码
			} else if (BisConst.Register.ORGANIZATION.equals(ems)) {
				model.addAttribute("errMsg_organization_org", e.getMessage()); //
			} else if (BisConst.Register.BUSINESS.equals(ems)) {
				model.addAttribute("errMsg_business_org", e.getMessage()); //
			} else {
				// TODO
				model.addAttribute("errMsg_org", e.getMessage());
			}
			model.addAttribute("CustinfoVo_org", custinfoVo);
			return "register/indexPage";
		}
		return "register/successPage";
	}
	
	/**
	 * 首页、广告页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "home/index")
	public String index(Model model) {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
//				model.addAttribute("SessionVo", s_custinfo);
			}else{
//				model.addAttribute("SessionVo", null);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "home/indexPage";
	}
	
	/**
	 * 登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/login")
	public String loginIn(CustinfoVo custinfoVo, Model model) {
		
		try{
			// TODO
			// 校验验证码 
			// VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			
			LoginAction loginAction = new LoginAction();
			loginAction.setLoginCode(custinfoVo.getMobileno());
			loginAction.setLoginPassword(custinfoVo.getPswpwd());
			
			// 登录
			Custinfo custinfo = custManager.loginIn(loginAction);
			
			// 登录成功，保存用户至session
			custinfoVo = this.convertCustInfo2Vo(custinfo);
			UserHelper.saveCustinfoVo(custinfoVo);
//			model.addAttribute("SessionVo", custinfoVo);
			
			// 货基信息显示
			model.addAttribute("FUNDINFOVO", this.getFundInfo());
			
			// NAV
			FundNav fundnav = new FundNav();
			fundnav.setFundcorpno("01");
			fundnav.setFundcode("519005");
			List<FundNav> navList = queryManager.qryFundNavList(fundnav);
			model.addAttribute("navList", navList);
			if(null != navList){
				FundNav curFundNav = navList.get(0);
				model.addAttribute("NAV", curFundNav);
			}
			
			if("Y".equals(custinfoVo.getOpenaccount())){
				
				// 资产显示
				//List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
				List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
				Assets assets = queryManager.queryAssets(tradeAccoList);
				model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));// 总资产
				model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));// 可用资产
				model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));// 冻结资产
				model.addAttribute("funddayincome", NumberUtils.DF_CASH_CONMMA.format(assets.getFunddayincome()));// 昨日收益
				model.addAttribute("totalincome", NumberUtils.DF_CASH_CONMMA.format(assets.getTotalincome()));// 累计受益
				
				// 交易明细显示
				List<String> apkinds = new ArrayList<String>();
				apkinds.add("022"); // 充值
				apkinds.add("023"); // 取现
				apkinds.add("024"); // 快速取现
				List<TradeRequest> listIn = queryManager.qryRecentTradeList(custinfoVo.getCustno(), apkinds, 8);
				model.addAttribute("listIn", listIn);
			} else {
				// 资产显示
				model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("funddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
				model.addAttribute("totalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
				
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.LOGINCODE.equals(ems)) {
				model.addAttribute("errMsg_mobileno", e.getMessage()); // 登录帐号
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode", e.getMessage()); // 验证码
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd", e.getMessage()); // 登录密码
			} else {
				// TODO throw userException?
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			return "home/indexPage";
		}
		return "cust/indexPage";
	}
	
	/**
	 * 登出
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/logout")
	public String logOut(Model model) {
		try{
			UserHelper.removeCustinfoVo();
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "home/indexPage";
	}
	
	/**
	 * 注册后直接登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/session")
	public String custLogin(CustinfoVo custinfoVo, Model model) {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				custinfoVo = this.convertCustSession2Vo(s_custinfo);
				
				// 货基信息显示
				model.addAttribute("FUNDINFOVO", this.getFundInfo());
				
				// NAV
				FundNav fundnav = new FundNav();
				fundnav.setFundcorpno("01");
				fundnav.setFundcode("519005");
				List<FundNav> navList = queryManager.qryFundNavList(fundnav);
				model.addAttribute("navList", navList);
				if(null != navList){
					FundNav curFundNav = navList.get(0);
					model.addAttribute("NAV", curFundNav);
				}
				
				
				if("Y".equals(s_custinfo.getOpenaccount())){
					// 资产显示
					//List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
					List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
					Assets assets = queryManager.queryAssets(tradeAccoList);
					model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));// 总资产
					model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));// 可用资产
					model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));// 冻结资产
					model.addAttribute("funddayincome", NumberUtils.DF_CASH_CONMMA.format(assets.getFunddayincome()));// 昨日收益
					model.addAttribute("totalincome", NumberUtils.DF_CASH_CONMMA.format(assets.getTotalincome()));// 累计受益
					// 交易明细显示
					List<String> apkinds = new ArrayList<String>();
					apkinds.add("022"); // 充值
					apkinds.add("023"); // 取现
					apkinds.add("024"); // 快速取现
					List<TradeRequest> listIn = queryManager.qryRecentTradeList(custinfoVo.getCustno(), apkinds, 8);
					model.addAttribute("listIn", listIn);
				} else {
					model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("funddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
					model.addAttribute("totalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
				}
			}else{
				// Session无效
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
//			model.addAttribute("SessionVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("CustinfoVo", custinfoVo);
			return "home/indexPage";
		}
		return "cust/indexPage";
	}
	
	private CustinfoVo convertCustInfo2Vo(Custinfo custinfo){
		CustinfoVo custinfoVo = new CustinfoVo();
		custinfoVo.setCustno(custinfo.getCustno());;                      
		custinfoVo.setMobileno(custinfo.getMobileno());                    
		custinfoVo.setInvtp(custinfo.getInvtp()); 
		custinfoVo.setInvnm(custinfo.getInvnm());        
		custinfoVo.setIdtp(custinfo.getIdtp());     
		custinfoVo.setIdno(custinfo.getIdno());             
		custinfoVo.setPswpwd(custinfo.getPasswd()); // 注意，页面上不能放密码信息                  
		custinfoVo.setPswpwd2(custinfo.getPasswd()); // 注意，页面上不能放密码信息                         
		custinfoVo.setTradepwd(custinfo.getTradepwd()); // 注意，页面上不能放密码信息                             
		custinfoVo.setTradepwd2(custinfo.getTradepwd()); // 注意，页面上不能放密码信息         
		custinfoVo.setOrganization(custinfo.getOrganization()); 
		custinfoVo.setBusiness(custinfo.getBusiness()); 
		custinfoVo.setCustst(custinfo.getCustst());
		custinfoVo.setLevel(custinfo.getLevel());
		custinfoVo.setOpenaccount(custinfo.getOpenaccount());
		return custinfoVo;
	}
	
	private CustinfoVo convertCustSession2Vo(CustinfoVo sessionInfo){
		CustinfoVo custinfoVo = new CustinfoVo();
		custinfoVo.setCustno(sessionInfo.getCustno());                  
		custinfoVo.setMobileno(sessionInfo.getMobileno());                    
		custinfoVo.setInvtp(sessionInfo.getInvtp()); 
		custinfoVo.setInvnm(sessionInfo.getInvnm());        
		custinfoVo.setIdtp(sessionInfo.getIdtp());     
		custinfoVo.setIdno(sessionInfo.getIdno());       
		custinfoVo.setOrganization(sessionInfo.getOrganization()); 
		custinfoVo.setBusiness(sessionInfo.getBusiness()); 
		custinfoVo.setCustst(sessionInfo.getCustst());
		custinfoVo.setLevel(sessionInfo.getLevel());
		custinfoVo.setOpenaccount(sessionInfo.getOpenaccount());
		return custinfoVo;
	}
	
	private FundInfo getFundInfo(){
		// 货基信息显示
		FundInfo fundInfo = new FundInfo();
		fundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
		fundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
		//DateFormat df = new SimpleDateFormat("yyyyMMdd");
		//fundInfo.setDate(df.format(new Date()));
		//fundInfo = queryManager.getFundInfo(fundInfo);

		fundInfo = fundManager.getFundInfo(fundInfo);
		return fundInfo;
	}
}
