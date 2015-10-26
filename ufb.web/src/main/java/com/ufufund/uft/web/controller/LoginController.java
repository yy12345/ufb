package com.ufufund.uft.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FundInfo;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 家庭版注册、登录页
 * @author ayis
 * 2015年10月23日
 */
@Controller
@RequestMapping(value="family")
@Slf4j
public class LoginController {
	
	private static final String FAMILY_HOME = "family/home.htm";
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private FundManager fundManager;
	@Autowired
	private OrgQueryManager orgQueryManager;

	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;

	/**
	 * 家庭版首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "index")
	public String home(Model model) {
		return "family/account/index";
	}
	
	/**
	 * 登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(CustinfoVo custinfoVo, Model model) {
		
		try{
			// 登录参数后台校验
			// code later...
			
			LoginAction loginAction = new LoginAction();
			loginAction.setLoginCode(custinfoVo.getMobileno());
			loginAction.setLoginPassword(custinfoVo.getLoginpwd());
			
			// 登录
			Custinfo custinfo = custManager.loginIn(loginAction);
			custinfoVo = this.convertCustInfo2Vo(custinfo);
			
			// 保存用户会话
			UserHelper.saveCustinfoVo(custinfoVo);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "登录异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		return "redirect:uft_index.htm";
	}
	
	/**
	 * 家庭版：注册首页-手机验证
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register_index")
	public String registerIndex(CustinfoVo custinfoVo, Model model){
		return "family/account/register_index";
	}
	
	/**
	 * 注册用户：家庭注册开户
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_passwd")
	public String registerPasswd(CustinfoVo custinfoVo, Model model) {
		
		try{
			// 后台验证手机验证码
			// coding later...
			
			model.addAttribute("CustinfoVo",custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		return "family/account/register_passwd";
	}
	
	@RequestMapping(value = "register_card")
	public String registerCard(CustinfoVo custinfoVo,BankCardVo bankCardVo, Model model) {
		
		try{
			//获得用户的注册的信息并进行校验
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			custManager.validateFamily(registerAction);
			/*//用户注册
			custManager.register(registerAction);
			// 注册成功，保存用户至session
		    custinfoVo.setCustno(registerAction.getCustno());
			UserHelper.saveCustinfoVo(custinfoVo);*/
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			//支持幼富通的银行===20151013
			List<BankBaseInfo> yftBankList= new ArrayList<BankBaseInfo>();
			//其它的银行
			List<BankBaseInfo> qtBankList= new ArrayList<BankBaseInfo>();
			for(BankBaseInfo bankinfo:bankBaseList){
				if("2".equals(bankinfo.getLevel())){
					qtBankList.add(bankinfo);
				}
				else if("1".equals(bankinfo.getLevel())){
					yftBankList.add(bankinfo);
				}
			}
			
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(yftBankList.get(0).getBankno());
			} 
			model.addAttribute("qtBankList", qtBankList);
			model.addAttribute("bankList", yftBankList);
			//===20151013
			model.addAttribute("CustinfoVo", custinfoVo);
			model.addAttribute("BankCardVo", bankCardVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		return "family/account/register_card";
	}
	
	@RequestMapping(value = "register_success")
	public String registerSuccess(CustinfoVo custinfoVo, BankCardVo bankCardVo, Model model) {
		
		try{
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setCustst("N");
			custManager.validateFamily(registerAction);
			//用户注册
			custManager.register(registerAction);
			// 注册成功，保存用户至session
		    custinfoVo.setCustno(registerAction.getCustno());
		    custinfoVo.setInvtp(Invtp.PERSONAL.getValue());
			UserHelper.saveCustinfoVo(custinfoVo);
				//绑卡、开户
			CustinfoVo s_custno=UserHelper.getCustinfoVo();
		    	bankCardVo.setBankidtp("0"); // 身份证绑卡
				OpenAccountAction openAccountAction = new OpenAccountAction();
				/** 开户所属基金单位 **/
				openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				/** 开户标志 **/
				if(s_custno.getIdno() !=null && s_custno.getInvnm() != null){
					openAccountAction.setOpenaccoflag(true);
				}else{
					openAccountAction.setOpenaccoflag(false);
				}
				// 个人
				openAccountAction.setCustno(registerAction.getCustno());
				openAccountAction.setInvnm(bankCardVo.getBankacnm());
				openAccountAction.setIdno(bankCardVo.getBankidno());
				/** 家庭**/
				openAccountAction.setLevel(Invtp.PERSONAL.getValue());
				openAccountAction.setTradepwd(custinfoVo.getTradepwd());
				openAccountAction.setTradepwd2(custinfoVo.getTradepwd2());
				// 银行
				openAccountAction.setBankno(bankCardVo.getBankno());
				openAccountAction.setBankacnm(bankCardVo.getBankacnm());
				openAccountAction.setBankacco(bankCardVo.getBankacco());
				openAccountAction.setBankidtp(bankCardVo.getBankidtp());
				openAccountAction.setBankidno(bankCardVo.getBankidno());
				openAccountAction.setBankmobile(bankCardVo.getBankmobile());
				openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
				openAccountAction.setBankcitynm(StringUtils.isNotBlank(bankCardVo.getBankcitynm())?bankCardVo.getBankcitynm():null);
				openAccountAction.setBankprovincenm(StringUtils.isNotBlank(bankCardVo.getBankprovincenm())?bankCardVo.getBankprovincenm():null);
				openAccountAction.setBankadd(StringUtils.isNotBlank(bankCardVo.getBankadd())?bankCardVo.getBankadd():null);
				openAccountAction.setOtherserial(bankCardVo.getOtherserial());
				/** 需要验证手机验证码标志 **/
				openAccountAction.setCheckautocodeflag(true);
				
				/** 银行手机验证，并带回Serialno、Protocolno的值 **/
				bankCardManager.openAccount3(openAccountAction);
				//其他的银行卡的银联验证
				String banklevel=bankCardManager.getLevelByBankno(openAccountAction.getBankno());
				if("2".equals(banklevel)){
					bankCardManager.checkYinLian(openAccountAction);  	
				}
				/** 开户 **/
				bankCardManager.openAccountPerson(openAccountAction);
				
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		return "family/account/register_success";
	}
	
	/**
	 * 幼富通首页
	 * @param custinfoVo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "uft_index")
	public String uftIndex(Model model) throws IOException {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			/** 货基信息显示 **/
			// 海富通
			FundInfo hftFundInfo = new FundInfo();
			hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
			//model.addAttribute("FUNDINFOVO", this.getFundInfo(htfFundInfo));
			model.addAttribute("hftFundInfo", fundManager.getFundInfo(hftFundInfo));
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
			
			
			// log.info("CusterController==.loginIn==orgQueryManager.getQueryOrgByCustno custno = " + custinfoVo.getCustno());
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(s_custinfo.getCustno());
			List<QueryOrgStudent> studentlist = null;
			List<QueryCustplandetail> planlist = null;
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			if(orglist != null && orglist.size() > 0){
				int count = 1;
				for(QueryOrgStudent org: orglist){
					
					String orgid = org.getOrgid();
					String custno = s_custinfo.getCustno();
					
					log.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid "
							+ "orgid = " + orgid
							+ ",custno = " +custno);
					

					studentlist = orgQueryManager.getQueryStudentByOrgid(orgid, custno);
					model.addAttribute("studentlist" + count, studentlist);
					
					// 个人用户查询收费计划详情
					planlist = orgQueryManager.getQueryCustplandetail(custno, orgid);
					model.addAttribute("planlist" + count, planlist);
					
					for(QueryCustplandetail plan:planlist){
						
						BigDecimal planmonthamt = BigDecimal.ZERO;
						if(null != plan.getPayappamount()){
							planmonthamt = new BigDecimal(plan.getPayappamount());
						}
						totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
					}
					
					count = count + 1;
				}
				
			}
			
			model.addAttribute("orglist", orglist);
			model.addAttribute("totalplanmonthamt", totalplanmonthamt);

			/** 资产 **/
			this.setModel(s_custinfo, model);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		
		return "family/account/uft_index";
	}
	
	/**
	 * 幼富宝
	 * @param custinfoVo
	 * @param model
	 * @return
	 * @throws IOException 
	 * 20150813
	 * CustinfoVo   Custinfo
	 */   
	@RequestMapping(value = "ufb_index")
	public String custUFB(CustinfoVo custinfoVo, Model model) throws IOException {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			/** 货基信息显示 **/
			// 海富通
			FundInfo hftFundInfo = new FundInfo();
			hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
		//	model.addAttribute("FUNDINFOVO", this.getFundInfo(hftFundInfo));
			model.addAttribute("hftFundInfo", fundManager.getFundInfo(hftFundInfo));
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
			/**资产明细**/
			List<String> apkinds = new ArrayList<String>();
			apkinds.add("000");
			apkinds.add("022");
			apkinds.add("023");
			apkinds.add("024");
			List<String> states = new ArrayList<String>();
			states.add("Y"); // 
			states.add("F"); // 
			states.add("I");
			List<TradeRequest> Tradelist = queryManager.qryTradeList(s_custinfo.getCustno(), 
					apkinds,states,null, null,0, 4);
			model.addAttribute("Tradelist", Tradelist);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "注册异常");
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("message_content0", "操作失败!");
			model.addAttribute("message_content1", ue.getMessage());
			return "error/user_error";
		}
		return "family/account/ufb_index";
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
	
	private void setModel(CustinfoVo custinfoVo, Model model){
		// 海富通
		List<String> tradeaccosts = new ArrayList<String>();
		tradeaccosts.add("Y"); // 
		tradeaccosts.add("N"); // 
		
		List<String> levels = new ArrayList<String>();
		levels.add("0"); 
		List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(
				custinfoVo.getCustno(),
				null,//Constant.HftSysConfig.HftFundCorpno, 
				levels,
				tradeaccosts);
		String isufbCard="";
		if(null!=hft_family_trade){
			for(TradeAccoinfoOfMore tradeacco:hft_family_trade){
				if(tradeacco.getClevel().equals("1")){
					isufbCard="Y";
					break;
				}
			}
		}
		model.addAttribute("isufbCard", isufbCard);
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
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计收益
				model.addAttribute("hft_family_trade_size", "0");
			}
		}else if("1".equals(custinfoVo.getInvtp())){
			// 经办人
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
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计收益
				model.addAttribute("hft_operator_trade_size", "0");
			}
			// 机构
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
	
}
