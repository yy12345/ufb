package com.ufufund.uft.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.org.OrgPlanManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FamilyCodes;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.OrgBankInfoVo;
import com.ufufund.ufb.model.vo.PayListVo;
import com.ufufund.ufb.model.vo.PayRecordQryVo;
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
	@Autowired
	private OrgPlanManager orgPlanManager;
	
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
	
	/**
	 * 家长确认缴费：预览页
	 * @param orgids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pay_preview")
	public String payPreview(String orgids,Model model) {
		
		List<PayListVo> planLists = new ArrayList<PayListVo>();
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			
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
				Custinfo orgcust=custManager.getCustinfo(orgid);
				OrgBankInfoVo orginfo=new OrgBankInfoVo();
				orginfo.setOrgnm(orgcust.getOrgnm());
				orginfo.setOrgid(orgid);
				
				PayListVo stuPayVo=new PayListVo();
				List<QueryCustplandetail> planlist=new ArrayList<QueryCustplandetail>();
				planlist = orgQueryManager.getQueryCustplandetail(custinfoVo.getCustno(), orgid,null,ispaylist);
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
		return "family/uft/pay_preview";
	}
	
	/**
	 * 家长确认缴费：确认页
	 * @param detailids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pay_confirm")
	public String payConfirm(String  detailids, Model model) {
		
		List<PayListVo> planlistchecked = new ArrayList<PayListVo>();
		try{
			CustinfoVo custinfoVo = UserHelper.getCustinfoVo();
			
			if(StringUtils.isBlank(detailids)){
				throw new UserException("系统异常！");
			}
			BigDecimal totalplanmonthamt = BigDecimal.ZERO;
			List<QueryCustplandetail> planchecked = new ArrayList<QueryCustplandetail>();
			String[] detailid=detailids.split(",");
			List<String> ispaylist=new ArrayList<String>();
			ispaylist.add("0");
			for(int i=0;i<detailid.length;i++){
				String detail=detailid[i];
				String orgid=orgQueryManager.getOrgidByDetailid(detail);
				List<QueryCustplandetail> planlist = orgQueryManager.getQueryCustplandetail(custinfoVo.getCustno(),orgid,detail,ispaylist);
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
		return "family/uft/pay_confirm";
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
		
		CustinfoVo custinfo = UserHelper.getCustinfoVo();
		try{
			// 交易密码校验
			Custinfo d_custinfo=custManager.getCustinfo(custinfo.getCustno());
			if(!d_custinfo.getTradepwd().equals(EncryptUtil.md5(tradePwd))){
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
			String paydate=orgPlanManager.confirmDetail(detailids, custinfo.getCustno(), paytype);
			
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
		return "family/uft/pay_result";
	}
	
	/**
	 * 缴费明细:记录列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_records")
	public String payRecords(PayRecordQryVo vo, Model model){
		
		String custno = UserHelper.getCustno();	
		try{
			// 获取绑定机构信息
			List<QueryOrgStudent> orglist = orgQueryManager.getQueryOrgByCustno(custno);
			if(orglist == null || orglist.size() == 0){
				return "redirect:/family/uft/code_index.htm";
			}
			
			if(vo == null){
				vo = new PayRecordQryVo();
			}
			vo.setCustno(custno);
			// 选中机构
			if(StringUtils.isBlank(vo.getOrgid())){
				vo.setOrgid(orglist.get(0).getOrgid());
			}
			// 选中展示机构下的学生信息
			List<QueryOrgStudent> studentlist = orgQueryManager.getQueryStudentByOrgid(vo.getOrgid(), custno);
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
			
			List<QueryCustplandetail> detailList = orgQueryManager.getPayRecords(vo);
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
		return "family/uft/pay_records";
	}
	
	/**
	 * 缴费明细:记录单元查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="record_unit")
	public String recordUnit(PayRecordQryVo vo, Model model){
		
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
			
			List<QueryCustplandetail> detailList = orgQueryManager.getPayRecords(vo);
			
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
		return "family/uft/record_unit";
	}
	
	/**
	 * 缴费记录：缴费通知书
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_notice")
	public String payNotice(Model model){
		
		CustinfoVo custinfo = UserHelper.getCustinfoVo();	
		try{
			
			
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
