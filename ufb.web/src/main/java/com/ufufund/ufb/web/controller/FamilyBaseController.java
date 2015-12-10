package com.ufufund.ufb.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.biz.manager.OrgQueryManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.Assets;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FundInfo;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.PayList;
import com.ufufund.ufb.model.db.PayRecordQry;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 家庭版base页
 * @author ayis
 * 2015年10月23日
 */
@Controller
@RequestMapping(value="family")
@Slf4j
public class FamilyBaseController {

	private static final String UFT_INDEX = "family/uft/uft_index.htm";
	private static final String UFT_INDEX_NAME = "我的账户";
	private static final String UFB_INDEX = "ufb/ufb_index.htm";
	private static final String UFB_INDEX_NAME = "幼富宝";
	
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private FundManager fundManager;
	@Autowired
	private OrgQueryManager orgQueryManager;
	
	/**
	 * 家庭版首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "index")
	public String home(Model model) {
		return "index";
	}
	
	/**
	 * 幼富通首页
	 * @param Custinfo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "uft/uft_index")
	public String uftIndex(Model model) throws IOException {
		
		Custinfo custinfo = UserHelper.getCustinfo();
		try{
			// 获取绑定机构信息
			List<OrgQuery> orglist = orgQueryManager.queryOrgByCustno(custinfo.getCustno());
			if(orglist == null || orglist.size() == 0){
				return "redirect:/family/uft/code_index.htm";
			}
			
			// 学生缴费信息
			List<PayList> paylist = new ArrayList<PayList>();
			int allcount=0;
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			List<String> ispaylist=new ArrayList<String>();
			ispaylist.add("0");
			ispaylist.add("1");
			for(OrgQuery org: orglist){
				BigDecimal monthtotalamt = BigDecimal.ZERO;
				BigDecimal monthbackamt = BigDecimal.ZERO;
				int count=0;
				PayList stuPayVo=new PayList();
				
				List<OrgQuery>  studentlist = orgQueryManager.queryStudentByOrgid(org.getOrgid(), custinfo.getCustno());
				stuPayVo.setStudentList(studentlist);
				
				// 个人用户查询收费计划详情
				OrgPlanDetail orgDetail = new OrgPlanDetail();
				orgDetail.setParentid(custinfo.getCustno());
				orgDetail.setOrgid(org.getOrgid());
				orgDetail.setIspaylist(ispaylist);
				List<OrgPlanDetail> planlist = orgQueryManager.queryCustplandetail(orgDetail);
				stuPayVo.setPlanList(planlist);
				
				for(OrgPlanDetail plan:planlist){
					BigDecimal planmonthamt = BigDecimal.ZERO;
					if(null!=plan.getAmount()){
						planmonthamt=new BigDecimal(plan.getAmount());
					}
					if("0".equals(plan.getState())){
						allcount=allcount+1;
						count=count+1;
						monthtotalamt=monthtotalamt.add(planmonthamt);
					}
				}
				
				//当月的退费   
				PayRecordQry payRecordQryVo = new PayRecordQry();
				payRecordQryVo.setCustno(custinfo.getCustno());
				payRecordQryVo.setOrgid(org.getOrgid());
				monthbackamt=orgQueryManager.getReversedMonthAmt(payRecordQryVo);
				
				stuPayVo.setPlancount(count);
				stuPayVo.setMonthtotalamt(monthtotalamt);
				stuPayVo.setMonthbackamt(monthbackamt);
				paylist.add(stuPayVo);
				totalplanmonthamt = totalplanmonthamt.add(monthtotalamt);
			}
			
			model.addAttribute("paylist", paylist);
			model.addAttribute("orglist", orglist);
			model.addAttribute("allcount", allcount);
			model.addAttribute("totalplanmonthamt", totalplanmonthamt);

			// 资产 
			this.setModel(custinfo, model);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		
		return "uft/uft_index";
	}
	
	/**
	 * 幼富宝首页
	 * @param Custinfo
	 * @param model
	 * @return
	 * @throws IOException 
	 * 20150813
	 * Custinfo   Custinfo
	 */   
	@RequestMapping(value = "ufb/ufb_index")
	public String ufbIndex(Custinfo Custinfo, Model model) throws IOException {
		try{
			Custinfo s_custinfo = UserHelper.getCustinfo();
			/** 货基信息显示 **/
			// 海富通
			FundInfo hftFundInfo = new FundInfo();
			hftFundInfo.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			hftFundInfo.setFundcode(BasicFundinfo.YFB.getFundCode());
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
			model.addAttribute("hftNavList", hftNavList);
			if(null != hftNavList && hftNavList.size() >0){
				FundNav curHtfFundNav = hftNavList.get(0);
				model.addAttribute("curHtfFundNav", curHtfFundNav);
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
			states.add("Y");  
			states.add("F");  
			states.add("I");
			List<TradeRequest> Tradelist = queryManager.qryTradeList(s_custinfo.getCustno(), 
					apkinds,states,null, null,0, 4);
			model.addAttribute("Tradelist", Tradelist);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFB_INDEX);
			model.addAttribute("back_module", UFB_INDEX_NAME);
			return "error/error";
		}
		return "ufb/ufb_index";
	}
	
	private void setModel(Custinfo custinfo, Model model){
		// 海富通
		List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(custinfo.getCustno());
		
		// 海富通资产显示
		if(null != hft_family_trade && hft_family_trade.size() > 0){
			Assets htfAssets = queryManager.queryAssets(hft_family_trade, BasicFundinfo.YFB.getFundCode());
			model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotal()));// 总资产
			model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
			model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFrozen()));// 冻结资产
			model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getFunddayincome()));// 昨日收益
			model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getTotalincome()));// 累计受益
			model.addAttribute("hft_family_trade_size", hft_family_trade.size());
		}else{
			model.addAttribute("hftTotalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
			model.addAttribute("hftAvailableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
			model.addAttribute("hftFrozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
			model.addAttribute("hftFunddayincome", NumberUtils.DF_CASH_CONMMA.format(0));// 昨日收益
			model.addAttribute("hftTotalincome", NumberUtils.DF_CASH_CONMMA.format(0));// 累计收益
			model.addAttribute("hft_family_trade_size", "0");
		}
	}
}
