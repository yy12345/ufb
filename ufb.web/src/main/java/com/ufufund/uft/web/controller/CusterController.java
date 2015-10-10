package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;

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
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FundInfo;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
//import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.PayNoticeVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
@Controller
public class CusterController {
	private static final Logger LOG = LoggerFactory.getLogger(CusterController.class);
	
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
	 * 首页、广告页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/home")
	public String index(Model model) {
		try{
			//CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "family/home";
	}
	
	/**
	 * 注册页
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/register1")
	public String getRegistPage(CustinfoVo custinfoVo, Model model){
		// 清除Session
		UserHelper.removeCustinfoVo();
		
		return "family/register1";
	}
	
	/**
	 * 登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/login")
	public String login(CustinfoVo custinfoVo, Model model) {
		
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
			model.addAttribute("hftFundInfo", fundManager.getFundInfo(hftFundInfo));
			
//			// 银联
//			FundInfo cpFundInfo = new FundInfo();
//			cpFundInfo.setFundcorpno(null);
//			cpFundInfo.setFundcode(null);
//			model.addAttribute("cpFundInfo", null);
			
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
//			// 银联
//			FundNav cpFundNav = new FundNav();
//			hftFundNav.setFundcorpno(null);
//			hftFundNav.setFundcode(null);
//			List<FundNav> cpNavList = queryManager.qryFundNavList(cpFundNav);
//			model.addAttribute("cpNavList", cpNavList);
//			if(null != cpNavList && cpNavList.size() >0){
//				FundNav curCpFundNav = cpNavList.get(0);
//				model.addAttribute("curCpFundNav", curCpFundNav);
//			}
			
			// LOG.info("CusterController==.loginIn==orgQueryManager.getQueryOrgByCustno custno = " + custinfoVo.getCustno());
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(custinfoVo.getCustno());
			List<QueryOrgStudent> studentlist = null;
			List<QueryCustplandetail> planlist = null;
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			if(orglist != null && orglist.size() > 0){
				int count = 1;
				for(QueryOrgStudent org: orglist){
					
					String orgid = org.getOrgid();
					String custno = custinfoVo.getCustno();
					
					LOG.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid "
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
			// 保存主帐户至session
			UserHelper.saveCustinfoVo(custinfoVo); // S_CUSTINFO
			this.setModel(custinfoVo, model);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) 
				|| BisConst.Register.LOGINCODE.equals(ems)
				|| BisConst.Register.VERIFYCODE.equals(ems)
				|| BisConst.Register.LOGINPWD.equals(ems)) {
				model.addAttribute("errMsg", e.getMessage());
			} else {
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/home";
		}
		return "family/account";
	}
	
	
	/**
	 * 注册后直接登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "family/session")
	public String session(CustinfoVo custinfoVo, Model model) throws IOException {
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
				
				
				// LOG.info("CusterController==.loginIn==orgQueryManager.getQueryOrgByCustno custno = " + custinfoVo.getCustno());
				List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(s_custinfo.getCustno());
				List<QueryOrgStudent> studentlist = null;
				List<QueryCustplandetail> planlist = null;
				BigDecimal totalplanmonthamt = BigDecimal.ZERO;
				if(orglist != null && orglist.size() > 0){
					int count = 1;
					for(QueryOrgStudent org: orglist){
						
						String orgid = org.getOrgid();
						String custno = s_custinfo.getCustno();
						
						LOG.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid "
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
			}else{
				// Session无效
				ServletHolder.getResponse().sendRedirect("/ufb/home/index.htm");
				return null;
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/home";
		}
		
		return "family/account";
	}
	
	@RequestMapping(value = "family/payNotice")
	public String payNotice(Model model) {
		
		ArrayList<List<QueryCustplandetail>> planlistlist = new ArrayList<List<QueryCustplandetail>>();
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			String custno = s_custinfo.getCustno();
			
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(s_custinfo.getCustno());
			List<QueryCustplandetail> planlist = null;
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			int plancount = 0;
			for(QueryOrgStudent org: orglist){
				String orgid = org.getOrgid();
				LOG.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid " + "orgid = " + orgid + ",custno = " +custno);
				
				// 个人用户查询收费计划详情
				planlist = orgQueryManager.getQueryCustplandetail(custno, orgid);
				if(planlist != null && planlist.size()>0){
					planlistlist.add(planlist);
				}
				
				for(QueryCustplandetail plan:planlist){
					plancount = plancount + 1;
					BigDecimal planmonthamt = BigDecimal.ZERO;
					if(null != plan.getPayappamount()){
						planmonthamt = new BigDecimal(plan.getPayappamount());
					}
					totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
				}
			}
			model.addAttribute("orglist", orglist);
			model.addAttribute("planlistlist", planlistlist);
			model.addAttribute("plancount", plancount);
			model.addAttribute("totalplanmonthamt", totalplanmonthamt);
			
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "family/home";
		}
		return "family/payNotice";
	}
	
	@RequestMapping(value = "family/payReview")
	public String payReview(PayNoticeVo paynoticevo, Model model) {
		
		ArrayList<List<QueryCustplandetail>> planlistlistchecked = new ArrayList<List<QueryCustplandetail>>();
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			String custno = s_custinfo.getCustno();
			
			/**  **/
			String allplanids = paynoticevo.getAllplanids();
			//String[] allplanidarray = null;
			//if(null != allplanids){
			//	allplanidarray = allplanids.split(",");
			//}
			/**  **/
			
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(s_custinfo.getCustno());
			List<QueryCustplandetail> planlist = null;
			List<QueryCustplandetail> planlistchecked = null;
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			int plancountchecked = 0;
			for(QueryOrgStudent org: orglist){
				String orgid = org.getOrgid();
				LOG.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid " + "orgid = " + orgid + ",custno = " +custno);
				
				// 个人用户查询收费计划详情
				planlist = orgQueryManager.getQueryCustplandetail(custno, orgid);
				planlistchecked = new ArrayList<QueryCustplandetail>();
				if(planlist != null && planlist.size()>0){
					for(QueryCustplandetail detail : planlist){
						if(allplanids != null){
							if(allplanids.indexOf(detail.getPlanid())>-1){
								planlistchecked.add(detail);
							}
						}
					}
					planlistlistchecked.add(planlistchecked);
				}
				
				for(QueryCustplandetail plan:planlistchecked){
					plancountchecked = plancountchecked + 1;
					BigDecimal planmonthamt = BigDecimal.ZERO;
					if(null != plan.getPayappamount()){
						planmonthamt = new BigDecimal(plan.getPayappamount());
					}
					totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
				}
			}
			
			model.addAttribute("orglist", orglist);
			model.addAttribute("planlistlistchecked", planlistlistchecked);
			model.addAttribute("plancountchecked", plancountchecked);
			model.addAttribute("totalplanmonthamt", totalplanmonthamt);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "family/home";
		}
		return "family/payReview";
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
				model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计受益
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
	
