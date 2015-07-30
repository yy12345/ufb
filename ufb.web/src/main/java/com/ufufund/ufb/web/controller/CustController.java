package com.ufufund.ufb.web.controller;

import java.io.IOException;
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
		// 清除Session
		UserHelper.removeCustinfoVo();
		
		return "register/indexPage";
	}
	
	/**
	 * 注册用户：家庭注册
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register/family")
	public String registerFamily(CustinfoVo custinfoVo, Model model) {
		
		try{
			// 初始化数据
			custinfoVo.setInvtp(Invtp.PERSONAL.getValue()); // 个人
			
			// 防止重复注册
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				if(s_custinfo.getMobileno().equals(custinfoVo.getMobileno())){
					return "register/successPage";
				}
			}
			
			// 校验手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			
			// 注册对象封装 
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setCustst("N");
			// 注册
			custManager.register(registerAction);

			// 注册成功，保存用户至session
			custinfoVo.setCustno(registerAction.getCustno());
			UserHelper.saveCustinfoVo(custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno_family", e.getMessage()); // 
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode_family", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode_family", e.getMessage()); // 手机验证码
			} else if (BisConst.Register.LOGINPWD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd_family", e.getMessage()); // 登录密码
			} else if (BisConst.Register.LOGINPWD2.equals(ems)) {
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
			custinfoVo.setInvtp(Invtp.ORGANIZATION.getValue()); // 机构
			
			// 防止重复注册
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				if(s_custinfo.getMobileno().equals(custinfoVo.getMobileno())){
					return "register/successPage";
				}
			}
			
			// 校验手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			
			// 注册对象封装 
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setInvtp(Invtp.ORGANIZATION);// 机构
			registerAction.setCustst("N");
			registerAction.setOrgnm(custinfoVo.getOrgnm());
			registerAction.setOrgbusiness(custinfoVo.getOrgbusiness());
			// 注册
			custManager.register(registerAction);

			// 注册成功，保存用户至session
			custinfoVo.setCustno(registerAction.getCustno());
			UserHelper.saveCustinfoVo(custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno_org", e.getMessage()); // 
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode_org", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode_org", e.getMessage()); // 手机验证码
			} else if (BisConst.Register.LOGINPWD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd_org", e.getMessage()); // 登录密码
			} else if (BisConst.Register.LOGINPWD2.equals(ems)) {
				model.addAttribute("errMsg_pswpwd2_org", e.getMessage()); // 登录确认密码
			} else if (BisConst.Register.ORGNM.equals(ems)) {
				model.addAttribute("errMsg_organization_org", e.getMessage()); //
			} else if (BisConst.Register.ORGBUSINESS.equals(ems)) {
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
			loginAction.setLoginPassword(custinfoVo.getLoginpwd());
			
			// 登录
			Custinfo custinfo = custManager.loginIn(loginAction);
			custinfoVo = this.convertCustInfo2Vo(custinfo);
			
			/** 货基信息显示 **/
			// 海富通
			FundInfo hftFundInfo = new FundInfo();
			hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
			//model.addAttribute("FUNDINFOVO", this.getFundInfo(htfFundInfo));
			model.addAttribute("hftFundInfo", this.getFundInfo(hftFundInfo));
			// 银联
			FundInfo cpFundInfo = new FundInfo();
			cpFundInfo.setFundcorpno(null);
			cpFundInfo.setFundcode(null);
			model.addAttribute("cpFundInfo", null);
			
			/** NAV **/
			// 海富通
			FundNav hftFundNav = new FundNav();
			hftFundNav.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			hftFundNav.setFundcode(BasicFundinfo.YFB.getFundCode());
			List<FundNav> hftNavList = queryManager.qryFundNavList(hftFundNav);
			//model.addAttribute("navList", hftNavList);
			model.addAttribute("hftNavList", hftNavList);
			if(null != hftNavList && hftNavList.size() >0){
				FundNav curHtfFundNav = hftNavList.get(0);
				//model.addAttribute("NAV", curFundNav);
				model.addAttribute("curHtfFundNav", curHtfFundNav);
			}
			// 银联
			FundNav cpFundNav = new FundNav();
			hftFundNav.setFundcorpno(null);
			hftFundNav.setFundcode(null);
			List<FundNav> cpNavList = queryManager.qryFundNavList(cpFundNav);
			model.addAttribute("cpNavList", cpNavList);
			if(null != cpNavList && cpNavList.size() >0){
				FundNav curCpFundNav = cpNavList.get(0);
				model.addAttribute("curCpFundNav", curCpFundNav);
			}

			/** 资产 **/
			// 保存主帐户至session
			UserHelper.saveCustinfoVo(custinfoVo); // S_CUSTINFO
			this.setModel(custinfoVo, model);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.LOGINCODE.equals(ems)) {
				model.addAttribute("errMsg_mobileno", e.getMessage()); // 登录帐号
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode", e.getMessage()); // 验证码
			} else if (BisConst.Register.LOGINPWD.equals(ems)) {
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
	
	private void setModel(CustinfoVo custinfoVo, Model model){
		// 海富通
		List<String> tradeaccosts = new ArrayList<String>();
		tradeaccosts.add("Y"); // 
		tradeaccosts.add("N"); // 
		
		List<String> levels = new ArrayList<String>();
		levels.add("0"); 
		List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(
				custinfoVo.getCustno(),
				Constant.HftSysConfig.HftFundCorpno, 
				levels,
				tradeaccosts);
		
		levels = new ArrayList<String>();
		levels.add("1"); 
		List<TradeAccoinfoOfMore> hft_organization_trade = tradeAccoManager.getTradeAccoList(
				custinfoVo.getCustno(),
				Constant.HftSysConfig.HftFundCorpno, 
				levels,
				tradeaccosts);
		
		levels = new ArrayList<String>();
		levels.add("2"); 
		List<TradeAccoinfoOfMore> hft_operator_trade = tradeAccoManager.getTradeAccoList(
				custinfoVo.getCustno(),
				Constant.HftSysConfig.HftFundCorpno, 
				levels,
				tradeaccosts);
		
		
		if("0".equals(custinfoVo.getInvtp())){
			// 家庭
			if(null != hft_family_trade && hft_family_trade.size() > 0){
				// 海富通资产显示
				Assets htfAssets = queryManager.queryAssets(hft_family_trade, BasicFundinfo.YFB.getFundCode());
				model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotal()));// 总资产
				model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
				model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFrozen()));// 冻结资产
				model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFunddayincome()));// 昨日收益
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotalincome()));// 累计受益
				model.addAttribute("hft_family_trade_size", hft_family_trade.size());
			} else {
				// 资产显示
				model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
				model.addAttribute("hft_family_trade_size", "0");
			}
		}else if("1".equals(custinfoVo.getInvtp())){
			// 机构
			if(null != hft_operator_trade && hft_operator_trade.size() > 0){
				// 海富通资产显示
				Assets htfAssets = queryManager.queryAssets(hft_operator_trade, BasicFundinfo.YFB.getFundCode());
				model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotal()));// 总资产
				model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
				model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFrozen()));// 冻结资产
				model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFunddayincome()));// 昨日收益
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotalincome()));// 累计受益
				model.addAttribute("hft_operator_trade_size", hft_operator_trade.size());
			} else {
				// 资产显示
				model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
				model.addAttribute("hft_operator_trade_size", "0");
			}
			// 经办人
			if(null != hft_organization_trade && hft_organization_trade.size() > 0){
				// 海富通资产显示
				Assets htfAssets = queryManager.queryAssets(hft_organization_trade, BasicFundinfo.YFB.getFundCode());
				model.addAttribute("org_hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotal()));// 总资产
				model.addAttribute("org_hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
				model.addAttribute("org_hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFrozen()));// 冻结资产
				model.addAttribute("org_hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFunddayincome()));// 昨日收益
				model.addAttribute("org_hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotalincome()));// 累计受益
				model.addAttribute("hft_organization_trade_size", hft_organization_trade.size());
			} else {
				// 资产显示
				model.addAttribute("org_hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("org_hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("org_hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("org_hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
				model.addAttribute("org_hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
				model.addAttribute("hft_organization_trade_size", "0");
			}
		}
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "cust/session")
	public String custLogin(CustinfoVo custinfoVo, Model model) throws IOException {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				
				/** 货基信息显示 **/
				// 海富通
				FundInfo hftFundInfo = new FundInfo();
				hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
				//model.addAttribute("FUNDINFOVO", this.getFundInfo(htfFundInfo));
				model.addAttribute("hftFundInfo", this.getFundInfo(hftFundInfo));
				// 银联
				FundInfo cpFundInfo = new FundInfo();
				cpFundInfo.setFundcorpno(null);
				cpFundInfo.setFundcode(null);
				model.addAttribute("cpFundInfo", null);
				
				/** NAV **/
				// 海富通
				FundNav hftFundNav = new FundNav();
				hftFundNav.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				hftFundNav.setFundcode(BasicFundinfo.YFB.getFundCode());
				List<FundNav> hftNavList = queryManager.qryFundNavList(hftFundNav);
				//model.addAttribute("navList", hftNavList);
				model.addAttribute("hftNavList", hftNavList);
				if(null != hftNavList && hftNavList.size() >0){
					FundNav curHtfFundNav = hftNavList.get(0);
					//model.addAttribute("NAV", curFundNav);
					model.addAttribute("curHtfFundNav", curHtfFundNav);
				}
				// 银联
				FundNav cpFundNav = new FundNav();
				hftFundNav.setFundcorpno(null);
				hftFundNav.setFundcode(null);
				List<FundNav> cpNavList = queryManager.qryFundNavList(cpFundNav);
				model.addAttribute("cpNavList", cpNavList);
				if(null != cpNavList && cpNavList.size() >0){
					FundNav curCpFundNav = cpNavList.get(0);
					model.addAttribute("curCpFundNav", curCpFundNav);
				}

				/** 资产 **/
				this.setModel(s_custinfo, model);
			}else{
				// Session无效
				ServletHolder.getResponse().sendRedirect("/ufb/home/index.htm");
				return null;
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("CustinfoVo", custinfoVo);
			return "home/indexPage";
		}
		return "cust/indexPage";
	}
	
	private CustinfoVo convertCustInfo2Vo(Custinfo custinfo){
		if(null == custinfo){
			return null;
		}
		CustinfoVo custinfoVo = new CustinfoVo();
		custinfoVo.setCustno(custinfo.getCustno());;                      
		custinfoVo.setMobileno(custinfo.getMobileno());                    
		custinfoVo.setInvtp(custinfo.getInvtp()); 
		custinfoVo.setInvnm(custinfo.getInvnm());        
		custinfoVo.setIdtp(custinfo.getIdtp());     
		custinfoVo.setIdno(custinfo.getIdno());             
		custinfoVo.setLoginpwd(custinfo.getLoginpwd()); // 注意，页面上不能放密码信息                  
		custinfoVo.setLoginpwd2(custinfo.getLoginpwd()); // 注意，页面上不能放密码信息                         
		custinfoVo.setTradepwd(custinfo.getTradepwd()); // 注意，页面上不能放密码信息                             
		custinfoVo.setTradepwd2(custinfo.getTradepwd()); // 注意，页面上不能放密码信息         
		custinfoVo.setOrgnm(custinfo.getOrgnm()); 
		custinfoVo.setOrgbusiness(custinfo.getOrgbusiness()); 
		custinfoVo.setCustst(custinfo.getCustst());
		custinfoVo.setLevel(custinfo.getLevel());
		return custinfoVo;
	}
	
	private FundInfo getFundInfo(FundInfo fundInfo){
		// 货基信息显示
		
		//DateFormat df = new SimpleDateFormat("yyyyMMdd");
		//fundInfo.setDate(df.format(new Date()));
		//fundInfo = queryManager.getFundInfo(fundInfo);

		fundInfo = fundManager.getFundInfo(fundInfo);
		return fundInfo;
	}
}
