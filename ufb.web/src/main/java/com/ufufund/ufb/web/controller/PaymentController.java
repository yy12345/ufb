package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.ufufund.ufb.biz.manager.OrgQueryManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.Assets;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.OrgPlanDetail;
import com.ufufund.ufb.model.db.OrgPriceItem;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.PayList;
import com.ufufund.ufb.model.db.PayRecordQry;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.BasicFundinfo;
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
	private CustManager custManager;
	@Autowired
	private FamilyManager familyManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private OrgQueryManager orgQueryManager;
	
	/**
	 * 识别码绑定学生，首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_index")
	public String codeIndex(Model model) {
		return "uft/code_index";
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
		Custinfo custinfo = UserHelper.getCustinfo();
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
			}else if(!custinfo.getName().equals(familyCodes.getParent_name())){
				resultMap.put("errCode", "0003");
				UserHelper.setSessionAttr("code", familyCodes.getCode());
				UserHelper.setSessionAttr("changeParent", true);
			}else{
				resultMap.put("errCode", "0000");
				UserHelper.setSessionAttr("familyCodes", familyCodes);
				UserHelper.setSessionAttr("changeParent", false);
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
		return "uft/code_parent";
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
			OrgQuery organ = familyManager.getOrgan(student.getCid());
			Bankcardinfo organCard = bankCardManager.getBankcardinfo(organ.getOrgid());
			
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
		return "uft/code_confirm";
	}
	
	/**
	 * 异步确认绑定：家长确认并绑定学生对象
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "code_result")
	public String codeResult(Model model) {
	
		Custinfo custinfo = UserHelper.getCustinfo();
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
	
	/**
	 * 家长确认缴费：预览页
	 * @param orgids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pay_preview")
	public String payPreview(String orgids,Model model) {
		
		List<PayList> planLists = new ArrayList<PayList>();
		try{
			Custinfo custinfo = UserHelper.getCustinfo();
			
			if(StringUtils.isBlank(orgids)){
				throw new UserException("系统异常！");
			}
			
			String[] orgidArr=orgids.split(",");
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			int plancount = 0;
			List<String> ispaylist=new ArrayList<String>();
			ispaylist.add("0");
			for(int i=0;i<orgidArr.length;i++){
				String orgid=orgidArr[i];
				OrgQuery orginfo=new OrgQuery();
				orginfo.setOrgid(orgid);
				
				PayList stuPayVo=new PayList();
				List<OrgPlanDetail> planlist=new ArrayList<OrgPlanDetail>();
				OrgPlanDetail orgdetail = new OrgPlanDetail();
				
				orgdetail.setParentid(custinfo.getCustno());
				orgdetail.setOrgid(orgid);
				orgdetail.setIspaylist(ispaylist);
				planlist = orgQueryManager.queryCustplandetail(orgdetail);
				if(null!=planlist && planlist.size()>0){
					for(OrgPlanDetail plan:planlist){
						orginfo.setOrgname(plan.getOrgname());
						plancount = plancount + 1;
						BigDecimal planmonthamt = BigDecimal.ZERO;
						if(null != plan.getAmount()){
							planmonthamt = new BigDecimal(plan.getAmount());
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
		return "uft/pay_preview";
	}
	
	/**
	 * 家长确认缴费：确认页
	 * @param detailids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pay_confirm")
	public String payConfirm(String  detailids, Model model) {
		
		List<PayList> planlistchecked = new ArrayList<PayList>();
		try{
			Custinfo custinfo = UserHelper.getCustinfo();
			
			if(StringUtils.isBlank(detailids)){
				throw new UserException("系统异常！");
			}
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			List<OrgPlanDetail> planchecked = new ArrayList<OrgPlanDetail>();
			String[] detailid=detailids.split(",");
			List<String> ispaylist=new ArrayList<String>();
			ispaylist.add("0");
			for(int i=0;i<detailid.length;i++){
				String detail=detailid[i];
				String orgid=orgQueryManager.getOrgidByDetailid(detail);
				
				OrgPlanDetail orgdetail = new OrgPlanDetail();
				orgdetail.setParentid(custinfo.getCustno());
				orgdetail.setId(detail);
				orgdetail.setOrgid(orgid);
				orgdetail.setIspaylist(ispaylist);
				List<OrgPlanDetail> planlist = orgQueryManager.queryCustplandetail(orgdetail);
				if(planlist.size()>0){
					OrgPlanDetail plan  = planlist.get(0);
					planchecked.add(plan);
				}
			}
			
			List<OrgQuery> orglist = orgQueryManager.queryOrgByCustno(custinfo.getCustno());
			for(OrgQuery org: orglist){
				String orgid=org.getOrgid();
				List<OrgPlanDetail> planOrglist=new  ArrayList<OrgPlanDetail>();
				OrgQuery orginfo=custManager.queryOrgBankInfo(orgid);
				PayList payVo = new PayList();
				for(OrgPlanDetail plan:planchecked){
					if(orgid.equals(plan.getOrgid())){
						BigDecimal planmonthamt = BigDecimal.ZERO;
						if(null != plan.getAmount()){
							planmonthamt = new BigDecimal(plan.getAmount());
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
			this.setModel(custinfo, model);
			Bankcardinfo bankcard=bankCardManager.getBankcardinfo(custinfo.getCustno());
			
			model.addAttribute("bankcard",bankcard);
			model.addAttribute("planlistchecked",planlistchecked);
			model.addAttribute("allcount",detailid.length);
			model.addAttribute("totalplanmonthamt",totalplanmonthamt);
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "uft/pay_confirm";
	}
	
	/**
	 * 家长确认缴费：结果页
	 * @param paytype
	 * @param orgmsg
	 * @param detailids
	 * @param allpayconfirm
	 * @param tradePwd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_result")
	public String payResult(String paytype,String orgmsg,String detailids,String allpayconfirm,String tradePwd,Model model){
		
		Custinfo custinfo = UserHelper.getCustinfo();
		try{
			// 交易密码校验
			Custinfo s_custinfo=custManager.getCustinfo(custinfo.getCustno());
			if(!s_custinfo.getTradepwd().equals(EncryptUtil.md5(tradePwd))){
				throw new UserException("交易密码错误！");
			}		
			if(StringUtils.isBlank(detailids)||StringUtils.isBlank(paytype)||StringUtils.isBlank(orgmsg)){
				throw new UserException("输入参数为空！");
			}
			
			// 机构信息
			String[] orgs=orgmsg.split(",");
			List<String> orgnamelist=new ArrayList<String>();
			for(int i=0;i<orgs.length;i++){
				orgnamelist.add(orgs[i]);
			}
			// 支付方式  :幼富宝    快捷方式    
			String paydate=orgQueryManager.confirmDetail(detailids,s_custinfo, paytype);
			paydate=paydate.substring(0, 4)+"年"+paydate.substring(4, 6)+"月"+paydate.substring(6)+"日";
			
			model.addAttribute("orgnamelist", orgnamelist);
			model.addAttribute("paytype", paytype);
			model.addAttribute("allpayconfirm", allpayconfirm);
			model.addAttribute("paydate", paydate);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "uft/pay_result";
	}
	
	/**
	 * 缴费明细:记录列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="record_index")
	public String recordIndex(PayRecordQry vo, Model model){
		
		String custno = UserHelper.getCustno();	
		try{
			// 获取绑定机构信息
			List<OrgQuery> orglist = orgQueryManager.queryOrgByCustno(custno);
			if(orglist == null || orglist.size() == 0){
				return "redirect:/family/uft/code_index.htm";
			}
			
			if(vo == null){
				vo = new PayRecordQry();
			}
			vo.setCustno(custno);
			// 选中机构
			if(StringUtils.isBlank(vo.getOrgid())){
				vo.setOrgid(orglist.get(0).getOrgid());
			}
			// 选中展示机构下的学生信息
			List<OrgQuery> studentlist = orgQueryManager.queryStudentByOrgid(vo.getOrgid(), custno);
			// 起、止日期
			if(StringUtils.isBlank(vo.getStartTime())){
				vo.setStartTime(DateUtil.format(getStartTime(), DateUtil.DATE_PATTERN_2));
			}
			if(StringUtils.isBlank(vo.getEndTime())){
				vo.setEndTime(DateUtil.format(getEndTime(), DateUtil.DATE_PATTERN_2));
			}
			/** 分页参数  **/
			// total
			vo.setTotal(orgQueryManager.getPayRecordCount(vo));
			// pageSize
			if(vo.getPageSize() == 0){
				vo.setPageSize(5);
			}else if(vo.getPageSize() > 20){
				vo.setPageSize(20);
			}
			int pageSize = vo.getPageSize();
			// page参数
			if(vo.getPage() < 1){
				vo.setPage(1);
			}
			if((vo.getPage()-1) * pageSize <= vo.getTotal()){
				vo.setStart((vo.getPage()-1)*pageSize + 1);
				vo.setEnd(vo.getPage()*pageSize);
			}
			/** 校验  **/
			if(vo.getStartTime().compareTo(vo.getEndTime()) > 0){
				throw new UserException("查询时间错误！");
			}
			if((vo.getPage()-1) * pageSize > vo.getTotal()){
				throw new UserException("分页参数page已超出范围！");
			}
			
			List<OrgPlanDetail> detailList = orgQueryManager.getPayRecords(vo);
			BigDecimal paidTotal = orgQueryManager.getPaidTotalAmt(vo);
			BigDecimal reversedTotal = orgQueryManager.getReversedTotalAmt(vo);
			
			
			model.addAttribute("orglist", orglist);
			model.addAttribute("vo", vo);
			model.addAttribute("studentlist", studentlist);
			model.addAttribute("detailList", detailList);
			model.addAttribute("paidTotal", NumberUtils.DF_CASH_CONMMA.format(paidTotal.doubleValue()));
			model.addAttribute("reversedTotal", NumberUtils.DF_CASH_CONMMA.format(reversedTotal.doubleValue()));
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "uft/record_index";
	}
	
	/**
	 * 缴费明细:记录单元查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="record_unit")
	public String recordUnit(PayRecordQry vo, Model model){
		
		String custno = UserHelper.getCustno();	
		try{
			vo.setCustno(custno);
			/** 分页参数  **/
			// total
			vo.setTotal(orgQueryManager.getPayRecordCount(vo));
			// total
			vo.setTotal(orgQueryManager.getPayRecordCount(vo));
			// pageSize
			if(vo.getPageSize() == 0){
				vo.setPageSize(5);
			}else if(vo.getPageSize() > 20){
				vo.setPageSize(20);
			}
			int pageSize = vo.getPageSize();
			// page参数
			if(vo.getPage() < 1){
				vo.setPage(1);
			}
			if((vo.getPage()-1) * pageSize <= vo.getTotal()){
				vo.setStart((vo.getPage()-1)*pageSize + 1);
				vo.setEnd(vo.getPage()*pageSize);
			}
			/** 校验  **/
			if(vo.getStartTime().compareTo(vo.getEndTime()) > 0){
				throw new UserException("查询时间错误！");
			}
			if((vo.getPage()-1) * pageSize > vo.getTotal()){
				throw new UserException("分页参数page已超出范围！");
			}
			
			List<OrgPlanDetail> detailList = orgQueryManager.getPayRecords(vo);
			
			model.addAttribute("vo", vo);
			model.addAttribute("detailList", detailList);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "uft/record_unit";
	}
	
	/**
	 * 缴费记录：缴费通知书
	 * @param model
	 * @return
	 */
	@RequestMapping(value="record_notice")
	public String recordNotice(String detailid,Model model){
		
		Custinfo custinfo = UserHelper.getCustinfo();	
		try{
			// 获得缴费详情信息
			OrgPlanDetail  payVo = orgQueryManager.getDetailNotice(custinfo.getCustno(), detailid);
			BigDecimal amount = new  BigDecimal(payVo.getAmount());
			BigDecimal discount = new  BigDecimal(payVo.getDiscount());
			BigDecimal ackamount = amount.subtract(discount);// 应收金额
			List<String> priceitems = Arrays.asList(payVo.getPrice_detail().split(","));	//计划表中的收费明细
			List<OrgPriceItem> orgPriceItems = 	orgQueryManager.getPriceItemList(priceitems);	//获取收费价目明细
			// 确认截止日  later.....
			String deadline = DateUtil.format(new Date(), DateUtil.DATE_PATTERN_1);
			String dead = payVo.getDeadline();
			
			model.addAttribute("deadline", deadline);
			model.addAttribute("dead", dead);
			model.addAttribute("ackamount", ackamount);
			model.addAttribute("planDetail", payVo);
			model.addAttribute("planPriceItems", orgPriceItems);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", UFT_INDEX);
			model.addAttribute("back_module", UFT_INDEX_NAME);
			return "error/error";
		}
		return "uft/record_notice";
	}
	
	private void setModel(Custinfo Custinfo, Model model){
		// 海富通
		List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(Custinfo.getCustno());
		if(null != hft_family_trade && hft_family_trade.size() > 0){
			Assets htfAssets = queryManager.queryAssets(hft_family_trade, BasicFundinfo.YFB.getFundCode());
			model.addAttribute("availableBalance", NumberUtils.DF_CASH_CONMMA.format(htfAssets.getAvailable()));// 可用资产
		}else {
			model.addAttribute("availableBalance", NumberUtils.DF_CASH_CONMMA.format(0));
		}
	}
	
	/**
	 * 获取缴费明细查询起始日期
	 * @return
	 */
	private Date getStartTime(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) -2);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * 获取缴费明细查询截止日期
	 * @return
	 */
	private Date getEndTime(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
}