		/**
	 * 幼富宝
	 * @param custinfoVo
	 * @param model
	 * @return
	 * @throws IOException 
	 * 20150813
	 * CustinfoVo   Custinfo
	 */   
	@RequestMapping(value = "family/myufb")
	public String custUFB(CustinfoVo custinfoVo, Model model) throws IOException {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				
				/** 货基信息显示 **/
				// 海富通
				FundInfo hftFundInfo = new FundInfo();
				hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
			//	model.addAttribute("FUNDINFOVO", this.getFundInfo(hftFundInfo));
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
			}else{
				 
				ServletHolder.getResponse().sendRedirect("/ufb/home/index.htm");
				return null;
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("CustinfoVo", custinfoVo);
			return "home/indexPage";
		}
		return "family/account/ufb";
	}
	 
	/**
	 * 家庭版注册页
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/createAccount1")
	public String familyIndex(CustinfoVo custinfoVo, Model model){
		// 清除Session
		UserHelper.removeCustinfoVo();
			return "family/account/createAccount1";
	}
	
	/**
	 * 注册用户：家庭注册开户
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/createAccount2")
	public String familyRegister(CustinfoVo custinfoVo, Model model) {
		
		try{
			// 校验手机验证码
		   //	MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			
			model.addAttribute("CustinfoVo",custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems) || BisConst.Register.BANKMOBILE.equals(ems)) {
				model.addAttribute("errMsg_mobileno_family", e.getMessage()); // 手机号
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				model.addAttribute("errMsg_verifycode_family", e.getMessage()); // 验证码
			} else if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode_family", e.getMessage()); // 手机验证码
			} else {
				// TODO
				model.addAttribute("errMsg_family", e.getMessage());
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/account/createAccount1";
		}
		return "family/account/createAccount2";
	}
	
	@RequestMapping(value = "family/createAccount3")
	public String createAccount3(CustinfoVo custinfoVo,BankCardVo bankCardVo, Model model) {
		
		try{
			//获得用户的注册的信息并进行校验
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			custManager.validateFamily(registerAction);
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(bankBaseList.get(0).getBankno());
			}else{
				// 上下文中的
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			model.addAttribute("bankList", bankBaseList);
			model.addAttribute("BankCardVo", bankCardVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.INVNM.equals(ems)) {
				model.addAttribute("errMsg_invnm_family", e.getMessage()); // 家长名
			}else if (BisConst.Register.IDNO.equals(ems)) {
				model.addAttribute("errMsg_idno_family", e.getMessage()); // 证件号
			}else if (BisConst.Register.LOGINPWD.equals(ems)) {
				model.addAttribute("errMsg_pswpwd_family", e.getMessage()); // 登录密码
			} else if (BisConst.Register.LOGINPWD2.equals(ems)) {
				model.addAttribute("errMsg_pswpwd2_family", e.getMessage()); // 登录确认密码
			} else if (BisConst.Register.TRADEPWD.equals(ems)) {
				model.addAttribute("errmsg_tradepwd_family", e.getMessage()); // 登录密码
			} else if (BisConst.Register.TRADEPWD2.equals(ems)) {
				model.addAttribute("errMsg_tradepwd2_family", e.getMessage()); // 登录确认密码
			} else {
				model.addAttribute("errMsg_family", e.getMessage());
			}
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/account/createAccount2";
		}
		return "family/account/createAccount3";
	}
	@RequestMapping(value = "family/createAccount4")
	public String createAccount4(CustinfoVo custinfoVo, BankCardVo bankCardVo, Model model) {
		
		try{
			
			//用户注册
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			registerAction.setInvtp(Invtp.PERSONAL);
			registerAction.setCustst("N");
			custManager.register(registerAction);
         
			
				//绑卡、开户
		    	bankCardVo.setBankidtp("0"); // 身份证绑卡
				OpenAccountAction openAccountAction = new OpenAccountAction();
				/** 开户所属基金单位 **/
				openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				/** 开户标志 **/
				if(custinfoVo.getIdno() !=null && custinfoVo.getInvnm() != null){
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
				openAccountAction.setBankacnm(custinfoVo.getInvnm());
				openAccountAction.setBankacco(bankCardVo.getBankacco());
				openAccountAction.setBankidtp(bankCardVo.getBankidtp());
				openAccountAction.setBankidno(bankCardVo.getBankidno());
				openAccountAction.setBankmobile(bankCardVo.getBankmobile());
				openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
				openAccountAction.setOtherserial(bankCardVo.getOtherserial());
				/** 需要验证手机验证码标志 **/
				openAccountAction.setCheckautocodeflag(true);
				
				/** 银行手机验证，并带回Serialno、Protocolno的值 **/
				bankCardManager.openAccount3(openAccountAction);
				
				/** 开户 **/
				bankCardManager.openAccountPerson(openAccountAction);
				
				// 注册成功，保存用户至session
			    custinfoVo.setCustno(registerAction.getCustno());
				UserHelper.saveCustinfoVo(custinfoVo);
			}catch (BizException e){
				
				// 获取银行列表
				List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
				if(StringUtils.isBlank(bankCardVo.getBankno())){
					// 默认第一个
					bankCardVo.setBankno(bankBaseList.get(0).getBankno());
				}else{
					// 上下文中的
				}
				model.addAttribute("bankList", bankBaseList);
				LOG.error(e.getErrmsg(), e);
				String ems = e.getOtherInfo();
				
				if(BisConst.Register.BANKACNM.equals(ems)){
					model.addAttribute("errMsg_invnm", e.getMessage());
				}else 
				if(BisConst.Register.BANKNO.equals(ems)){
					model.addAttribute("errMsg_bankNo", e.getMessage());
				}else
				if(BisConst.Register.BANKIDNO.equals(ems) 
					|| BisConst.Register.IDNO.equals(ems)){
					model.addAttribute("errMsg_bankIdno", e.getMessage());
				}else
				if(BisConst.Register.BANKACCO.equals(ems)){
					model.addAttribute("errMsg_bankAcco", e.getMessage());
				}else
				if(BisConst.Register.MOBILE.equals(ems)
					|| BisConst.Register.BANKMOBILE.equals(ems)){
					model.addAttribute("errMsg_bankMobile", e.getMessage());
				}else
				if(BisConst.Register.BANKMOBILEMSGCODE.equals(ems) 
					|| "对方序列号".equals(ems)){
					if("对方序列号".equals(ems)){
						model.addAttribute("errMsg_msgcode", "手机验证码无效！");
					}else{
						model.addAttribute("errMsg_msgcode", e.getMessage());
					}
				}else{
					model.addAttribute("errMsg", e.getMessage());
				}
			model.addAttribute("BankCardVo", bankCardVo);
			model.addAttribute("CustinfoVo", custinfoVo);
			return "family/account/createAccount3";
		}
		return "family/account/createAccount4";
	}
	
	private FundInfo getFundInfo(FundInfo fundInfo){
		fundInfo = fundManager.getFundInfo(fundInfo);
		return fundInfo;
	}
}
