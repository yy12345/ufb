package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.FamilyManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.OrgBankInfoVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.model.vo.PayListVo;
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
	
	private static final String UFT_INDEX = "family/uft/uft_index.htm";
	private static final String UFT_INDEX_NAME = "我的账户";

	@Autowired
	private OrgQueryManager orgQueryManager;
	@Autowired
	private FamilyManager familyManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private CustManager custManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	/**
	 * 识别码绑定学生，首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_index")
	public String codeIndex(Model model) {
		return "family/uft/code_index";
	}
	
	/**
	 * 异步验证识别码：第一次，仅通过识别码验证
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_verify")
	@ResponseBody
	public Map<String,Object> codeVerify(String code, Model model) {
	
		Map<String,Object> resultMap = new HashMap<String,Object>();
		CustinfoVo custinfo = UserHelper.getCustinfoVo();
		try{
			if(StringUtils.isBlank(code)){
				throw new UserException("参数为空！");
			}
			FamilyCodes familyCodes = new FamilyCodes();
			familyCodes.setCode(code);
			familyCodes = familyManager.getFamilyCodes(familyCodes);
			
			if(familyCodes == null){
				resultMap.put("errCode", "0001");
			}else if("1".equals(familyCodes.getState())){
				resultMap.put("errCode", "0002");
			}else if(!custinfo.getInvnm().equals(familyCodes.getParent_name())){
				resultMap.put("errCode", "0003");
				UserHelper.setSessionAttr("code", familyCodes.getCode());
			}else{
				resultMap.put("errCode", "0000");
				UserHelper.setSessionAttr("familyCodes", familyCodes);
			}
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
	 * 识别码绑定学生，（家长姓名不符），第二页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_parent")
	public String codeParent(Model model) {
		return "family/uft/code_parent";
	}
	
	/**
	 * 异步验证识别码：第二次，通过识别码和家长姓名验证
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_parentVerify")
	@ResponseBody
	public Map<String,Object> codeParentVerify(FamilyCodes familyCodes, Model model) {
	
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(StringUtils.isBlank(familyCodes.getCode())
					|| StringUtils.isBlank(familyCodes.getParent_name())){
				throw new UserException("参数为空！");
			}
			familyCodes = familyManager.getFamilyCodes(familyCodes);
			
			if(familyCodes == null){
				resultMap.put("errCode", "0004");
			}else{
				resultMap.put("errCode", "0000");
				UserHelper.setSessionAttr("familyCodes", familyCodes);
				UserHelper.setSessionAttr("changeParent", true);
			}
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
	 * 识别码绑定学生，第三页，确认页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_confirm")
	public String codeConfirm(Model model) {
		
		try{
			FamilyCodes familyCodes = (FamilyCodes)UserHelper.getSessionAttr("familyCodes");
			if(familyCodes == null){
				throw new UserException("识别码未匹配！");
			}
			Student student = familyManager.getStudent(familyCodes.getSid());
			Custinfo organ = familyManager.getOrgan(student.getCid());
			Bankcardinfo organCard = bankCardManager.getBankCardInfo(organ.getCustno());
			
			model.addAttribute("student", student);
			model.addAttribute("organ", organ);
			model.addAttribute("organCard", organCard);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "family/uft/code_confirm";
	}
	
	/**
	 * 异步确认绑定：家长确认并绑定学生对象
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_result")
	public String codeResult(Model model) {
	
		CustinfoVo custinfo = UserHelper.getCustinfoVo();
		try{
			FamilyCodes familyCodes = (FamilyCodes)UserHelper.getSessionAttr("familyCodes");
			boolean changeParent = (Boolean)UserHelper.getSessionAttr("changeParent");
			if(familyCodes == null){
				throw new UserException("识别码未匹配！");
			}
			
			familyManager.bindStudent(custinfo, familyCodes, changeParent);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "redirect:/"+UFT_INDEX;
	}
	
	@RequestMapping(value = "pay_notice")
	public String payNotice(String orgids,Model model) {
		
		List<PayListVo> planLists = new ArrayList<PayListVo>();
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			
			if(StringUtils.isBlank(orgids)){
				throw new UserException("系统异常！");
			}
			
			String[] orgidArr=orgids.split(",");
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			int plancount = 0;
			for(int i=0;i<orgidArr.length;i++){
				String orgid=orgidArr[i];
				Custinfo orgcust=custManager.getCustinfo(orgid);
				OrgBankInfoVo orginfo=new OrgBankInfoVo();
				orginfo.setOrgnm(orgcust.getOrgnm());
				orginfo.setOrgid(orgid);
				
				PayListVo stuPayVo=new PayListVo();
				List<QueryCustplandetail> planlist=new ArrayList<QueryCustplandetail>();
				planlist = orgQueryManager.getQueryCustplandetail(custinfoVo.getCustno(), orgid,null);
				if(null!=planlist && planlist.size()>0){
					for(QueryCustplandetail plan:planlist){
						plancount = plancount + 1;
						BigDecimal planmonthamt = BigDecimal.ZERO;
						if(null != plan.getPayappamount()){
							planmonthamt = new BigDecimal(plan.getPayappamount());
						}
						totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
					}
				}
				stuPayVo.setOrginfo(orginfo);
				stuPayVo.setPlanList(planlist);
				planLists.add(stuPayVo);
			}
			model.addAttribute("planLists", planLists);
			model.addAttribute("plancount", plancount);
			model.addAttribute("totalplanmonthamt", totalplanmonthamt);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "family/uft/pay_notice";
	}
	
	@RequestMapping(value = "pay_confirm")
	public String payReview(String  allplanids, Model model) {
		
		List<PayListVo> planlistchecked = new ArrayList<PayListVo>();
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			
			if(StringUtils.isBlank(allplanids)){
				throw new UserException("系统异常！");
			}
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			List<QueryCustplandetail> planchecked = new ArrayList<QueryCustplandetail>();
			String[] planids=allplanids.split(",");
			for(int i=0;i<planids.length;i++){
				String planid=planids[i];
				String orgid=orgQueryManager.getOrgidByPlanid(planid);
				List<QueryCustplandetail> planlist = orgQueryManager.getQueryCustplandetail(custinfoVo.getCustno(),orgid,planid);
				if(planlist.size()>0){
					QueryCustplandetail plan  = planlist.get(0);
					planchecked.add(plan);
				}
			}
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(custinfoVo.getCustno());
			for(QueryOrgStudent org: orglist){
				String orgid=org.getOrgid();
				List<QueryCustplandetail> planOrglist=new  ArrayList<QueryCustplandetail>();
				OrgBankInfoVo orginfo=custManager.queryOrgBankInfo(orgid);
				PayListVo payVo = new PayListVo();
				for(QueryCustplandetail plan:planchecked){
					if(orgid.equals(plan.getOrgid())){
						BigDecimal planmonthamt = BigDecimal.ZERO;
						if(null != plan.getPayappamount()){
							planmonthamt = new BigDecimal(plan.getPayappamount());
						}
						totalplanmonthamt = totalplanmonthamt.add(planmonthamt);
						planOrglist.add(plan);
					}
				}
				payVo.setOrginfo(orginfo);
				payVo.setPlanList(planOrglist);
				planlistchecked.add(payVo);
			}
			
			// 银行、账户信息 
			this.setModel(custinfoVo, model);
			Bankcardinfo bankcard=bankCardManager.getBankCardInfo(custinfoVo.getCustno());
			
			model.addAttribute("bankcard",bankcard);
			model.addAttribute("planlistchecked",planlistchecked);
			model.addAttribute("allcount",planids.length);
			model.addAttribute("totalplanmonthamt",totalplanmonthamt);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "family/uft/pay_confirm";
	}
	
	private void setModel(CustinfoVo custinfoVo, Model model){
		// 海富通
		List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
		if(null != hft_family_trade && hft_family_trade.size() > 0){
			Assets htfAssets = queryManager.queryAssets(hft_family_trade, BasicFundinfo.YFB.getFundCode());
			model.addAttribute("availableBalance", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
		}else {
			model.addAttribute("availableBalance", NumberUtils.DF_CASH_CONMMA.format(0));
		}
	}
}
