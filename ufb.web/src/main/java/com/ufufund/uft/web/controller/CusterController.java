package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.web.util.UserHelper;

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
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					String curmonth = sdf.format(new Date());
					
					for(QueryCustplandetail plan:planlist){
						
						String plandate = plan.getPlandate();
						String planmonth = "";
						if(null != plandate){
							planmonth = plandate.substring(0, 7);
						}
						
						if(planmonth.equals(curmonth)){
							String planmonthamtStr = plan.getPayappamount();
							BigDecimal planmonthamt = BigDecimal.ZERO;
							if(null != planmonthamt){
								planmonthamt = new BigDecimal(planmonthamtStr);
							}
							totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
						}
					}
					
					
					
					
					count = count + 1;
				}
				
				model.addAttribute("orglist", orglist);
				model.addAttribute("totalplanmonthamt", totalplanmonthamt);
				
			}
			
			
			
			
		

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
	
	@RequestMapping(value = "family/payNotice")
	public String payNotice(CustinfoVo custinfoVo, Model model) {
		
		try{
			
		
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "family/home";
		}
		return "family/payNotice";
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
}
