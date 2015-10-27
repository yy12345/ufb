package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.PayNoticeVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 家庭版-幼富通：家校绑定、缴学费功能模块
 * @author ayis
 * 2015年10月26日
 */
@Controller
@RequestMapping(value="family/uft")
@Slf4j
public class PaymentController {
	
	private static final String UFT_INDEX = "family/uft_index.htm";
	private static final String UFT_INDEX_NAME = "我的账户";

	@Autowired
	private OrgQueryManager orgQueryManager;
	
	
	@RequestMapping(value = "pay_notice")
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
				log.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid " + "orgid = " + orgid + ",custno = " +custno);
				
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
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/user_error";
		}
		return "family/uft/pay_notice";
	}
	
	@RequestMapping(value = "pay_confirm")
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
				log.info("CusterController==.loginIn==orgQueryManager.getQueryStudentByOrgid " + "orgid = " + orgid + ",custno = " +custno);
				
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
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/user_error";
		}
		return "family/uft/pay_confirm";
	}
}
